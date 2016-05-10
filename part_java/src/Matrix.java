public class Matrix {
    private double[] values;
    private int columns;
    private int rows;

    static {
        System.loadLibrary("Matrix");
    }

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        values = new double[rows * columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                values[i * columns + j] = Math.random();
            }
        }
    }

    public Matrix(int rows, int columns, double value) {
        this.rows = rows;
        this.columns = columns;

        values = new double[rows * columns];
        for (int i = 0; i < values.length; i++) {
            values[i] = value;
        }
    }

    public Matrix multiplyNative(Matrix other) {
        if (columns != other.rows) {
            throw new IllegalArgumentException("Inner dimensions have to be equal!");
        }
        Matrix result = new Matrix(rows, other.columns);
        double[] resultValues = new double[rows * other.columns];
        multiplyC(values, other.values, resultValues, rows, other.columns, columns);
        result.values = resultValues;
        return result;
    }

    private native void multiplyC(double[] a, double[] b, double[] r, int i, int j, int k);

    public Matrix power(int k) {
        Matrix powered = new Matrix(rows, columns);
        powered.setValues(values);
        for (int i = 0; i < k - 1; i++) {
            powered = powered.multiply(this);
        }
        return powered;
    }

    public Matrix powerNative(int k) {
        Matrix result = new Matrix(rows, columns);
        double[] resultValues = new double[rows * columns];
        powerC(values, resultValues, rows, k);
        result.setValues(resultValues);
        return result;
    }

    private native void powerC(double[] a, double[] r, int dimension, int k);


    /**
     * Returns a new Matrix representing this Matrix
     * multiplied with the given other Matrix.
     * <p>
     * 1   2
     * 7   8   9                       (7*1 + 8*3 + 9*5)     (7*2 + 8*4 + 9*6)
     * x    3   4   =
     * 10  11  12                      (10*1 + 11*3 + 12*5)  (10*2 + 11*4 + 12*6)
     * 5   6
     */
    public Matrix multiply(Matrix other) {
        if (columns != other.rows) {
            throw new IllegalArgumentException("Inner dimensions have to be the same!");
        }
        Matrix result = new Matrix(rows, other.columns);
        double[] resultValues = new double[rows * other.columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                resultValues[i * other.columns + j] = getCellResult(i, other, j);
            }
        }
        result.values = resultValues;
        return result;
    }

    /**
     * Sums the multiplication of the values of the row of this matrix
     * with the values of the column of the given matrix. The result represents
     * the value of a single cell in a matrix multiplication.
     *
     * @param row
     * @return
     */
    private double getCellResult(int row, Matrix other, int column) {
        double result = 0;
        for (int i = 0; i < columns; i++) {
            double aFactor = values[row * columns + i];
            double bFactor = other.values[i * other.columns + column];
            result += aFactor * bFactor;
        }
        return result;
    }

    public boolean equals(Object object) {
        if (object != null && object.getClass().isAssignableFrom(Matrix.class)) {
            Matrix other = (Matrix) object;
            if (values != null && other.values != null
                    && values.length == other.values.length) {
                if (columns == other.columns && rows == other.rows) {
                    int i = 0;
                    while (i < values.length && values[i] == other.values[i]) {
                        i++;
                    }
                    return i == values.length;
                }
            }
        }
        return false;
    }

    public void print() {
        for (int i = 0; i < values.length; i++) {
            if (i % columns == 0) {
                System.out.println("");
            }
            System.out.print(values[i] + "\t");
        }
    }

    public void setValues(double[] values) {
        this.values = values;
    }
}
