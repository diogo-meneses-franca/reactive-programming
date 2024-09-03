package sec02;

import common.Util;
import sec02.assignment.FileServiceImpl;

public class Lec09Assignment {

	public static void main(String[] args) {
		var fileService = new FileServiceImpl();
		fileService.write("teste.txt", "Este é um arquivo de teste").subscribe(Util.subscriber());
		fileService.read("teste.txt").subscribe(Util.subscriber());
		fileService.delete("teste.txt").subscribe(Util.subscriber());
	}
}
