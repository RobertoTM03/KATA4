package view;

import model.Histogram;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final JFreeBarChartHistogramDisplay histogramDisplay;

    public MainFrame() throws HeadlessException {
        setTitle("Histogram display");
        setSize(1100, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        histogramDisplay = new JFreeBarChartHistogramDisplay();
        add(histogramDisplay);
    }

    public void put(Histogram histogram){
        histogramDisplay.display(histogram);
    }
}
