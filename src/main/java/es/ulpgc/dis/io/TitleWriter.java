package es.ulpgc.dis.io;

import es.ulpgc.dis.model.Title;

import java.io.IOException;

public interface TitleWriter extends AutoCloseable{
    void write(Title title) throws IOException;
}
