package ai.clarity.poc.performance.matrices.ejml;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.RandomMatrices_DDRM;
import org.ejml.dense.row.SpecializedOps_DDRM;

import java.security.SecureRandom;
import java.util.Random;

public class EJMLVectorGenerator {

    private static Random random = new SecureRandom();

    public static DMatrixRMaj generateVector(int size) {
        return RandomMatrices_DDRM.rectangle(1, size, random);
    }


    public static DMatrixRMaj generateMatrix(int rows, int cols) {

        return RandomMatrices_DDRM.rectangle(rows, cols, random);
    }


    public static String printVector(DMatrixRMaj vector) {

        return printMatrix(vector);
    }


    public static String printMatrix(DMatrixRMaj matrix) {

        StringBuilder sb = new StringBuilder();

        for(int row = 0; row < matrix.getNumRows(); row++) {
            sb.append("{");
            for(int col = 0; col < matrix.getNumCols(); col++) {
                sb.append(matrix.get(row, col) + " ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("}");
            sb.append("\n");
        }

        return sb.toString();
    }


    public static double[][] toArray(DMatrixRMaj matrix) {
        DMatrixRMaj[] rows = SpecializedOps_DDRM.splitIntoVectors(matrix, false);

        double[][] resp = new double[matrix.getNumRows()][matrix.getNumCols()];

        for(int rowIndex = 0; rowIndex < 0; rowIndex++) {
            resp[rowIndex] = rows[rowIndex].getData();
        }

        return resp;
    }


    private EJMLVectorGenerator() {}

}
