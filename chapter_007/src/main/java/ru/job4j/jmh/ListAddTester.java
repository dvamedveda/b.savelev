package ru.job4j.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.All)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 10)
@Measurement(iterations = 20)
public class ListAddTester {

    private List<Integer> arrayList;
    private List<Integer> linkedList;

    @Benchmark
    public void testAddingTenMillionToArray(Blackhole blackhole) {
        arrayList = new ArrayList<Integer>();
        for (int i = 0; i < 10_000_000; i++) {
            arrayList.add(i);
        }
        blackhole.consume(arrayList);
    }

    @Benchmark
    public void testAddingTenMillionToLinked(Blackhole blackhole) {
        linkedList = new LinkedList<Integer>();
        for (int i = 0; i < 10_000_000; i++) {
            linkedList.add(i);
        }
        blackhole.consume(linkedList);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(ListAddTester.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}