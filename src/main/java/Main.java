import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        TitleReader reader = new TsvTitleReader(new File("./title.basics.tsv"), true);
        List<Title> titles = reader.read();

        Map<Title.TitleType, Integer> histogram = new HashMap<>();

        for(Title title : titles){
            histogram.putIfAbsent(title.type(), 0);
            histogram.compute(title.type(), (k, v) -> v + 1);
        }

        System.out.println(histogram);
    }
}
