package Mabaya.SponsoredAds.controllers;

import Mabaya.SponsoredAds.dto.CampaignDTO;
import Mabaya.SponsoredAds.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/campaigns")
public class CampaignController {
    private final CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }
    @PostMapping
    public ResponseEntity<CampaignDTO> createCampaign(@RequestBody CampaignDTO campaignDTO) {
        CampaignDTO createdCampaign = campaignService.createCampaign(campaignDTO);
        return new ResponseEntity<>(createdCampaign, HttpStatus.CREATED);
    }

}
