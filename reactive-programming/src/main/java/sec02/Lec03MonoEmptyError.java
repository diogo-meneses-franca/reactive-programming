package sec02;

import common.Util;
import reactor.core.publisher.Mono;

public class Lec03MonoEmptyError {

	public static void main(String[] args) {
		getUserName(1).subscribe(Util.subscriber());
		getUserName(2).subscribe(Util.subscriber());
		getUserName(3).subscribe(Util.subscriber());
	}

	private static Mono<String> getUserName(int userId){
		return switch (userId){
			case 1 -> Mono.just("John");
			case 2 -> Mono.empty();
			default -> Mono.error(new RuntimeException("Invalid input"));
		};
	}
}
