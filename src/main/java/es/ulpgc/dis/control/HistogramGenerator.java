package es.ulpgc.dis.control;

import es.ulpgc.dis.model.Histogram;
import es.ulpgc.dis.model.Title;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistogramGenerator {

    private final Map<String, Integer> histogram;

    public HistogramGenerator() {
        histogram = new HashMap<>();
    }

    public void feed(Title title) {
        histogram.putIfAbsent(title.type().name(), 0);
        histogram.compute(title.type().name(), (k, v) -> v + 1);
    }

    public Histogram get() {
        return new MapHistogram(histogram);
    }

    private static class MapHistogram implements Histogram {
        private final Map<String, Integer> histogram;

        public MapHistogram(Map<String, Integer> histogram) {
            this.histogram = histogram;
        }

        @Override
        public String title() {
            return "Title types distribution";
        }

        @Override
        public List<String> labels() {
            return new ArrayList<>(histogram.keySet());
        }

        @Override
        public int valueOf(String label) {
            return histogram.get(label);
        }
    }
}
