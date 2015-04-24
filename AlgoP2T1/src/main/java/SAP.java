/**
 * Created by ASUS on 16.04.2015.
 */
public class SAP {

    /**
     * Digraph
     */
    private Digraph digraph;

    /**
     * Constructor takes a digraph (not necessarily a DAG).
     *
     * @param G Digraph
     */
    public SAP(Digraph G) {

        if (G == null) {
            throw new NullPointerException();
        }

        this.digraph = G;
    }

    /**
     * Length of shortest ancestral path between v and w; -1 if no such path.
     *
     * @param v param 1
     * @param w param 1
     * @return length of shortest ancestral path between v and w; -1 if no such path
     */
    public int length(int v, int w) {

        if (!isValidVertex(v) || !isValidVertex(w)) {
            throw new IndexOutOfBoundsException();
        }

        throw new UnsupportedOperationException();
    }

    /**
     * A common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path.
     *
     * @param v param 1
     * @param w param 2
     * @return common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     */
    public int ancestor(int v, int w) {

        if (!isValidVertex(v) || !isValidVertex(w)) {
            throw new IndexOutOfBoundsException();
        }

        throw new UnsupportedOperationException();
    }

    /**
     * Length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path.
     *
     * @param v param 1
     * @param w param 2
     * @return length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        if (v == null || w == null) {
            throw new NullPointerException();
        }

        throw new UnsupportedOperationException();
    }

    /**
     * A common ancestor that participates in shortest ancestral path; -1 if no such path.
     *
     * @param v param 1
     * @param w param 1
     * @return A common ancestor that participates in shortest ancestral path; -1 if no such path
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {

        if (v == null || w == null) {
            throw new NullPointerException();
        }

        throw new UnsupportedOperationException();
    }

    /**
     * validateVertex.
     *
     * @param vertex command line arguments
     * @return if vertex is valid
     */
    private boolean isValidVertex(int vertex) {
        return vertex > 0 && vertex <= digraph.V();
    }

    /**
     * Main.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
