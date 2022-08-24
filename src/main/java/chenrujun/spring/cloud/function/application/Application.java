package chenrujun.spring.cloud.function.application;

import com.azure.storage.blob.BlobClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    BlobClient blobClient;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Function<String, String> uppercase() {
        return (String string) -> {
            LOGGER.info("uppercase function executed. string = {}", string);
            return string.toUpperCase();
        };
    }

    @Bean
    public Supplier<String> supplier() {
        return () -> {
            String suppliedValue = "This is a string supplied by supplier. Date = " + new Date();
            LOGGER.info("supplier executed. suppliedValue = {}", suppliedValue);
            return suppliedValue;
        };
    }

    @Bean
    public Consumer<String> consumer() {
        return (String string) -> LOGGER.info("consumer executed. string = {}", string);
    }

    @Bean
    public Supplier<String> readBlob() {
        return () -> {
            String blobContent = blobClient.downloadContent().toString();
            LOGGER.info("readBlob executed. blobContent = {}", blobContent);
            return blobContent;
        };
    }

    @Bean
    public Consumer<String> writeBlob() {
        return (String string) -> {
            LOGGER.info("writeBlob executed. string = {}", string);
            InputStream dataStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
            blobClient.upload(dataStream, string.length(), true);
        };
    }
}