package control;

import model.Histogram;
import model.Title;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistogramGenerator {
    public static Histogram generate(List<Title> titles) {
        Map<String, Integer> histogram = new HashMap<>();

        for (Title title : titles) {
            histogram.putIfAbsent(title.type().name(), 0);
            histogram.compute(title.type().name(), (k, v) -> v + 1);
        }

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
