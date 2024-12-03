package es.ulpgc.dis.view;

import es.ulpgc.dis.io.SQLiteTitleReader;
import es.ulpgc.dis.model.Title;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class JRandomTitlePanel extends JPanel {

    private final JLabel resultLabel;
    private File dbFile;

    public JRandomTitlePanel() {
        JButton randomButton = new JButton("Realize random consult");
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizeConsult();
            }
        });

        resultLabel = new JLabel();

        setLayout(new BorderLayout());
        add(resultLabel, BorderLayout.NORTH);
        add(randomButton, BorderLayout.SOUTH);
    }

    private void realizeConsult() {
        try (SQLiteTitleReader reader = new SQLiteTitleReader(dbFile)) {
            Title title = reader.randomRead();

            if (title != null) {
                String resultText = "<html> ID: " + title.id() + "<br>" +
                        "Type: " + title.type() + "<br>" +
                        "Title: " + title.originalTitle() + "<br>" +
                        "Genre: " + title.genres().toString() + "</html>";
                resultLabel.setText(resultText);
            } else {
                resultLabel.setText("No titles founds");
            }
        } catch (Exception e) {
            resultLabel.setText("Error getting random title: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void display(File dbFile) {
        this.dbFile = dbFile;
    }
}
