package chenrujun.web.applcation;

import com.azure.storage.blob.BlobClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@SpringBootApplication
@RestController
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    BlobClient blobClient;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostMapping("/uppercase")
    public String uppercase(@RequestBody String string) {
        return string.toUpperCase();
    }

    @GetMapping(value = "/supplier")
    public String supplier() {
        String suppliedValue = "This is a string supplied by supplier. Date = " + new Date();
        LOGGER.info("supplier executed. suppliedValue = {}", suppliedValue);
        return suppliedValue;
    }

    @PostMapping("/consumer")
    public void consumer(@RequestBody String string) {
        LOGGER.info("consumer executed. string = {}", string);
    }

    @GetMapping("/readBlob")
    public String readBlob() {
        String blobContent = blobClient.downloadContent().toString();
        LOGGER.info("readBlob executed. blobContent = {}", blobContent);
        return blobContent;
    }

    @PostMapping("/writeBlob")
    public void writeBlob(@RequestBody String string) {
        LOGGER.info("writeBlob executed. string = {}", string);
        InputStream dataStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
        blobClient.upload(dataStream, string.length(), true);
    }
}