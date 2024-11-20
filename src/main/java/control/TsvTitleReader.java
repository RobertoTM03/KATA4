package control;

import model.Title;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TsvTitleReader implements TitleReader {
    private final File file;
    private final boolean header;

    public TsvTitleReader(File file, boolean header) {
        this.file = file;
        this.header = header;
    }

    @Override
    public List<Title> read() throws IOException {
        List<Title> titles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            if (header) readHeaderWith(reader);

            while (true){
                String line = reader.readLine();
                if (line == null) break;
                titles.add(new TsvTitleDeserializer().deserialize(line));
            }
        }


        return titles;
    }

    private static void readHeaderWith(BufferedReader reader) throws IOException {
        reader.readLine();
    }
}
