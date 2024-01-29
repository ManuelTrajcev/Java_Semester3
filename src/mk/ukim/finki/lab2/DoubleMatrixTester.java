package mk.ukim.finki.lab2;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Stream;

public class DoubleMatrixTester {

    public static class InsufficientElementsException extends Exception {
        public InsufficientElementsException() {
            super("Insufficient number of elements");
        }
    }


    public static class InvalidRowNumberException extends Exception {
        public InvalidRowNumberException() {
            super("Invalid row number");
        }
    }

    public static class InvalidColumnNumberException extends Exception {
        public InvalidColumnNumberException() {
            super("Invalid column number");
        }
    }

    public static class DoubleMatrix {
        private final double[] a;
        private final double[][] matrix;
        private final int m;
        private final int n;

        public DoubleMatrix(double[] a, int m, int n) throws InsufficientElementsException {
            if (a.length < m * n) {
                throw new InsufficientElementsException();
            }

            this.a = new double[a.length];
            int c = 0;
            for (int i = 0; i < a.length; i++) {
                this.a[i] = a[i];
            }

            this.matrix = new double[m][n];
            if (a.length > m * n) {
                c = a.length - m * n;
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = a[c++];
                }
            }
            this.m = m;
            this.n = n;
        }

        public String getDimensions() {
            return String.format("[%d x %d]", m, n);
        }

        public int rows() {
            return m;
        }

        public int columns() {
            return n;
        }

        public double maxElementAtRow(int row) throws InvalidRowNumberException {
            if (row < 1 || row > m) {
                throw new InvalidRowNumberException();
            }
            row -= 1;
            double max = matrix[row][0];
            for (int i = 0; i < n; i++) {
                if (matrix[row][i] > max) {
                    max = matrix[row][i];
                }
            }
            return max;
        }

        public double maxElementAtColumn(int column) throws InvalidColumnNumberException {
            if (column < 1 || column > n) {
                throw new InvalidColumnNumberException();
            }
            column -= 1;
            double max = matrix[0][column];
            for (int i = 0; i < m; i++) {
                if (matrix[i][column] > max) {
                    max = matrix[i][column];
                }
            }
            return max;
        }

        public double sum() {
            double sum = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sum += matrix[i][j];
                }
            }
            return sum;
        }

        double[] toSortedArray() {
            Double[] arr = new Double[m * n];
            int c = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    arr[c++] = matrix[i][j];
                }
            }
            Arrays.sort(arr, Collections.reverseOrder());
            double[] sorted = new double[arr.length];
            for (int i = 0; i < arr.length; i++) {
                sorted[i] = arr[i].doubleValue();
            }
            return sorted;

        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < m; i++) {
                stringBuilder.append(String.format("%.2f", matrix[i][0]));
                for (int j = 1; j < n; j++) {
                    stringBuilder.append("\t").append(String.format("%.2f", matrix[i][j]));
                }
                if (i + 1 != m)
                    stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DoubleMatrix that = (DoubleMatrix) o;
            if (m != that.m || n != that.n) return false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] != that.matrix[i][j]) return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(m, n);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    result = 31 * result + Double.hashCode(matrix[i][j]);
                }
            }
            return result;
        }
    }

    public static class MatrixReader {

        public MatrixReader() {
        }

        public static DoubleMatrix read(InputStream input) throws InsufficientElementsException {
            Scanner scanner = new Scanner(input);
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            double[] arr = new double[m * n];
            for (int i = 0; i < m * n; i++) {
                arr[i] = scanner.nextDouble();
            }
            try {
                DoubleMatrix doubleMatrix = new DoubleMatrix(arr, m, n);
            } catch (InsufficientElementsException exception) {
                exception.getMessage();
            }
            DoubleMatrix doubleMatrix = new DoubleMatrix(arr, m, n);

            return doubleMatrix;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        DoubleMatrix fm = null;

        double[] info = null;

        DecimalFormat format = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            String operation = scanner.next();

            switch (operation) {
                case "READ": {
                    int N = scanner.nextInt();
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    double[] f = new double[N];

                    for (int i = 0; i < f.length; i++)
                        f[i] = scanner.nextDouble();

                    try {
                        fm = new DoubleMatrix(f, R, C);
                        info = Arrays.copyOf(f, f.length);

                    } catch (InsufficientElementsException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }

                    break;
                }

                case "INPUT_TEST": {
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    StringBuilder sb = new StringBuilder();

                    sb.append(R + " " + C + "\n");

                    scanner.nextLine();

                    for (int i = 0; i < R; i++)
                        sb.append(scanner.nextLine() + "\n");

                    fm = MatrixReader.read(new ByteArrayInputStream(sb
                            .toString().getBytes()));

                    info = new double[R * C];
                    Scanner tempScanner = new Scanner(new ByteArrayInputStream(sb
                            .toString().getBytes()));
                    tempScanner.nextDouble();
                    tempScanner.nextDouble();
                    for (int z = 0; z < R * C; z++) {
                        info[z] = tempScanner.nextDouble();
                    }

                    tempScanner.close();

                    break;
                }

                case "PRINT": {
                    System.out.println(fm.toString());
                    break;
                }

                case "DIMENSION": {
                    System.out.println("Dimensions: " + fm.getDimensions());
                    break;
                }

                case "COUNT_ROWS": {
                    System.out.println("Rows: " + fm.rows());
                    break;
                }

                case "COUNT_COLUMNS": {
                    System.out.println("Columns: " + fm.columns());
                    break;
                }

                case "MAX_IN_ROW": {
                    int row = scanner.nextInt();
                    try {
                        System.out.println("Max in row: "
                                + format.format(fm.maxElementAtRow(row)));
                    } catch (InvalidRowNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "MAX_IN_COLUMN": {
                    int col = scanner.nextInt();
                    try {
                        System.out.println("Max in column: "
                                + format.format(fm.maxElementAtColumn(col)));
                    } catch (InvalidColumnNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "SUM": {
                    System.out.println("Sum: " + format.format(fm.sum()));
                    break;
                }

                case "CHECK_EQUALS": {
                    int val = scanner.nextInt();

                    int maxOps = val % 7;

                    for (int z = 0; z < maxOps; z++) {
                        double work[] = Arrays.copyOf(info, info.length);

                        int e1 = (31 * z + 7 * val + 3 * maxOps) % info.length;
                        int e2 = (17 * z + 3 * val + 7 * maxOps) % info.length;

                        if (e1 > e2) {
                            double temp = work[e1];
                            work[e1] = work[e2];
                            work[e2] = temp;
                        }

                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(work, fm.rows(),
                                fm.columns());
                        System.out
                                .println("Equals check 1: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode() && f1
                                        .equals(f2)));
                    }

                    if (maxOps % 2 == 0) {
                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(new double[]{3.0, 5.0,
                                7.5}, 1, 1);

                        System.out
                                .println("Equals check 2: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode() && f1
                                        .equals(f2)));
                    }

                    break;
                }

                case "SORTED_ARRAY": {
                    double[] arr = fm.toSortedArray();

                    String arrayString = "[";

                    if (arr.length > 0)
                        arrayString += format.format(arr[0]) + "";

                    for (int i = 1; i < arr.length; i++)
                        arrayString += ", " + format.format(arr[i]);

                    arrayString += "]";

                    System.out.println("Sorted array: " + arrayString);
                    break;
                }
            }
        }
        scanner.close();
    }
}