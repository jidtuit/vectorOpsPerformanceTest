package ai.clarity.poc.performance.matrices.test;

import ai.clarity.poc.performance.matrices.colt.ColtVectorOps;
import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix2D;
import org.junit.jupiter.api.Test;

import static ai.clarity.poc.performance.matrices.test.TestUtils.assertEqualsWithError;

public class ColtVectorOpsTest {

    private double v1[] = TestUtils.v1;
    private double v2[] = TestUtils.v2;

    private double expected[] = TestUtils.expected;

    private double error = TestUtils.error;

    @Test
    void sum_OK() {

        double result[] = ColtVectorOps.sum(v1, v2);

        assertEqualsWithError(expected, result, error);
    }


    @Test
    void sumMatrix_native_OK() {

        double matrix[][] = new double[2][v1.length];
        matrix[0] = v1;
        matrix[1] = v2;

        double result[] = ColtVectorOps.sumRowsMatrix(matrix);

        assertEqualsWithError(expected, result, error);
    }


    @Test
    void sumMatrix_native_alternative_OK() {

        double matrix[][] = new double[2][v1.length];
        matrix[0] = v1;
        matrix[1] = v2;

        double result[] = ColtVectorOps.sumRowsMatrixAlternative(matrix);

        assertEqualsWithError(expected, result, error);
    }


    @Test
    void sumMatrix_ColtObjects_OK() {

        double matrix[][] = new double[2][v1.length];
        matrix[0] = v1;
        matrix[1] = v2;

        DoubleMatrix2D coltObj = DoubleFactory2D.dense.make(matrix);

        double result[] = ColtVectorOps.sumRowsMatrix(coltObj);

        assertEqualsWithError(expected, result, error);
    }

}
