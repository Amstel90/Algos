import org.junit.Test;

/**
 * Created by Artem_Mikhalevitch on 5/15/15.
 */
public class SeamCarverFuncTest {

    String pictureName = "3x7.png";

    @Test
    public void test() {

        SeamCarver seamCarver = getSeamCarver(pictureName);

    }

    private SeamCarver getSeamCarver(String name) {
        return new SeamCarver(new Picture(Folder.PATH + pictureName));
    }
}
