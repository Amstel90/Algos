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

        this.digraph = new Digraph(G);
    }

    /**
     * Length of shortest ancestral path between v and w; -1 if no such path.
     *
     * @param v param 1
     * @param w param 1
     * @return length of shortest ancestral path between v and w; -1 if no such path
     */
    public int length(int v, int w) {

        validateVertexes(v, w);

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        Queue<Integer> vertexes = new Queue<Integer>();
        vertexes.enqueue(v);
        vertexes.enqueue(w);

        return length(bfsV, bfsW, vertexes);
    }

    /**
     * A common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path.
     *
     * @param v param 1
     * @param w param 2
     * @return common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     */
    public int ancestor(int v, int w) {

        validateVertexes(v, w);

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        Queue<Integer> vertexes = new Queue<Integer>();
        vertexes.enqueue(v);
        vertexes.enqueue(w);

        return ancestor(bfsV, bfsW, vertexes);
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

        Queue<Integer> vertexes = new Queue<Integer>();

        for (int i : v) {
            validateVertex(i);
            vertexes.enqueue(i);
        }

        for (int i : w) {
            validateVertex(i);
            vertexes.enqueue(i);
        }

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        return length(bfsV, bfsW, vertexes);
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

        Queue<Integer> vertexes = new Queue<Integer>();

        for (int i : v) {
            validateVertex(i);
            vertexes.enqueue(i);
        }

        for (int i : w) {
            validateVertex(i);
            vertexes.enqueue(i);
        }

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        return ancestor(bfsV, bfsW, vertexes);
    }

    /**
     * Length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path.
     *
     * @param bfsV     param 1
     * @param bfsW     param 2
     * @param vertexes param 2
     * @return length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     */
    private int ancestor(BreadthFirstDirectedPaths bfsV, BreadthFirstDirectedPaths bfsW, Queue<Integer> vertexes) {
        int lca = -1;
        int dist = Integer.MAX_VALUE;
        boolean[] visited = new boolean[digraph.V()];

        while (vertexes.size() != 0) {

            int vertex = vertexes.dequeue();

            if (visited[vertex]) {
                continue;
            }

            if (bfsV.hasPathTo(vertex)
                    && bfsW.hasPathTo(vertex)) {

                int tmpDist = bfsV.distTo(vertex) + bfsW.distTo(vertex);

                if (tmpDist < dist) {
                    dist = tmpDist;
                    lca = vertex;
                }
            }

            visited[vertex] = true;

            for (int i : digraph.adj(vertex)) {
                vertexes.enqueue(i);
            }
        }
        return lca;
    }

    /**
     * Length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path.
     *
     * @param bfsV     param 1
     * @param bfsW     param 2
     * @param vertexes param 2
     * @return length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     */
    private int length(BreadthFirstDirectedPaths bfsV, BreadthFirstDirectedPaths bfsW, Queue<Integer> vertexes) {

        int lca = ancestor(bfsV, bfsW, vertexes);

        if (lca == -1) {
            return -1;
        }

        return bfsV.distTo(lca) + bfsW.distTo(lca);
    }

    /**
     * validateVertex.
     *
     * @param v command line arguments
     * @param w command line arguments
     */
    private void validateVertexes(int v, int w) {

        validateVertex(v);
        validateVertex(w);
    }

    /**
     * validateVertex.
     *
     * @param v command line arguments
     */
    private void validateVertex(int v) {

        if (v < 0 || v >= digraph.V()) {
            throw new IndexOutOfBoundsException();
        }
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
