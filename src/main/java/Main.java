import control.HistogramGenerator;
import control.TitleReader;
import control.TsvTitleReader;
import model.Histogram;
import model.Title;
import view.MainFrame;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        checkNumberOfArgs(args);

        TitleReader reader = new TsvTitleReader(new File(args[0]), true);
        Histogram histogram = generateHistogram(reader);

        display(histogram);
    }

    private static void display(Histogram histogram) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.put(histogram);
        mainFrame.setVisible(true);
    }

    private static Histogram generateHistogram(TitleReader reader) throws IOException {
        List<Title> titles = reader.read();

        return HistogramGenerator.generate(titles);
    }

    private static void checkNumberOfArgs(String[] args) throws IOException {
        if (args.length != 1) throw new IOException("Incorrect number of args");
    }
}
