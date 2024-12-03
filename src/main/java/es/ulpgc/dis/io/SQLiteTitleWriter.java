package es.ulpgc.dis.io;

import es.ulpgc.dis.model.Title;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteTitleWriter implements TitleWriter{
    private static final String createTableStatement = """
            CREATE TABLE titles(
                id TEXT PRIMARY KEY,
                type TEXT,
                primaryTitle TEXT,
                originalTitle TEXT,
                genres TEXT
            );
            """;
    private static final String insertStatement = "INSERT INTO titles (id, type, primaryTitle, originalTitle, genres) VALUES (?,?,?,?,?)";
    private final Connection connection;
    private PreparedStatement preparedInsertStatement;

    public SQLiteTitleWriter(File dbFile) throws IOException {
        try {
            connection = openConnection(dbFile);
            prepareDatabase();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private void prepareDatabase() throws SQLException {
        connection.createStatement().execute(createTableStatement);
        preparedInsertStatement = connection.prepareStatement(insertStatement);
        connection.setAutoCommit(false);
    }

    private static Connection openConnection(File dbFile) throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
    }

    @Override
    public void write(Title title) throws IOException {
        try {
            preparedInsertStatement.setString(1, title.id());
            preparedInsertStatement.setString(2, title.type().name());
            preparedInsertStatement.setString(3, title.title());
            preparedInsertStatement.setString(4, title.originalTitle());
            preparedInsertStatement.setString(5, String.join(";", toListString(title.genres())));
            preparedInsertStatement.execute();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private List<String> toListString(List<Title.Genre> genres) {
        List<String> names = new ArrayList<>();
        for (Title.Genre genre : genres)
            names.add(genre.name());
        return names;
    }

    @Override
    public void close() throws Exception {
        connection.commit();
        connection.close();
    }
}
