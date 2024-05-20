package com.room.good;

import com.room.good.entity.CategoryBig;
import com.room.good.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //
// 날짜 자동으로 수집하는?
public class GoodApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoodApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData(CategoryRepository repository) {
		return (args) -> {
			insertCategoryIfNotExists(repository, "가구", "5000", "50,000");
			insertCategoryIfNotExists(repository, "가전", "5000", "50,000");
			insertCategoryIfNotExists(repository, "정리/보관", "0", "20,000");
			insertCategoryIfNotExists(repository, "생활용품", "0", "10,000");
			insertCategoryIfNotExists(repository, "소품/취미", "0", "10,000");
		};
	}

	private void insertCategoryIfNotExists(CategoryRepository repository, String cname, String deliveryFee, String refund) {
		if (!repository.existsByCname(cname)) {
			CategoryBig category = new CategoryBig();
			category.setCname(cname);
			category.setDeliveryFee(deliveryFee);
			category.setRefund(refund);
			repository.save(category);
		}
	}
}