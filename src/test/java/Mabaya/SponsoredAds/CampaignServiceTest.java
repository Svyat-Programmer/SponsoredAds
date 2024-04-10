package Mabaya.SponsoredAds;

import Mabaya.SponsoredAds.dto.CampaignDTO;
import Mabaya.SponsoredAds.entity.Campaign;
import Mabaya.SponsoredAds.entity.Product;
import Mabaya.SponsoredAds.repos.CampaignRepository;
import Mabaya.SponsoredAds.repos.ProductRepository;
import Mabaya.SponsoredAds.service.CampaignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CampaignService campaignService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");
        product1.setCategory("Category 1");

        product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");
        product2.setCategory("Category 2");
    }

    @Test
    void createCampaign_WithValidProducts() {
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setName("New Campaign");
        campaignDTO.setStartDate(new Date());
        campaignDTO.setBid(1.5);
        campaignDTO.setProductIds(Arrays.asList(1L, 2L));

        when(productRepository.findAllById(campaignDTO.getProductIds())).thenReturn(Arrays.asList(product1, product2));
        when(campaignRepository.save(any(Campaign.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CampaignDTO result = campaignService.createCampaign(campaignDTO);

        assertThat(result.getName()).isEqualTo(campaignDTO.getName());
        verify(campaignRepository, times(1)).save(any(Campaign.class));
    }

    @Test
    void createCampaign_WithSomeProductsNotFound() {
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setName("Incomplete Campaign");
        campaignDTO.setStartDate(new Date());
        campaignDTO.setBid(2.0);
        campaignDTO.setProductIds(Arrays.asList(1L, 3L)); // Assuming product with ID 3 does not exist

        when(productRepository.findAllById(campaignDTO.getProductIds())).thenReturn(Arrays.asList(product1));
        when(campaignRepository.save(any(Campaign.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CampaignDTO result = campaignService.createCampaign(campaignDTO);

        assertThat(result.getName()).isEqualTo(campaignDTO.getName());
        verify(campaignRepository, times(1)).save(any(Campaign.class));
    }
}
