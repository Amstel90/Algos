import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by ASUS on 16.04.2015.
 */
public class WordNet {

    private Map<String, List<Integer>> synsetMap;
    private Map<Integer, List<String>> synsetMapId;
    private SAP sap;

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

        parse(synsets, hypernyms);
    }

    /**
     * Returns all WordNet nouns.
     *
     * @return all WordNet nouns
     */
    public Iterable<String> nouns() {
        return this.synsetMap.keySet();
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
        return this.synsetMap.containsKey(word);
    }

    /**
     * Distance between nounA and nounB (defined below).
     *
     * @param nounA param 1
     * @param nounB param 1
     * @return distance between nounA and nounB.
     */
    public int distance(String nounA, String nounB) {

        validate(nounA, nounB);

        return sap.length(this.synsetMap.get(nounA), this.synsetMap.get(nounB));
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

        validate(nounA, nounB);

        int ancestor = sap.ancestor(this.synsetMap.get(nounA), this.synsetMap.get(nounB));

        return sap(ancestor);
    }

    private String sap(int ancestor) {

        List<String> bag = this.synsetMapId.get(ancestor);
        StringBuilder sb = new StringBuilder();
        for (String noun : bag) {
            sb.append(noun + " ");
        }
        return sb.toString();
    }


    /**
     * A synset (second field of synsets.txt) that is the common ancestor of nounA and nounB.
     * in a shortest ancestral path (defined below)
     *
     * @param nounA param 1
     * @param nounB param 1
     * @return common ancestor of nounA and nounB.
     */
    private void validate(String nounA, String nounB) {

        if (nounA == null || nounB == null) {
            throw new NullPointerException();
        }

        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Constructor takes the name of the two input files.
     *
     * @param synsets   file 1
     * @param hypernyms file 2
     */
    private void parse(String synsets, String hypernyms) {

        synsetMap = new HashMap<String, List<Integer>>();
        synsetMapId = new HashMap<Integer, List<String>>();

        In synsetsIn = new In(synsets);
        int nouns = 0;

        while (!synsetsIn.isEmpty()) {
            parseSynSet(synsetsIn.readLine());
            nouns++;
        }

        synsetsIn.close();

        Digraph digraph = new Digraph(nouns);

        In hypernymIn = new In(hypernyms);

        while (!hypernymIn.isEmpty()) {
            parseHypernym(digraph, hypernymIn.readLine());
        }

        hypernymIn.close();

        DirectedCycle cycle = new DirectedCycle(digraph);

        if (cycle.hasCycle()) {
            throw new IllegalArgumentException();
        }

        int rooted = 0;
        for (int i = 0; i < digraph.V(); i++) {
            if (!digraph.adj(i).iterator().hasNext())
                rooted++;
        }

        if (rooted != 1) {
            throw new IllegalArgumentException();
        }

        sap = new SAP(digraph);
    }

    /**
     * Constructor takes the name of the two input files.
     *
     * @param entry file 1
     */
    private void parseSynSet(String entry) {

        String[] split = entry.split(",");

        int id = Integer.parseInt(split[0]);

        String[] split2 = split[1].split(" ");

        List<String> synList = new LinkedList<String>();


        for (String syn : split2) {

            List<Integer> bag = synsetMap.get(syn);

            if (bag == null) {
                bag = new LinkedList<Integer>();
                synsetMap.put(syn, bag);
            }

            bag.add(id);

            synList.add(syn);
        }

        synsetMapId.put(id, synList);
    }

    /**
     * Constructor takes the name of the two input files.
     *
     * @param d     d
     * @param entry file 1
     */
    private void parseHypernym(Digraph d, String entry) {

        String[] split = entry.split(",");

        int id = Integer.parseInt(split[0]);

        for (int i = 1; i < split.length; i++) {
            d.addEdge(id, Integer.parseInt(split[i]));
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
