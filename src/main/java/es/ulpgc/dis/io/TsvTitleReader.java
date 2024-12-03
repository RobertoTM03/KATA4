package es.ulpgc.dis.io;

import es.ulpgc.dis.model.Title;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TsvTitleReader implements TitleReader {

    private final BufferedReader reader;

    public TsvTitleReader(File file, boolean header) throws IOException {
        reader = new BufferedReader(new FileReader(file));
        if (header) readHeaderWith(reader);
    }

    @Override
    public Title read() throws IOException {
        String line = reader.readLine();
        if (line == null) return null;
        return new TsvTitleDeserializer().deserialize(line);
    }

    private static void readHeaderWith(BufferedReader reader) throws IOException {
        reader.readLine();
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
