package pl.sdacademy.java.adv.school.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface RecordsParser<R> {
    List<R> parseData(InputStream inputStream) throws IOException;
}
