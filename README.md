# Vector operation benchmarks

## 1. Generate jar
`./gradlew clean jmhJar`

## 2. Command-line options
Display jmh command-line options: 

    java -jar java -jar build/libs/vectorOpsPerformanceTest-0.1.0-SNAPSHOT-all.jar -h 

## 3. Run the benchmarks
``
List jmh result formats report

    java -jar java -jar build/libs/vectorOpsPerformanceTest-0.1.0-SNAPSHOT-all.jar -lrf
    
Run benchmarks and generate a csv report file

    java -jar java -jar build/libs/vectorOpsPerformanceTest-0.1.0-SNAPSHOT-all.jar -rf csv

Run benchmarks and generate a csv report file with a name (-rf is optional in this case because it defaults to CSV)

    java -jar java -jar build/libs/vectorOpsPerformanceTest-0.1.0-SNAPSHOT-all.jar -rf csv -rff myBenchmarkJMHReport.csv
    
List the benchmarks that satisfies the regular expression

    java -jar build/libs/vectorOpsPerformanceTest-0.1.0-SNAPSHOT-all.jar -lp '.*Benchmark.*_Sum_*
      
Run the benchmarks that satisfies the regular expression. No `-option needed.

    java -jar build/libs/vectorOpsPerformanceTest-0.1.0-SNAPSHOT-all.jar '.*Benchmark.*_Sum_*
    
Run benchmarks forcing full gc after each iteration (`-gc true`) and override some parms like warmup (`-w`) and iteration (`-r`) durations

    java -jar build/libs/vectorOpsPerformanceTest-0.1.0-SNAPSHOT-all.jar '.*EJML.*Benchmark.*' -w 10s -r 10s -gc true
      
Note: You can combine all the different options together

 
## 4. Run benchmarks with a profiler

List jmh profilers options

    java -jar java -jar build/libs/vectorOpsPerformanceTest-0.1.0-SNAPSHOT-all.jar -lprof
    
Run benchmarks and use a profiler

    java -jar java -jar build/libs/vectorOpsPerformanceTest-0.1.0-SNAPSHOT-all.jar -prof <profiler>
