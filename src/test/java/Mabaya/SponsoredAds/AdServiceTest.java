package Mabaya.SponsoredAds;

import Mabaya.SponsoredAds.dto.ProductDTO;
import Mabaya.SponsoredAds.entity.Campaign;
import Mabaya.SponsoredAds.entity.Product;
import Mabaya.SponsoredAds.repos.CampaignRepository;
import Mabaya.SponsoredAds.repos.ProductRepository;
import Mabaya.SponsoredAds.service.AdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Date;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AdServiceTest {

	@Mock
	private CampaignRepository campaignRepository;

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private AdService adService;

	private Campaign campaign;
	private Product product;

	@BeforeEach
	void setUp() {
		product = new Product();
		product.setId(1L);
		product.setTitle("Laptop Pro 17");
		product.setCategory("Electronics");
		product.setPrice(2399.99);
		product.setSerialNumber("SN001");

		campaign = new Campaign();
		campaign.setId(1L);
		campaign.setName("Summer Electronics Sale");
		campaign.setStartDate(new Date(System.currentTimeMillis() - 100000));
		campaign.setBid(2.5);
		campaign.setProducts(Arrays.asList(product));
	}

	@Test
	void whenValidCategory_thenProductShouldBeReturned() {
		when(campaignRepository.findAll()).thenReturn(Arrays.asList(campaign));

		ProductDTO result = adService.serveAd("Electronics");

		assertThat(result).isNotNull();
		assertThat(result.getCategory()).isEqualTo("Electronics");
	}



	@Test
	void whenRepositoryThrowsException_thenShouldPropagate() {
		when(campaignRepository.findAll()).thenThrow(new RuntimeException("Error serving ad for category"));

		Exception exception = assertThrows(RuntimeException.class, () -> adService.serveAd("Electronics"));
		assertThat(exception.getMessage()).contains("Error serving ad for category: Electronics");
	}
}
