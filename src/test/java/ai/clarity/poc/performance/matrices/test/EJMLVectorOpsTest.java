package ai.clarity.poc.performance.matrices.test;

import ai.clarity.poc.performance.matrices.ejml.EJMLVectorOps;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Test;

import static ai.clarity.poc.performance.matrices.test.TestUtils.assertEqualsWithError;

public class EJMLVectorOpsTest {

    private double v1[] = TestUtils.v1;
    private double v2[] = TestUtils.v2;

    private double expected[] = TestUtils.expected;

    private double error = TestUtils.error;

    @Test
    void sum_OK() {

        double result[] = EJMLVectorOps.sum(v1, v2);

        assertEqualsWithError(expected, result, error);
    }


    @Test
    void sumMatrix_native_OK() {

        double matrix[][] = new double[2][v1.length];
        matrix[0] = v1;
        matrix[1] = v2;

        double result[] = EJMLVectorOps.sumRowsMatrix(matrix);

        assertEqualsWithError(expected, result, error);
    }


    @Test
    void sumMatrix_alternative_OK() {

        double matrix[][] = new double[2][v1.length];
        matrix[0] = v1;
        matrix[1] = v2;

        double result[] = EJMLVectorOps.sumRowsMatrixAlternative(matrix);

        assertEqualsWithError(expected, result, error);
    }


    @Test
    void sumMatrix_EJMLObjects_OK() {

        double matrix[][] = new double[2][v1.length];
        matrix[0] = v1;
        matrix[1] = v2;

        DMatrixRMaj ejmlMatrix = new DMatrixRMaj(matrix);

        double result[] = EJMLVectorOps.sumRowsMatrix(ejmlMatrix);

        assertEqualsWithError(expected, result, error);
    }

}
