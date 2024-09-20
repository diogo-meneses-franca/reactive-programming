package sec06.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

// Revenue é a receita total gerada por uma empresa com a venda de seus produtos
// ou serviços antes da dedução de quaisquer custos ou despesas. Refere-se ao
// valor bruto de dinheiro proveniente das operações da empresa.

public class RevenueService implements OrderProcessor{

	private final Map<String, Integer> db = new HashMap<>();

	@Override
	public void consume(Order order) {
		var currentRevenue = db.getOrDefault(order.category(), 0);
		var updateRevenue = currentRevenue + order.price();
		db.put(order.category(), updateRevenue);
	}

	@Override
	public Flux<String> stream() {
		return Flux.interval(Duration.ofSeconds(2))
				.map(i -> this.db.toString());
	}
}
