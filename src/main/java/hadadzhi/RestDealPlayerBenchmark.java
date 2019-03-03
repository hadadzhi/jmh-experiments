package hadadzhi;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.cdfe.deal.game.DealPlayer;
import ru.cdfe.deal.game.DealStrategy;
import ru.cdfe.deal.game.InMemoryDealPlayer;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class RestDealPlayerBenchmark {
//	private final DealPlayer player = new RestDealPlayer(DealStrategy.SMART, URI.create("http://localhost:8080/deal"));
	private final DealPlayer player = new InMemoryDealPlayer(DealStrategy.SMART);
//	private final AtomicLong wins = new AtomicLong(0);
//	private final AtomicLong runs = new AtomicLong(0);
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
			.include(RestDealPlayerBenchmark.class.getSimpleName())
			.threads(8)
			.forks(1)
			.warmupIterations(15)
			.measurementIterations(15)
			.build();
		
		new Runner(opt).run();
	}
	
	@Benchmark
	public void deal() {
		try {
			if (player.play()) {
//				wins.incrementAndGet();
			}
//			runs.incrementAndGet();
		} catch (Exception e) {
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}
