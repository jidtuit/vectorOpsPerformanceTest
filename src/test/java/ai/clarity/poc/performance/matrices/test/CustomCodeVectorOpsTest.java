package ai.clarity.poc.performance.matrices.test;

import ai.clarity.poc.performance.matrices.customcode.CustomCodeVectorOps;
import org.junit.jupiter.api.Test;

import static ai.clarity.poc.performance.matrices.test.TestUtils.assertEqualsWithError;


public class CustomCodeVectorOpsTest {

    private double v1[] = TestUtils.v1;
    private double v2[] = TestUtils.v2;

    private double expected[] = TestUtils.expected;

    private double error = TestUtils.error;

    @Test
    void sum_OK() {

        double result[] = CustomCodeVectorOps.sum(v1, v2);

        assertEqualsWithError(expected, result, error);
    }


    @Test
    void sumMatrix_OK() {

        double matrix[][] = new double[2][v1.length];
        matrix[0] = v1;
        matrix[1] = v2;

        double result[] = CustomCodeVectorOps.sumRowsMatrix(matrix);

        assertEqualsWithError(expected, result, error);
    }

}
