package es.ulpgc.dis.io;

import es.ulpgc.dis.model.Title;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SQLiteTitleDeserializer implements TitleDeserializer<ResultSet> {
    @Override
    public Title deserialize(ResultSet line) throws IOException {
        try {
            return new Title(
                    line.getString(1),
                    Title.TitleType.valueOf(line.getString(2)),
                    line.getString(3),
                    line.getString(4),
                    toGenres(line.getString(5))
            );
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private List<Title.Genre> toGenres(String value) {
        if (value.isEmpty()) return Collections.emptyList();
        List<Title.Genre> genres = new ArrayList<>();
        for (String name : value.split(";"))
            genres.add(Title.Genre.valueOf(name.trim()));
        return genres;
    }
}
