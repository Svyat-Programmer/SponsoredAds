package Mabaya.SponsoredAds.dto;

import Mabaya.SponsoredAds.entity.Campaign;
import Mabaya.SponsoredAds.entity.Product;

import java.util.stream.Collectors;

public class CampaignMapper {

        public static CampaignDTO toDTO(Campaign campaign) {
            CampaignDTO dto = new CampaignDTO();
            dto.setId(campaign.getId());
            dto.setName(campaign.getName());
            dto.setStartDate(campaign.getStartDate());
            dto.setBid(campaign.getBid());

            if (campaign.getProducts() != null) {
                dto.setProductIds(campaign.getProducts().stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()));
            }

            return dto;
        }

        public static Campaign toEntity(CampaignDTO dto) {
            Campaign campaign = new Campaign();
            campaign.setId(dto.getId());
            campaign.setName(dto.getName());
            campaign.setStartDate(dto.getStartDate());
            campaign.setBid(dto.getBid());


            return campaign;
        }
    }


