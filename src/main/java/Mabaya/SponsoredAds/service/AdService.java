package Mabaya.SponsoredAds.service;

import Mabaya.SponsoredAds.dto.ProductDTO;
import Mabaya.SponsoredAds.dto.ProductMapper;
import Mabaya.SponsoredAds.entity.Campaign;
import Mabaya.SponsoredAds.entity.Product;
import Mabaya.SponsoredAds.repos.CampaignRepository;
import Mabaya.SponsoredAds.repos.ProductRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
    {
        Date currentDate = new Date();
        List<Campaign>activeCampaigns = campaignRepository.findAll().stream()
                .filter(campaign->campaign.getStartDate().compareTo(currentDate)<=0
                        && ChronoUnit.DAYS.between(campaign.getStartDate().toInstant(), currentDate.toInstant()) <= 10)
                .collect(Collectors.toList());
        Product highestBidProduct = activeCampaigns.stream()
                .flatMap(campaign -> campaign.getProducts().stream())
                .filter(product -> product.getCategory().equals(category))
                .max(Comparator.comparingDouble(product -> findBidForProduct(product, activeCampaigns)))
                .orElseGet(() -> findHighestBidProduct(activeCampaigns));

        return ProductMapper.toDTO(highestBidProduct);
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
