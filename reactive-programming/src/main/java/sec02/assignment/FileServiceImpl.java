package sec02.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService{

	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	private static final Path PATH = Path.of("src/main/resources/sec02");

	@Override
	public Mono<String> read(String fileName) {
		return Mono.fromCallable(() -> Files.readString(PATH.resolve(fileName)))
				.onErrorReturn("Erro ao ler arquivo!");
	}

	@Override
	public Mono<Void> write(String fileName, String content) {
		return Mono.fromRunnable(() -> this.writeFile(fileName, content));
	}

	@Override
	public Mono<Void> delete(String fileName) {
		return Mono.fromRunnable(() -> this.deleteFile(fileName));
	}

	private void writeFile(String fileName, String content){
		try {
			Files.writeString(PATH.resolve(fileName), content);
		} catch (IOException e) {
			logger.info("created {}", fileName);
			throw new RuntimeException(e);
		}
	}

	private void deleteFile(String fileName){
		try {
			Files.delete(PATH.resolve(fileName));
		} catch (Exception e) {
			logger.info("deleted {}", fileName);
			throw new RuntimeException(e);
		}
	}
}
