package sec05.assignment;

import common.AbstractHttpClient;
import common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;


public class ExternalProductService extends AbstractHttpClient {


	public Mono<String> getProductName(int productId){
		var defaultPath = "/demo03/product/" + productId;
		var timeoutPath = "/demo03/timeout-fallback/product/" + productId;
		var emptyPath = "/demo03/empty-fallback/product/" + productId;
		return getProductName(defaultPath)
				.timeout(Duration.ofSeconds(2), getProductName(timeoutPath))
				.switchIfEmpty(getProductName(emptyPath));

	}

	public Mono<String> getProductName(String path){
		return this.httpClient.get()
				.uri(path)
				.responseContent()
				.asString()
				.next();
	}

}
