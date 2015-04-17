/**
 * Created by ASUS on 16.04.2015.
 */
public class Outcast {

    private WordNet wordNet;

    /**
     * Constructor takes a WordNet object.
     *
     * @param wordnet wordnet
     */
    public Outcast(WordNet wordnet) {
        this.wordNet = wordNet;
    }

    /**
     * Given an array of WordNet nouns, return an outcast.
     *
     * @param nouns array of nouns
     * @return all WordNet nouns
     */
    public String outcast(String[] nouns) {
        throw new UnsupportedOperationException();
    }

    /**
     * Main.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
