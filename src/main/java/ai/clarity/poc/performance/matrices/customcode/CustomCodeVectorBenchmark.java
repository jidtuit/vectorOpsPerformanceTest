package ai.clarity.poc.performance.matrices.customcode;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

import static ai.clarity.poc.performance.matrices.customcode.CustomCodeVectorGenerator.generateMatrix;
import static ai.clarity.poc.performance.matrices.customcode.CustomCodeVectorGenerator.generateVector;

//@BenchmarkMode({Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 7, time = 10, timeUnit = TimeUnit.SECONDS)
public class CustomCodeVectorBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        public double v1[];
        public double v2[];

        public double matrix[][];

        private final int rows = 100000;
        private final int cols = 269;

        @Setup(Level.Trial)
        public void setupState() {
            v1 = generateVector(rows);
            v2 = generateVector(rows);
            matrix = generateMatrix(rows, cols);
        }
    }


    @Benchmark
    @Fork(value = 1) // defaultJVM
    public void customCodeVector_Sum_JVM_default(MyState state, Blackhole bh) {

        double sum[] = CustomCodeVectorOps.sum(state.v1, state.v2);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    //@Benchmark
    @Fork(value = 1, jvmArgsAppend = "-XX:-UseSuperWord")
    public void customCodeVector_Sum_JVM_WithoutSIMD(MyState state, Blackhole bh) {

        double sum[] = CustomCodeVectorOps.sum(state.v1, state.v2);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }


    @Benchmark
    @Fork(value = 1) // defaultJVM
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void customCodeVector_MatrixSum_JVM_default(MyState state, Blackhole bh) {

        double sum[] = CustomCodeVectorOps.sumRowsMatrix(state.matrix);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    //@Benchmark
    @Fork(value = 1, jvmArgsAppend = "-XX:-UseSuperWord")
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void customCodeVector_MatrixSum_JVM_WithoutSIMD(MyState state, Blackhole bh) {

        double sum[] = CustomCodeVectorOps.sumRowsMatrix(state.matrix);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }


    @Benchmark
    @Fork(value = 1) // defaultJVM
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void customCodeVector_MatrixSum_alternative_JVM_default(MyState state, Blackhole bh) {

        double sum[] = CustomCodeVectorOps.sumRowsMatrixAlternative(state.matrix);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }

    //@Benchmark
    @Fork(value = 1, jvmArgsAppend = "-XX:-UseSuperWord")
    @Warmup(iterations = 3, time = 30, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 7, time = 30, timeUnit = TimeUnit.SECONDS)
    public void customCodeVector_MatrixSum_alternative_JVM_WithoutSIMD(MyState state, Blackhole bh) {

        double sum[] = CustomCodeVectorOps.sumRowsMatrixAlternative(state.matrix);

        // Blackhole is needed to avoid JVM detecting this code as dead code and apply optimizations
        bh.consume(sum);
    }



}
