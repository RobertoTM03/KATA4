package es.ulpgc.dis.io;

import es.ulpgc.dis.model.Title;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TsvTitleDeserializer implements TitleDeserializer<String> {
    @Override
    public Title deserialize(String line) {
        String[] fields = line.split("\t");
        return new Title(fields[0], typeOf(fields[1]), fields[2], fields[3], genresOf(fields[8]));
    }

    private Title.TitleType typeOf(String field) {
        return Title.TitleType.valueOf(capitalize(field.trim()));
    }

    private String capitalize(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    private List<Title.Genre> genresOf(String field) {
        if (field.equals("\\N")) return Collections.emptyList();
        List<Title.Genre> genres = new ArrayList<>();

        for (String genre : field.split(","))
            genres.add(Title.Genre.valueOf(genre.trim().replace("-", "")));

        return genres;
    }
}
