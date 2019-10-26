package ai.clarity.poc.performance.matrices.ejml;

import cern.colt.matrix.DoubleMatrix2D;
import org.ejml.data.DMatrixRMaj;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

import static ai.clarity.poc.performance.matrices.ejml.EJMLVectorGenerator.generateMatrix;
import static ai.clarity.poc.performance.matrices.ejml.EJMLVectorGenerator.generateVector;
import static ai.clarity.poc.performance.matrices.ejml.EJMLVectorGenerator.toArray;

//@BenchmarkMode({Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 7, time = 10, timeUnit = TimeUnit.SECONDS)
public class EJMLVectorBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        double[] v1;
        double[] v2;

        double[][] matrix;

        DMatrixRMaj v1Andv2;
        DMatrixRMaj matrixObject;

        private final int rows = 100000;
        private final int cols = 269;

        @Setup(Level.Trial)
        public void setupState() {
            v1 = generateVector(rows).getData();
            v2 = generateVector(rows).getData();
            matrix = toArray(generateMatrix(rows, cols));

            v1Andv2 = generateMatrix(2, cols);
            matrixObject = generateMatrix(rows, cols);
        }
    }


    @Benchmark
    @Fork(value = 1) // defaultJVM
    public void ejmlVector_Sum_JVM_default(MyState state, Blackhole bh) {

        double[] sum = EJMLVectorOps.sum(state.v1, state.v2);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    //@Benchmark
    @Fork(value = 1, jvmArgsAppend = "-XX:-UseSuperWord")
    public void ejmlVector_Sum_JVM_WithoutSIMD(MyState state, Blackhole bh) {

        double[] sum = EJMLVectorOps.sum(state.v1, state.v2);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    @Benchmark
    @Fork(value = 1) // defaultJVM
    public void ejmlVector_Sum_UsingEJMLObjects_JVM_default(MyState state, Blackhole bh) {

        double[] sum = EJMLVectorOps.sumRowsMatrix(state.v1Andv2);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    //@Benchmark
    @Fork(value = 1, jvmArgsAppend = "-XX:-UseSuperWord")
    public void ejmlVector_Sum_UsingEJMLObjects_JVM_WithoutSIMD(MyState state, Blackhole bh) {

        double[] sum = EJMLVectorOps.sumRowsMatrix(state.v1Andv2);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }


    @Benchmark
    @Fork(value = 1) // defaultJVM
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void ejmlVector_MatrixSum_JVM_default(MyState state, Blackhole bh) {

        double[] sum = EJMLVectorOps.sumRowsMatrix(state.matrix);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    //@Benchmark
    @Fork(value = 1, jvmArgsAppend = "-XX:-UseSuperWord")
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void ejmlVector_MatrixSum_JVM_WithoutSIMD(MyState state, Blackhole bh) {

        double[] sum = EJMLVectorOps.sumRowsMatrix(state.matrix);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    @Benchmark
    @Fork(value = 1) // defaultJVM
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void ejmlVector_MatrixSum_UsingEJMLObjects_JVM_default(MyState state, Blackhole bh) {

        double[] sum = EJMLVectorOps.sumRowsMatrix(state.matrixObject);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    //@Benchmark
    @Fork(value = 1, jvmArgsAppend = "-XX:-UseSuperWord")
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void ejmlVector_MatrixSum_UsingEJMLObjects_JVM_WithoutSIMD(MyState state, Blackhole bh) {

        double[] sum = EJMLVectorOps.sumRowsMatrix(state.matrixObject);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }


    @Benchmark
    @Fork(value = 1) // defaultJVM
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void ejmlVector_MatrixSum_alternative_JVM_default(MyState state, Blackhole bh) {

        double[] sum = EJMLVectorOps.sumRowsMatrixAlternative(state.matrix);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    //@Benchmark
    @Fork(value = 1, jvmArgsAppend = "-XX:-UseSuperWord")
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void ejmlVector_MatrixSum_alternative_JVM_WithoutSIMD(MyState state, Blackhole bh) {

        double[] sum = EJMLVectorOps.sumRowsMatrixAlternative(state.matrix);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

}
