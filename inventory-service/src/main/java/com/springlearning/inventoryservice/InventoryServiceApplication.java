package com.springlearning.inventoryservice;

import com.springlearning.inventoryservice.model.Inventory;
import com.springlearning.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory1 = Inventory.builder()
					.skuCode("Oneplus 6t")
					.quantity(1000).build();

			Inventory inventory2 = Inventory.builder()
					.skuCode("Iphone 15")
					.quantity(500).build();

			Inventory inventory3 = Inventory.builder()
					.skuCode("Iphone 16")
					.quantity(0).build();

			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
			inventoryRepository.save(inventory3);
		};
	}

}
