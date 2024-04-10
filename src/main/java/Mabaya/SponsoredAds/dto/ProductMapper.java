package Mabaya.SponsoredAds.dto;

import Mabaya.SponsoredAds.entity.Product;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setSerialNumber(product.getSerialNumber());
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setSerialNumber(dto.getSerialNumber());
        return product;
    }
}
