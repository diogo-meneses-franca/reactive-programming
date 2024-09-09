package sec04;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import sec04.helper.NameGenerator;

import java.util.ArrayList;

public class Lec03FluxSinkThreadSafety {

	/*
	FluxSink is thread safe!
	 */
	private static final Logger log = LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);

	public static void main(String[] args) {

		demo2();

	}

	public static void demo1() {
		var list = new ArrayList<Integer>();
		Runnable runnable = () -> {
			for (int i = 0; i < 1000; i++) {
				list.add(i);
			}
		};

		for (int i = 0; i < 10; i++) {
			Thread.ofPlatform().start(runnable);
		}
		Util.sleepSeconds(5);
		log.info("list size: {}", list.size());
	}

	private static void demo2() {
		var list = new ArrayList<String>();
		var generator = new NameGenerator();
		var flux = Flux.create(generator);
		flux.subscribe(list::add);
		Runnable runnable = () -> {
			for (int i = 0; i < 1000; i++) {
				generator.generateNames();
			}
		};

		for (int i = 0; i < 10; i++) {
			Thread.ofPlatform().start(runnable);
		}
		Util.sleepSeconds(5);
		log.info("list size: {}", list.size());
	}

}
