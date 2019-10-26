package ai.clarity.poc.performance.matrices.customcode;

public class CustomCodeVectorOps {

    public static double[] sum(double[] v1, double[] v2) {

        double result[] = new double[v1.length];

        for(int i = 0; i < v1.length; i++) {
            result[i] = v1[i] + v2[i];
        }

        return result;
    }


    public static double[] sumRowsMatrix(double matrix[][]) {

        double resp[] = matrix[0];

        for(int row = 1; row < matrix.length; row++) {
            resp = sum(resp, matrix[row]);
        }

        return resp;
    }


    public static double[] sumRowsMatrixAlternative(double matrix[][]) {

        double resp[] = matrix[0];

        for(int row = 1; row < matrix.length; row++) {
            for(int col = 0; col < resp.length; col++) {
                resp[col] += matrix[row][col];
            }
        }

        return resp;
    }


    private CustomCodeVectorOps() {}

}
