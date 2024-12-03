package es.ulpgc.dis.io;

import es.ulpgc.dis.model.Title;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteTitleReader implements TitleReader{

    private final Connection connection;
    private final ResultSet resultSet;

    public SQLiteTitleReader(File dbFile) throws IOException {
        try {
            connection = openConnection(dbFile);
            resultSet = connection.createStatement().executeQuery("SELECT * FROM titles;");
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private static Connection openConnection(File dbFile) throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
    }

    @Override
    public Title read() throws IOException {
        try {
            if (resultSet.next()) {
                return new SQLiteTitleDeserializer().deserialize(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public Title randomRead() throws IOException {
        try {
            ResultSet resultTitle = connection.createStatement().executeQuery("SELECT * FROM titles ORDER BY RANDOM() LIMIT 1;");

            if (resultTitle.next()) {
                return new SQLiteTitleDeserializer().deserialize(resultTitle);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }
}
