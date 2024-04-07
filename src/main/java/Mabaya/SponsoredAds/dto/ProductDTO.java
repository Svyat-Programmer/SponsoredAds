package Mabaya.SponsoredAds.dto;

import lombok.Data;

import java.security.SecureRandom;
@Data
public class ProductDTO {
    private Long id;
    private String title;
    private String category;
    private Double price;
    private String serialNumber;
}
