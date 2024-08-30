package sec02;

import common.Util;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec04FromSuplier {

	/*
	No caso abaixo foi passada uma lista para ser processada e somada no método sum.
	Toda vez que for passado um item para ser processado deve-se utilizar o método fromSuplier.
	Nesse caso o método just não é adequado pois o item ainda não foi produzido e o método just deve
	ser utilizado apenas para dados que estão prontos em memória.
	Assim como reactive programming suporta fromSupplier também suporta fromCallable, a diferença
	é que o fromSupplier não irá lançar uma exceção, enquanto que o Callable tem uma exceção como parte
	da sua assinatura.
	 */
	public static void main(String[] args) {
		List<Integer> list = List.of(1,2,3);
		Mono.fromSupplier(() -> sum(list)).subscribe(Util.subscriber());
	}

	private static int sum(List<Integer> list){
		return list.stream().mapToInt(a -> a).sum();
	}
}
