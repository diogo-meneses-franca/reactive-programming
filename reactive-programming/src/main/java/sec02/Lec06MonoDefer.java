package sec02;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06MonoDefer {

	private static final Logger logger = LoggerFactory.getLogger(Lec06MonoDefer.class);

	public static void main(String[] args) {

	// Mono.defer() adia a criação do Mono até o momento da inscrição (subscribe).
	// Isso é útil para garantir que a lógica de criação do Mono seja executada
	// apenas quando necessário, evitando a execução antecipada de operações custosas.

		Mono.defer(Lec06MonoDefer::createPublisher).subscribe(Util.subscriber());
	}

	private static Mono<Integer> createPublisher(){
		logger.info("Creating publisher...");
		var list = List.of(1, 2, 3);
		Util.sleepSeconds(1);
		return Mono.fromSupplier(() -> sum(list));
	}

	private static int sum(List<Integer> list){
		logger.info("Finding the sum of {}", list);
		Util.sleepSeconds(3);
		return list.stream().mapToInt(i -> i).sum();
	}
}
