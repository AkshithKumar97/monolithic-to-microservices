package com.upgrade.companyms.company.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompanyRabbitMQConfig {

    @Bean
    public Queue companyRatingQueue(){
        return new Queue("companyRatingQueue");
    }

    @Bean
    public MessageConverter jasonMessageConverter(){
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rbt = new RabbitTemplate(connectionFactory);
        rbt.setMessageConverter(jasonMessageConverter());
        return rbt;
    }
}
