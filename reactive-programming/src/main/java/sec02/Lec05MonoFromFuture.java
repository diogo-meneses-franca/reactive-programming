package sec02;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec05MonoFromFuture {

	private static final Logger logger = LoggerFactory.getLogger(Lec05MonoFromFuture.class);

	public static void main(String[] args) {

		Mono.fromFuture(Lec05MonoFromFuture::getName).subscribe(Util.subscriber());
		Util.sleepSeconds(1);

	}
	/*
	CompletableFuture.supplyAsync(...) é usado para criar uma nova tarefa assíncrona que executa
	 o código fornecido em um thread separado. No seu caso, essa tarefa gera um nome
	  falso usando Util.faker().name().firstName().
	A chamada logger.info("generating name"); é usada para registrar uma mensagem informativa
	 quando a tarefa é executada.
	O método getName() retorna um CompletableFuture<String>,
	 que representa a tarefa assíncrona que, eventualmente, resultará em uma string (o nome gerado).
	 */
	private static CompletableFuture<String> getName(){
		return CompletableFuture.supplyAsync(() ->{
			logger.info("generating name");
			return Util.faker().name().firstName();
		});
	}
}
