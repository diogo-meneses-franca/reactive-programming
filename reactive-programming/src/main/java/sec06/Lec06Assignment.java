package sec06;

import common.Util;
import sec06.assignment.ExternalServiceClient;
import sec06.assignment.InventoryService;
import sec06.assignment.RevenueService;

/*
Para rodar o código é necessario que o arquivo external-service.jar esteja em execução
o arquivo está na pasta src/main/java/sec02/lec07ExternalServices
 */

public class Lec06Assignment {

	public static void main(String[] args) {

		var client = new ExternalServiceClient();
		var inventoryService = new InventoryService();
		var revenueService = new RevenueService();

		client.orderStream().subscribe(inventoryService::consume);
		client.orderStream().subscribe(revenueService::consume);

		inventoryService.stream().subscribe(Util.subscriber());
		revenueService.stream().subscribe(Util.subscriber());

		Util.sleepSeconds(30);


	}

}
