package Mabaya.SponsoredAds.controllers;

import Mabaya.SponsoredAds.service.CampaignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/campaigns")
public class CampaignController {
    private final CampaignService campaignService;


    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }
}
