/**
 * Created by ASUS on 16.04.2015.
 */
public class Outcast {

    private WordNet wordNet;

    /**
     * Constructor takes a WordNet object.
     *
     * @param wordNet wordNet
     */
    public Outcast(WordNet wordNet) {
        this.wordNet = wordNet;
    }

    /**
     * Given an array of WordNet nouns, return an outcast.
     *
     * @param nouns array of nouns
     * @return all WordNet nouns
     */
    public String outcast(String[] nouns) {
        String outcast = null;
        int max = 0;

        for (int i = 0; i < nouns.length; i++) {
            int distance = 0;
            for (int j = 0; j < nouns.length; j++) {
                if (i == j) {
                    continue;
                }
                distance += this.wordNet.distance(nouns[i], nouns[j]);
            }

            if (distance > max) {
                max = distance;
                outcast = nouns[i];
            }
        }

        return outcast;
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
