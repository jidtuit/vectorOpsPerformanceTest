package ai.clarity.poc.performance.matrices.colt;

import cern.colt.matrix.DoubleFactory1D;
import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;

import java.security.SecureRandom;
import java.util.Random;

public class ColtVectorGenerator {

    private static Random random = new SecureRandom();

    public static DoubleMatrix1D generateVector(int size) {
        return DoubleFactory1D.dense
                .make(size)
                .assign(cellValue -> random.nextDouble());
    }


    public static DoubleMatrix2D generateMatrix(int rows, int cols) {
        return DoubleFactory2D.dense
                .make(rows, cols)
                .assign(cellValue -> random.nextDouble());
    }


    public static String printVector(DoubleMatrix1D vector) {

        StringBuilder sb = new StringBuilder("{");

        for(int i = 0; i < vector.size(); i++) {
            sb.append( vector.getQuick(i) + " ");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");

        return sb.toString();
    }


    public static String printMatrix(DoubleMatrix2D matrix) {

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < matrix.rows(); i++) {
            DoubleMatrix1D row = matrix.viewRow(i);
            sb.append(printVector(row));
            sb.append("\n");
        }

        return sb.toString();
    }


    private ColtVectorGenerator() {}

}
