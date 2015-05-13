import org.junit.Assert;
import org.junit.Test;

public class WordNetTest {

    public static final String folder = "D:\\Projects\\Study\\src\\Algos\\AlgoP2T1\\src\\resources\\";

    @Test
    public void test() {

        test("synsets100-subgraph.txt", "hypernyms100-subgraph.txt",
                "macromolecule", "haptoglobin",
                "macromolecule supermolecule", 2);
    }

    @Test
    public void test2() {
        test("synsets500-subgraph.txt", "hypernyms500-subgraph.txt",
                "HMG-CoA_reductase", "Lipo-Hepin",
                "macromolecule supermolecule", 7);
    }

    @Test
    public void test3() {
        test("synsets1000-subgraph.txt", "hypernyms1000-subgraph.txt",
                "ember", "adrenal_cortex",
                "part piece", 7);
    }

    private void test(String synsets, String hypernyms, String nounA, String nounB, String expectedAncestor, int expectedLength) {

        WordNet wordNet = new WordNet(folder + synsets, folder + hypernyms);

        Assert.assertEquals(expectedLength, wordNet.distance(nounA, nounB));
        Assert.assertEquals(expectedAncestor, wordNet.sap(nounA, nounB).trim());
    }

}
