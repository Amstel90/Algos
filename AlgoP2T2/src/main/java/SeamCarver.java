/**
 * Created by Artem_Mikhalevitch on 5/15/15.
 */
public class SeamCarver {
    /**
     * Given image.
     */
    private Picture picture;

    /**
     * Constructor. Create a seam carver object based on the given picture.
     *
     * @param source picture
     */
    public SeamCarver(Picture source) {
        this.picture = source;
    }

    /**
     * Current picture.
     *
     * @return current picture
     */
    public Picture picture() {
        return picture;
    }

    /**
     * Width of current picture.
     *
     * @return width of current picture
     */
    public int width() {
        return picture.width();
    }

    /**
     * Height of current picture.
     *
     * @return height of current picture
     */
    public int height() {
        return picture.height();
    }

    /**
     * Energy of pixel at column x and row y.
     *
     * @return energy of pixel at column x and row y
     */
    public double energy(int x, int y) {

        validate(x, y);

        throw new UnsupportedOperationException();
    }

    /**
     * Sequence of indices for horizontal seam.
     *
     * @return sequence of indices for horizontal seam
     */
    public int[] findHorizontalSeam() {
        throw new UnsupportedOperationException();
    }

    /**
     * Sequence of indices for vertical seam.
     *
     * @return sequence of indices for vertical seam.
     */
    public int[] findVerticalSeam() {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove horizontal seam from current picture/
     */
    public void removeHorizontalSeam(int[] seam) {

        this.validate(seam);

        throw new UnsupportedOperationException();
    }

    /**
     * Remove vertical seam from current picture/
     */
    public void removeVerticalSeam(int[] seam) {

        this.validate(seam);

        throw new UnsupportedOperationException();
    }

    /**
     * Checks for null.
     *
     * @throws NullPointerException
     */
    private void validate(int[] seam) {

        if (seam == null) {
            throw new NullPointerException();
        }

        //todo validate for IllegalArgumentException
    }

    /**
     * Checks for valid coordinates.
     *
     * @throws NullPointerException
     */
    private void validate(int x, int y) {

        if (x >= picture.width() || y >= picture.height()) {
            throw new IndexOutOfBoundsException();
        }
    }
}
