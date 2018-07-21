package cn.shiyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ArticleProvider8002_App {
	public static void main(String[] args) {
		SpringApplication.run(ArticleProvider8002_App.class, args);
	}
}
