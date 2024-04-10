package Mabaya.SponsoredAds;

import Mabaya.SponsoredAds.entity.Product;
import Mabaya.SponsoredAds.repos.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql({"/schema.sql", "/data.sql"})
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	void whenFindByCategory_thenReturnsProducts() {

		List<Product> products =  productRepository.findByCategory("Electronics");


		assertThat(products).hasSize(4);
		assertThat(products.get(0).getTitle()).isEqualTo("Laptop Pro 17");
		assertThat(products.get(1).getTitle()).isEqualTo("Smartphone X");
	}
}


