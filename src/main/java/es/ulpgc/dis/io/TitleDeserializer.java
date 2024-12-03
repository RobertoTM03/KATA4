package es.ulpgc.dis.io;

import es.ulpgc.dis.model.Title;

import java.io.IOException;

public interface TitleDeserializer<T> {
    Title deserialize(T line) throws IOException;
}
