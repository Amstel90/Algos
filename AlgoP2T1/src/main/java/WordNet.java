import java.util.ArrayList;

/**
 * Created by ASUS on 16.04.2015.
 */
public class WordNet {

    ArrayList<Synset> synsetsList;

    /**
     * Constructor takes the name of the two input files.
     *
     * @param synsets   file 1
     * @param hypernyms file 2
     */
    public WordNet(String synsets, String hypernyms) {

        if (synsets == null || hypernyms == null) {
            throw new NullPointerException();
        }

        In synsetsIn = new In(synsets);
        synsetsList = new ArrayList<Synset>();

        while (!synsetsIn.isEmpty()) {
            String entry = synsetsIn.readString();
            synsetsList.add(new Synset(entry));
        }
    }

    /**
     * Returns all WordNet nouns.
     *
     * @return all WordNet nouns
     */
    public Iterable<String> nouns() {
        throw new UnsupportedOperationException();
    }

    /**
     * Is the word a WordNet noun.
     *
     * @param word word to search
     * @return true if noun
     */
    public boolean isNoun(String word) {
        if (word == null) {
            throw new NullPointerException();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Distance between nounA and nounB (defined below).
     *
     * @param nounA param 1
     * @param nounB param 1
     * @return distance between nounA and nounB.
     */
    public int distance(String nounA, String nounB) {

        if (nounA == null || nounB == null) {
            throw new NullPointerException();
        }

        throw new UnsupportedOperationException();
    }

    /**
     * A synset (second field of synsets.txt) that is the common ancestor of nounA and nounB.
     * in a shortest ancestral path (defined below)
     *
     * @param nounA param 1
     * @param nounB param 1
     * @return common ancestor of nounA and nounB.
     */
    public String sap(String nounA, String nounB) {

        if (nounA == null || nounB == null) {
            throw new NullPointerException();
        }

        throw new UnsupportedOperationException();
    }

    private class Synset {
        private int id;
        private String sysnset;
        private String glossary;

        private Synset(String unparsedSysnet) {
            String[] split = unparsedSysnet.split(",");
            id = Integer.parseInt(split[0]);
            sysnset = split[1];
            glossary = split[2];
        }
    }

    /**
     * Main.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

    }
}
