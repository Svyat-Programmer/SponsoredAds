package Mabaya.SponsoredAds.repos;

import Mabaya.SponsoredAds.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository <Campaign, Long>  {
}
