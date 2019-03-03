package hadadzhi;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import hadadzhi.passgen.PassphraseGenerator;

public class PassgenBenchmark {
	private static final int SIZE = 1024;
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
			.include(PassgenBenchmark.class.getSimpleName())
			.threads(Runtime.getRuntime().availableProcessors())
			.forks(1)
			.warmupIterations(10)
			.measurementIterations(10)
			.build();
		
		new Runner(opt).run();
	}
	
	@Benchmark
	public String base64() {
		return PassphraseGenerator.genBase64(SIZE);
	}
	
	@Benchmark
	public String charset() {
		return PassphraseGenerator.genCharList(SIZE);
	}
}
