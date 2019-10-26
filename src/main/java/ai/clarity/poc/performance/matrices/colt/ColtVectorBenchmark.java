package ai.clarity.poc.performance.matrices.colt;

import cern.colt.matrix.DoubleMatrix2D;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

//@BenchmarkMode({Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 7, time = 10, timeUnit = TimeUnit.SECONDS)
public class ColtVectorBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        double[] v1;
        double[] v2;

        double[][] matrix;

        DoubleMatrix2D v1Andv2;
        DoubleMatrix2D matrixObject;

        private final int rows = 100000;
        private final int cols = 269;

        @Setup(Level.Trial)
        public void setupState() {
            v1 = ColtVectorGenerator.generateVector(rows).toArray();
            v2 = ColtVectorGenerator.generateVector(rows).toArray();
            matrix = ColtVectorGenerator.generateMatrix(rows, cols).toArray();

            v1Andv2 = ColtVectorGenerator.generateMatrix(2, cols);
            matrixObject = ColtVectorGenerator.generateMatrix(rows, cols);
        }
    }


    @Benchmark
    @Fork(value = 1) // defaultJVM
    public void coltVector_Sum_JVM_default(MyState state, Blackhole bh) {

        double[] sum = ColtVectorOps.sum(state.v1, state.v2);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    //@Benchmark
    @Fork(value = 1, jvmArgsAppend = "-XX:-UseSuperWord")
    public void coltVector_Sum_JVM_WithoutSIMD(MyState state, Blackhole bh) {

        double[] sum = ColtVectorOps.sum(state.v1, state.v2);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    @Benchmark
    @Fork(value = 1) // defaultJVM
    public void coltVector_Sum_UsingColtObjects_JVM_default(MyState state, Blackhole bh) {

        double[] sum = ColtVectorOps.sumRowsMatrix(state.v1Andv2);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    //@Benchmark
    @Fork(value = 1, jvmArgsAppend = "-XX:-UseSuperWord")
    public void coltVector_Sum_UsingColtObjects_JVM_WithoutSIMD(MyState state, Blackhole bh) {

        double[] sum = ColtVectorOps.sumRowsMatrix(state.v1Andv2);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }


    @Benchmark
    @Fork(value = 1) // defaultJVM
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void coltVector_MatrixSum_JVM_default(MyState state, Blackhole bh) {

        double[] sum = ColtVectorOps.sumRowsMatrix(state.matrix);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    //@Benchmark
    @Fork(value = 1, jvmArgsAppend = "-XX:-UseSuperWord")
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void coltVector_MatrixSum_JVM_WithoutSIMD(MyState state, Blackhole bh) {

        double[] sum = ColtVectorOps.sumRowsMatrix(state.matrix);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    @Benchmark
    @Fork(value = 1) // defaultJVM
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void coltVector_MatrixSum_UsingColtObjects_JVM_default(MyState state, Blackhole bh) {

        double[] sum = ColtVectorOps.sumRowsMatrix(state.matrixObject);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    //@Benchmark
    @Fork(value = 1, jvmArgsAppend = "-XX:-UseSuperWord")
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void coltVector_MatrixSum_UsingColtObjects_JVM_WithoutSIMD(MyState state, Blackhole bh) {

        double[] sum = ColtVectorOps.sumRowsMatrix(state.matrixObject);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }


    @Benchmark
    @Fork(value = 1) // defaultJVM
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void coltVector_MatrixSum_alternative_JVM_default(MyState state, Blackhole bh) {

        double[] sum = ColtVectorOps.sumRowsMatrixAlternative(state.matrix);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    //@Benchmark
    @Fork(value = 1, jvmArgsAppend = "-XX:-UseSuperWord")
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void coltVector_MatrixSum_alternative_JVM_WithoutSIMD(MyState state, Blackhole bh) {

        double[] sum = ColtVectorOps.sumRowsMatrixAlternative(state.matrix);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }


}
