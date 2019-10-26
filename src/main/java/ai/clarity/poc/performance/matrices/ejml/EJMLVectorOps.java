package ai.clarity.poc.performance.matrices.ejml;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;

public class EJMLVectorOps {

    public static double[] sum(double[] v1, double[] v2) {

        double[][] matrixParam = new double[2][v1.length];
        matrixParam[0] = v1;
        matrixParam[1] = v2;

        return sumRowsMatrix(matrixParam);
    }


    public static double[] sumRowsMatrix(double[][] matrixParam) {

        DMatrixRMaj matrix = new DMatrixRMaj(matrixParam);
        return sumRowsMatrix(matrix);
    }


    public static double[] sumRowsMatrix(DMatrixRMaj matrix) {
        return CommonOps_DDRM.sumCols(matrix, null)
                            .getData();
    }


    public static double[] sumRowsMatrixAlternative(double[][] matrixParam) {

        DMatrixRMaj matrix = new DMatrixRMaj(matrixParam);

        double[] resp = matrixParam[0];

        for(int row = 1; row < matrix.getNumRows(); row++) {
            for(int col = 0; col < matrix.getNumCols(); col++) {
                resp[col] += matrix.unsafe_get(row, col);
            }
        }

        return resp;
    }


    private EJMLVectorOps() {}

}
