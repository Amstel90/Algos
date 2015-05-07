import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ASUS on 24.04.2015.
 */
public class SAPTest {

    public static final String folder = "D:\\Projects\\Algos\\AlgoP2T1\\src\\resources\\";

    Digraph twoVertex = new Digraph(2);
    SAP twoVertexSap;

    {
        twoVertex.addEdge(0, 1);
        twoVertexSap = new SAP(twoVertex);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void length1() {
        twoVertexSap.length(0, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void length2() {
        twoVertexSap.length(2, 0);
    }

    @Test
    public void length3() {

        SAP sap = new SAP(getDigraphByFileName("digraph1.txt"));

        Assert.assertEquals(3, sap.length(9, 12));
    }

    @Test
    public void length4() {

        SAP sap = new SAP(getDigraphByFileName("digraph2.txt"));

        Assert.assertEquals(2, sap.length(1, 5));
    }

    @Test
    public void ancestor1() {

        SAP sap = new SAP(getDigraphByFileName("digraph1.txt"));

        Assert.assertEquals(1, sap.ancestor(3, 11));
    }

    @Test
    public void ancestor2() {

        SAP sap = new SAP(getDigraphByFileName("digraph2.txt"));

        Assert.assertEquals(0, sap.ancestor(1, 5));
    }

    private Digraph getDigraphByFileName(String fileName) {

        In in = new In(folder + fileName);
        return new Digraph(in);
    }

}
