package sec05;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleUntilAssignment {

	public static void main(String[] args) {

		Flux.<String>generate(sync -> sync.next(Util.faker().country().name()))
				.handle((country, sink) ->{
					sink.next(country);
					if(country.equalsIgnoreCase("brazil")){
						sink.complete();
					}
				}).subscribe(Util.subscriber());
	}
}
