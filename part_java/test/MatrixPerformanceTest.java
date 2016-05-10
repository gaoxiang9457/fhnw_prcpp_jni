import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class MatrixPerformanceTest {

    @Test
    public void measureMultiplicationPerformance() {
        Matrix a = new Matrix(400, 6000);
        Matrix b = new Matrix(6000, 300);
        long beforeJava = Calendar.getInstance().getTimeInMillis();
        Matrix javaResult = a.multiply(b);
        long afterJava = Calendar.getInstance().getTimeInMillis();
        Matrix cppResult = a.multiplyNative(b);
        long afterCpp = Calendar.getInstance().getTimeInMillis();
        System.out.println("java took\t" + (afterJava - beforeJava)  + "  millis");
        System.out.println("cpp took\t" + (afterCpp - afterJava) + " millis");

        Assert.assertEquals(javaResult, cppResult);
    }
    @Test
    public void measurePowerPerformance() {
        Matrix a = new Matrix(200, 200);
        long beforeJava = Calendar.getInstance().getTimeInMillis();
        Matrix javaResult = a.power(51);
        long afterJava = Calendar.getInstance().getTimeInMillis();
        Matrix cppResult = a.powerNative(51);
        long afterCpp = Calendar.getInstance().getTimeInMillis();
        System.out.println("java took\t" + (afterJava - beforeJava)  + "  millis");
        System.out.println("cpp took\t" + (afterCpp - afterJava) + " millis");

        Assert.assertEquals(javaResult, cppResult);
    }

}
