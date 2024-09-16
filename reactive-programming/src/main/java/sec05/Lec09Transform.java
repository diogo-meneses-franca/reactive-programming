package sec05;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Lec09Transform {

	private static final Logger log = LoggerFactory.getLogger(Lec09Transform.class);

	record Customer(int id, String name){}
	record PurchaseOrder(String productName, int price, int quantity){}

	public static void main(String[] args) {

		var isDebugEnabled = true;

		/*
		Nos dois trechos de código abaixo o operador transform() foi utilizado para
		evitar a repetição de código como se fosse um operador customizado"
		A explicação sobre o método addDebugger encontra-se no final do código
		Function.identity() significa que deve ser retornado o valor como ele é,
		nesse caso somente quando a condição for falsa.
		 */
		getCustomers()
				.transform((isDebugEnabled ? addDebugger() : Function.identity()))
				.subscribe(Util.subscriber());

		getPurchaseOrders()
				.transform(addDebugger())
				.subscribe(Util.subscriber());
	}

	private static Flux<Customer> getCustomers(){

		return Flux.range(1, 4)
				.map(i -> new Customer(i, Util.faker().name().firstName()));
	}

	private static Flux<PurchaseOrder> getPurchaseOrders(){
		return Flux.range(1, 6)
				.map(i -> new PurchaseOrder(Util.faker().commerce().productName(), i, i * 10));
	}

	//Explicação no final do código
	private static <T> UnaryOperator<Flux<T>> addDebugger(){
		return flux -> flux
				.doOnNext(i -> log.info("received {}", i))
				.doOnComplete(() -> log.info("completed"))
				.doOnError(err -> log.error("error", err));
	}
}

/**
 * O que é UnaryOperator?
 *
 * O UnaryOperator faz parte da biblioteca Java e é uma interface funcional que
 * estende Function<T, T>. Ou seja, é uma função que aceita um argumento de um
 * tipo e retorna um valor do mesmo tipo.
 *
 * Em termos mais simples:
 * - Um UnaryOperator<T> é uma função que transforma algo do tipo T e retorna
 *   um novo valor também do tipo T.
 *
 * Aplicação no código:
 *
 * No método `addDebugger`, o UnaryOperator<Flux<T>> indica que essa função
 * recebe um Flux<T> e devolve um Flux<T> transformado. O tipo T é genérico,
 * então pode ser qualquer tipo de dado dentro do Flux (pode ser um Integer,
 * String, ou qualquer outro tipo).
 *
 * - Entrada: Um objeto do tipo Flux<T>.
 * - Saída: O mesmo objeto Flux<T>, mas com a adição dos operadores de log.
 *
 * Por que usar UnaryOperator aqui?
 *
 * Ao usar UnaryOperator<Flux<T>>, estamos dizendo que a função vai aplicar uma
 * operação sobre um Flux e devolver outro Flux, que pode ser o mesmo fluxo ou
 * uma versão modificada. Isso é útil quando queremos aplicar transformações,
 * como adicionar operadores de depuração, sem mudar o tipo do fluxo em si.
 *
 * Diferença entre UnaryOperator e Function:
 *
 * Se usássemos Function<Flux<T>, Flux<T>> no lugar de UnaryOperator<Flux<T>>,
 * o comportamento seria o mesmo. A diferença é que o UnaryOperator é uma
 * especialização do Function, simplificando o código quando o tipo de entrada
 * e saída são iguais.
 *
 * Em resumo, o UnaryOperator<Flux<T>> é uma maneira de expressar que estamos
 * aplicando uma transformação ou modificação em um Flux<T>, retornando o mesmo
 * tipo de Flux<T> no final, sem alterar o tipo do fluxo.
 */
