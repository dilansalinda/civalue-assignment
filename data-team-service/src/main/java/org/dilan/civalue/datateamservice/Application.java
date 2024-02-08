package org.dilan.civalue.datateamservice;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootApplication

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RestController
	@RequestMapping("/api/v1")
	class DataService {

		@GetMapping("/products")
		public List getProductList() throws IOException {
			InputStream inputStream = new ClassPathResource("products.json").getInputStream();
			Gson gson = new Gson();
			String jsonString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			return gson.fromJson(jsonString, List.class);

		}

		@GetMapping("/shoppers")
		public List getShopperProductList() throws IOException {
			InputStream inputStream = new ClassPathResource("shelves.json").getInputStream();
			Gson gson = new Gson();
			String jsonString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			return gson.fromJson(jsonString, List.class);

		}

	}


}
