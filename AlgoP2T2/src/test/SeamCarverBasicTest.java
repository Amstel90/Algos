import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Artem_Mikhalevitch on 5/15/15.
 */
public class SeamCarverBasicTest {

    String pictureName = "3x7.png";
    SeamCarver seamCarver;
    Picture picture;

    {
        picture = new Picture(Folder.PATH + pictureName);
        seamCarver = new SeamCarver(picture);
    }

    @Test
    public void testEqualPicture() {
        Assert.assertEquals(picture, seamCarver.picture());
        Assert.assertEquals(picture.width(), seamCarver.width());
        Assert.assertEquals(picture.height(), seamCarver.height());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testEnergy0() {
        seamCarver.energy(picture.width() - 1, picture.height() - 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEnergy1() {
        seamCarver.energy(picture.width() - 1, picture.height());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEnergy2() {
        seamCarver.energy(picture.width(), picture.height() - 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        seamCarver.removeHorizontalSeam(new int[]{1, 2, 3});
    }

    @Test(expected = NullPointerException.class)
    public void testRemove1() {
        seamCarver.removeHorizontalSeam(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemove2() {
        seamCarver.removeVerticalSeam(null);
    }
}
