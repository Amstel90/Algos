import org.junit.Test;

/**
 * Created by Artem_Mikhalevitch on 5/13/15.
 */
public class OutcastTest {

    public static final String folder = "D:\\Projects\\Study\\src\\Algos\\AlgoP2T1\\src\\resources\\";

    @Test
    public void test() {
        WordNet wordNet = new WordNet(folder + "synsets50000-subgraph.txt",
                folder + "hypernyms50000-subgraph.txt");

        Outcast outcast = new Outcast(wordNet);

        In in = new In(folder + "outcast8.txt");
        String[] nouns = in.readAllStrings();
        StdOut.println(outcast.outcast(nouns));
    }
}
