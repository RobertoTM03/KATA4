package control;

import model.Title;

public interface TitleDeserializer {
    Title deserialize(String line);
}
