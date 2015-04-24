import org.junit.Assert;
import org.junit.Test;

public class WordNetTest {

    public static final String folder = "D:\\Projects\\Algos\\AlgoP2T1\\src\\resources\\";
    public static final String synsets = folder + "synsets3.txt";
    public static final String hypernyms = folder + "hypernyms.txt";

    @Test
    public void test() {

        WordNet wordNet = new WordNet(synsets, hypernyms);
    }
}
