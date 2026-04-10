package com.upgrade.companyms.company.clients;

import com.upgrade.companyms.company.dto.ReviewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="review-service")
public interface ReviewClient {

    @GetMapping("reviews")
    List<ReviewDTO> reviewsByCompId(@RequestParam("companyId") Long companyId);

    @GetMapping("/reviews/averageRating")
    Double getAverageRatingForCompany(@RequestParam("companyId") Long companyID);
}
