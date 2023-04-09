package benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;

import java.io.IOException;

@State(Scope.Benchmark)
@Threads(1)
public class Day1BenchmarkTest {
    @Benchmark
    public void day1part1() {
        day01.Day1Kt.day1part1();
    }

    @Benchmark
    public void day1part1_() {
        day01.Day1Kt.day1part1_();
    }

    @Benchmark
    public void day1part2() {
        day01.Day1Kt.day1part2();
    }

    @Benchmark
    public void day1part2_() {
        day01.Day1Kt.day1part2_();
    }

    //    @Test
    public void benchmark() throws IOException {
        String[] argv = {};
        org.openjdk.jmh.Main.main(argv);
    }
}
