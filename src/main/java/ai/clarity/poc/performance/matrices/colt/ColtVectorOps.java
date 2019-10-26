package ai.clarity.poc.performance.matrices.colt;

import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;

import java.util.Arrays;

public class ColtVectorOps {

    public static double[] sum(double[] v1, double[] v2) {

        double[][] matrixParam = new double[2][v1.length];
        matrixParam[0] = v1;
        matrixParam[1] = v2;

        return sumRowsMatrix(matrixParam);
    }


    public static double[] sumRowsMatrix(double[][] matrixParam) {

        int vSize = matrixParam[0].length;
        double[] resp = new double[vSize];

        DoubleMatrix2D matrix = DoubleFactory2D.dense
                .make(matrixParam);

        DoubleMatrix1D column;
        for(int col = 0; col < vSize; col++) {

            column = matrix.viewColumn(col);
            resp[col] = column.zSum();
        }

        return resp;
    }


    public static double[] sumRowsMatrix(DoubleMatrix2D matrix) {

        int vSize = matrix.columns();
        double[] resp = new double[vSize];

        for(int col = 0; col < vSize; col++) {
            resp[col] = matrix.viewColumn(col).zSum();
        }

        return resp;
    }


    public static double[] sumRowsMatrixAlternative(double[][] matrixParam) {

        int vSize = matrixParam[0].length;
        double[] resp = matrixParam[0];

        DoubleMatrix2D matrix = DoubleFactory2D.dense
                .make(matrixParam);

        for(int row = 1; row < matrixParam.length; row++) {
            for (int col = 0; col < vSize; col++) {
                resp[col] += matrix.getQuick(row, col);
            }
        }
        return resp;
    }


    private ColtVectorOps() {}

}
