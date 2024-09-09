package sec04;

import common.Util;
import sec04.assignment.FileReaderService;
import sec04.assignment.FileReaderServiceImpl;

import java.nio.file.Path;

public class Lec09Assignment {

	public static void main(String[] args) {

		FileReaderService service = new FileReaderServiceImpl();
		Path path = Path.of("src/main/resources/sec04/file.txt");
		service.read(path)
				.take(4)
				.subscribe(Util.subscriber());
	}
}
