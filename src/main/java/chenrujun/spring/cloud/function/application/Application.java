package chenrujun.spring.cloud.function.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Function<Flux<String>, Flux<String>> uppercase() {
        return flux -> flux.map(String::toUpperCase);
    }

    @Bean
    public Supplier<String> supplier() {
        return () -> "test";
    }

    @Bean
    public Consumer<String> consumer() {
        return System.out::println;
    }
}