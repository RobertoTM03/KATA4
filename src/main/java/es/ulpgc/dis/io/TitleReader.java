package es.ulpgc.dis.io;

import es.ulpgc.dis.model.Title;

import java.io.IOException;

public interface TitleReader extends AutoCloseable {
    Title read() throws IOException;
}
