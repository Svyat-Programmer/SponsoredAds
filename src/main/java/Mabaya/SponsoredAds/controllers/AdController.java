package Mabaya.SponsoredAds.controllers;

import Mabaya.SponsoredAds.dto.ProductDTO;
import Mabaya.SponsoredAds.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ads")
public class AdController {
    private final AdService adService;

    @Autowired
    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping
    public ResponseEntity<ProductDTO> serveAd(@RequestParam String category) {
        ProductDTO productDTO = adService.serveAd(category);
        if (productDTO != null) {
            return ResponseEntity.ok(productDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
