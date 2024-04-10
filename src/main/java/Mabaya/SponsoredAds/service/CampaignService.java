package Mabaya.SponsoredAds.service;

import Mabaya.SponsoredAds.dto.CampaignDTO;
import Mabaya.SponsoredAds.dto.CampaignMapper;
import Mabaya.SponsoredAds.entity.Campaign;
import Mabaya.SponsoredAds.entity.Product;
import Mabaya.SponsoredAds.repos.CampaignRepository;
import Mabaya.SponsoredAds.repos.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private ProductRepository productRepository;

    public CampaignDTO createCampaign(CampaignDTO campaignDTO) {
        log.debug("Start createCampaign");
        List<Product> products = productRepository.findAllById(campaignDTO.getProductIds());
        if (products.size() != campaignDTO.getProductIds().size()) {
            log.error("Some products not found");
        }

        Campaign campaign = new Campaign();
        campaign.setName(campaignDTO.getName());
        campaign.setStartDate(campaignDTO.getStartDate());
        campaign.setBid(campaignDTO.getBid());
        campaign.setProducts(products);
        campaign = campaignRepository.save(campaign);

        log.debug("Finished create campaign");
        return CampaignMapper.toDTO(campaign);
    }
}

