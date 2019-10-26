package ai.clarity.poc.performance.matrices.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestUtils {

    final static double v1[] = {1.1, 2.2, 3.3};
    final static double v2[] = {2.1, 2.2, 2.3};

    final static double expected[] = {3.2, 4.4, 5.6};

    final static double error = 0.000001;

    static void assertEqualsWithError(float expected[], float result[], float error) {
        for(int i = 0; i < result.length; i++) {
            assertTrue(expected[i] - result[i] < error);
        }
    }


    static void assertEqualsWithError(double expected[], double result[], double error) {
        for(int i = 0; i < result.length; i++) {
            assertTrue(expected[i] - result[i] < error);
        }
    }

    private TestUtils() {}
}
