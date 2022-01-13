package pl.sdacademy.java.adv.school.parsers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public abstract class AbstractCsvParser<R> implements RecordsParser<R> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCsvParser.class);

    @Override
    public List<R> parseData(InputStream dataStream) throws IOException {

        if (dataStream == null) {
            return Collections.emptyList();
        }

        List<R> results = new LinkedList<>();
        Scanner scanner = new Scanner(dataStream);
        int lineNumber = 0;
        while (scanner.hasNextLine()) {
            lineNumber++;
            String line = scanner.nextLine();
            if (StringUtils.isBlank(line)) {                        //line == null || line.length() == 0
                LOGGER.info("Skipping line [{}] ", lineNumber);
                continue;
            }
            LOGGER.info("Parsing line [{}]:{} ", lineNumber, line);
            //parseRecord(line).ifPresent(result -> results.add(result));
            parseRecord(line).ifPresent(results::add);
        }
        return results;
    }

    protected abstract Optional<R> parseRecord(String line) throws IOException;
}
