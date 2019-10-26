package ai.clarity.poc.performance.matrices.customcode;

import java.security.SecureRandom;
import java.util.Random;

public class CustomCodeVectorGenerator {

    private static Random random = new SecureRandom();

    public static double[] generateVector(int size) {
        double resp[] = new double[size];

        for(int i = 0; i < size; i++) {
            resp[i] = random.nextFloat();
        }

        return resp;
    }


    public static double[][] generateMatrix(int numberOfLines, int size) {

        double resp[][] = new double[numberOfLines][size];

        for(int i = 0; i < numberOfLines; i++) {
            resp[i] = generateVector(size);
        }

        return resp;
    }


    public static String printVector(double v[]) {

        StringBuilder sb = new StringBuilder("{");

        for(double f : v) {
            sb.append(f+" ");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");

        return sb.toString();
    }


    public static String printMatrix(double m[][]) {

        StringBuilder sb = new StringBuilder();

        for(double row[] : m) {
            sb.append(printVector(row));
            sb.append("\n");
        }

        return sb.toString();
    }


    private CustomCodeVectorGenerator() {}

}
