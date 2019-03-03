package hadadzhi;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
public class ConstantFoldingBenchmark {
	private double pi = Math.PI;
	private final double finalPi = Math.PI;
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(ConstantFoldingBenchmark.class.getSimpleName())
				.threads(1)
				.warmupIterations(10)
				.measurementIterations(10)
				.forks(1)
				.build();
		
		new Runner(opt).run();
	}
	
	@Benchmark
	public double folded() {
		return Math.exp(finalPi);
	}
	
	@Benchmark
	public double unfolded() {
		return Math.exp(pi);
	}
}
