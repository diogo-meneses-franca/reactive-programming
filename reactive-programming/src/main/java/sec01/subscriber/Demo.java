package sec01.subscriber;

import sec01.publisher.PublisherImpl;

import java.time.Duration;

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		demo1();
		demo2();
		demo3();
	}

	private static void demo1() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(4);
		Thread.sleep(Duration.ofSeconds(2));
		subscriber.getSubscription().request(4);
		Thread.sleep(Duration.ofSeconds(2));
		subscriber.getSubscription().request(4);
		Thread.sleep(Duration.ofSeconds(2));
		subscriber.getSubscription().request(4);
		Thread.sleep(Duration.ofSeconds(2));
	}

	private static void demo2() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(4);
		Thread.sleep(Duration.ofSeconds(2));
		subscriber.getSubscription().cancel();
		Thread.sleep(Duration.ofSeconds(2));
		subscriber.getSubscription().request(4);
		Thread.sleep(Duration.ofSeconds(2));
	}

	private static void demo3() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(11);
	}
}
