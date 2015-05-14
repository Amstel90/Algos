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

    @Test
    public void testDistance1() {

        WordNet wordNet = getWordnet("synsets100-subgraph.txt", "hypernyms100-subgraph.txt");

        Assert.assertEquals(1, wordNet.distance("change", "thing"));

        wordNet = getWordnet("synsets500-subgraph.txt", "hypernyms500-subgraph.txt");

        Assert.assertEquals(6, wordNet.distance("oil", "antihemophilic_globulin"));

        wordNet = getWordnet("synsets1000-subgraph.txt", "hypernyms1000-subgraph.txt");

        Assert.assertEquals(3, wordNet.distance("oil", "lecithin"));
    }

    private void test(String synsets, String hypernyms, String nounA, String nounB, String expectedAncestor, int expectedLength) {

        WordNet wordNet = getWordnet(synsets, hypernyms);

        Assert.assertEquals(expectedLength, wordNet.distance(nounA, nounB));
        Assert.assertEquals(expectedAncestor, wordNet.sap(nounA, nounB).trim());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidRootTest1() {
        WordNet wordNet = getWordnet("synsets3.txt", "hypernyms3InvalidTwoRoots.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidRootTest2() {
        WordNet wordNet = getWordnet("synsets3.txt", "hypernyms3InvalidCycle.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidRootTest3() {
        WordNet wordNet = getWordnet("synsets6.txt", "hypernyms6InvalidTwoRoots.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidRootTest4() {
        WordNet wordNet = getWordnet("synsets6.txt", "hypernyms6InvalidTwoRoots.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidRootTest5() {
        WordNet wordNet = getWordnet("synsets6.txt", "hypernyms6InvalidCycle.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidRootTest6() {
        WordNet wordNet = getWordnet("synsets6.txt", "hypernyms6InvalidCycle+Path.txt");
    }

    private WordNet getWordnet(String synsets, String hypernyms) {
        return new WordNet(folder + synsets, folder + hypernyms);
    }

}
