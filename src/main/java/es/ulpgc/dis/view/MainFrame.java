package es.ulpgc.dis.view;

import es.ulpgc.dis.model.Histogram;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {

    private final JFreeBarChartHistogramDisplay histogramDisplay;
    private final JRandomTitlePanel randomTitlePanel;

    public MainFrame() throws HeadlessException {
        setTitle("Histogram display");
        setSize(1100, 920);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        histogramDisplay = new JFreeBarChartHistogramDisplay();
        randomTitlePanel = new JRandomTitlePanel();

        setLayout(new BorderLayout());
        add(histogramDisplay, BorderLayout.NORTH);
        add(randomTitlePanel, BorderLayout.SOUTH);
    }

    public void put(Histogram histogram, File dbFile){
        histogramDisplay.display(histogram);
        randomTitlePanel.display(dbFile);
    }
}
