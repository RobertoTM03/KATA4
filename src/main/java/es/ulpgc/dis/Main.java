package es.ulpgc.dis;

import es.ulpgc.dis.control.HistogramGenerator;
import es.ulpgc.dis.control.TitleLoader;
import es.ulpgc.dis.io.SQLiteTitleReader;
import es.ulpgc.dis.io.TitleReader;
import es.ulpgc.dis.io.TsvTitleReader;
import es.ulpgc.dis.model.Histogram;
import es.ulpgc.dis.model.Title;
import es.ulpgc.dis.view.MainFrame;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        checkNumberOfArgs(args);

        File sourceFile = new File(args[0]);
        File dbFile = new File(args[1]);
        new TitleLoader(sourceFile, dbFile).execute();

        Histogram histogram = generateHistogram(dbFile);

        display(histogram, dbFile);
    }

    private static void display(Histogram histogram, File dbFile) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.put(histogram, dbFile);
        mainFrame.setVisible(true);
    }

    private static Histogram generateHistogram(File dbFile) throws IOException {
        HistogramGenerator histogramGenerator = new HistogramGenerator();

        try (SQLiteTitleReader reader = new SQLiteTitleReader(dbFile)) {

            while (true) {
                Title title = reader.read();
                if (title == null) break;
                histogramGenerator.feed(title);
            }
        } catch (Exception e) {
            throw new IOException(e);
        }

        return histogramGenerator.get();
    }

    private static void checkNumberOfArgs(String[] args) throws IOException {
        if (args.length != 2) throw new IOException("Incorrect number of args");
    }
}
