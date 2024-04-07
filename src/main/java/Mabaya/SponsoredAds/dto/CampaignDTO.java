package Mabaya.SponsoredAds.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class CampaignDTO {
    private Long id;
    private String name;
    private Date startDate;
    private List<Long> productIds;
    private Double bid;
}
