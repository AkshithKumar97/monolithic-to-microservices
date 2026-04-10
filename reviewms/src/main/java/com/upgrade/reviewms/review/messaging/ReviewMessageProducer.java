package com.upgrade.reviewms.review.messaging;

import com.upgrade.reviewms.review.Review;
import com.upgrade.reviewms.review.dto.ReviewMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private static final String COMPANY_RATING_QUEUE = "companyRatingQueue";


    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Review review){

        ReviewMessage rm = new ReviewMessage();
        rm.setId(review.getId());
        rm.setTitle(review.getTitle());
        rm.setDescription(review.getDescription());
        rm.setRating(review.getRating());
        rm.setCompanyId(review.getCompanyId());

        rabbitTemplate.convertAndSend(COMPANY_RATING_QUEUE, rm);
    }
}
