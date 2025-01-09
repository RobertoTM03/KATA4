package es.ulpgc.dis.control;

import es.ulpgc.dis.io.SQLiteTitleWriter;
import es.ulpgc.dis.io.TsvTitleReader;
import es.ulpgc.dis.model.Title;

import java.io.File;

public class TitleLoader {
    private final File sourceFile;
    private final File dbFile;

    public TitleLoader(File sourceFile, File dbFile) {
        this.sourceFile = sourceFile;
        this.dbFile = dbFile;
    }

    public void execute() {
        try (SQLiteTitleWriter writer = new SQLiteTitleWriter(dbFile);
             TsvTitleReader reader = new TsvTitleReader(sourceFile, true)) {
            while (true) {
                Title title = reader.read();
                if (title == null) break;
                writer.write(title);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
