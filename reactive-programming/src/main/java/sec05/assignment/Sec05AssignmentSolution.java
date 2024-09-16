package sec05.assignment;

import common.Util;

public class Sec05AssignmentSolution {

	public static void main(String[] args) {

		ExternalProductService service = new ExternalProductService();
		for (int i = 1; i < 5; i++){
			service.getProductName(i)
					.subscribe(Util.subscriber());
		}

		Util.sleepSeconds(5);
	}
}
