import org.junit.Assert;
import org.junit.Test;

public class MatrixTest {

    @Test
    public void testEqualSqareMatrices() {
        Matrix a = new Matrix(2, 2, 5);
        Matrix b = new Matrix(2, 2, 5);
        Assert.assertTrue(a.equals(b));
    }

    @Test
    public void testEqual5x9Matrixes() {
        Matrix a = new Matrix(5, 9, 2);
        Matrix b = new Matrix(5, 9, 2);
        Assert.assertTrue(a.equals(b));
    }

    @Test
    public void testMultiplication() {
        Matrix a = new Matrix(2, 3);
        double[] aValues = {1, 2, 3, 4, -2, 1};
        a.setValues(aValues);

        Matrix b = new Matrix(3, 2);
        double[] bValues = {0, 3, 1, 3, 2, 1};
        b.setValues(bValues);

        Matrix r = a.multiply(b);
        Matrix expected = new Matrix(2, 2);
        double[] result = {8, 12, 0, 7};
        expected.setValues(result);
        Assert.assertTrue(expected.equals(r));
    }

    @Test
    public void testMultiplicationOfSquareMatrices() {
        Matrix a = new Matrix(2, 2);
        double[] aValues = {1, 2, 3, 4};
        a.setValues(aValues);

        Matrix b = new Matrix(2, 2);
        double[] bValues = {0, 3, 1, 3};
        b.setValues(bValues);

        Matrix r = a.multiply(b);
        Matrix expected = new Matrix(2, 2);
        double[] result1 = {2, 9, 4, 21};
        expected.setValues(result1);
        Assert.assertEquals(expected, r);

        r = b.multiply(a);
        expected = new Matrix(2, 2);
        double[] result2 = {9, 12, 10, 14};
        expected.setValues(result2);
        Assert.assertEquals(expected, r);
    }

    @Test
    public void testNativeMultiplication() {
        Matrix b = new Matrix(2, 2, 2);
        Matrix a = new Matrix(2, 2, 2);
        Matrix result = a.multiplyNative(b);
        Matrix expected = new Matrix(2, 2, 8);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testPowerMatrix() {
        Matrix a = new Matrix(2, 2, 2);
        Matrix r = a.power(4);
        Matrix expected = new Matrix(2, 2, 128);
        Assert.assertEquals(expected, r);
    }

    @Test
    public void testNativePower() {
        Matrix a = new Matrix(2, 2, 2);
        Matrix r = a.powerNative(4);
        Matrix expected = new Matrix(2, 2, 128);
        Assert.assertEquals(expected, r);
    }

}
