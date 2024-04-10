package Mabaya.SponsoredAds.service;

import Mabaya.SponsoredAds.dto.ProductDTO;
import Mabaya.SponsoredAds.dto.ProductMapper;
import Mabaya.SponsoredAds.entity.Campaign;
import Mabaya.SponsoredAds.entity.Product;
import Mabaya.SponsoredAds.exceptions.AdServiceException;
import Mabaya.SponsoredAds.repos.CampaignRepository;
import Mabaya.SponsoredAds.repos.ProductRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdService {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private ProductRepository productRepository;

    public ProductDTO serveAd (String category)
    {   try{
        log.debug("Serving ad for category: {}", category);
        Date currentDate = new Date();
        log.debug("current date: {}" , currentDate);
        List<Campaign>activeCampaigns = campaignRepository.findAll().stream()
                .filter(campaign->campaign.getStartDate().compareTo(currentDate)<=0
                        && ChronoUnit.DAYS.between(campaign.getStartDate().toInstant(), currentDate.toInstant()) <= 10)
                .collect(Collectors.toList());
        log.info("Found {} active campaigns", activeCampaigns.size());
        Product highestBidProduct = activeCampaigns.stream()
                .flatMap(campaign -> campaign.getProducts().stream())
                .filter(product -> product.getCategory().equals(category))
                .max(Comparator.comparingDouble(product -> findBidForProduct(product, activeCampaigns)))
                .orElseGet(() -> findHighestBidProduct(activeCampaigns));
        if (highestBidProduct == null) {
            log.warn("No product found for category: {}", category);
            return null;
        } else {
            log.info("Serving product {} for category: {}", highestBidProduct.getId(), category);
            return ProductMapper.toDTO(highestBidProduct);
        }
        }catch (Exception e){
            log.error("Error serving ad for category: {}", category, e);

            throw new AdServiceException("Error serving ad for category: " + category, e);
        }
    }

    private Product findHighestBidProduct(List<Campaign> activeCampaigns) {
        return activeCampaigns.stream()
                .flatMap(campaign -> campaign.getProducts().stream()
                        .map(product -> new AbstractMap.SimpleEntry<>(product, campaign.getBid())))
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }


    private double findBidForProduct(Product product, List<Campaign> activeCampaigns) {
        return activeCampaigns.stream()
                .filter(campaign -> campaign.getProducts().contains(product))
                .mapToDouble(Campaign::getBid)
                .max()
                .orElse(0);
    }


}
