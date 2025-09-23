package com.itau.account.balance.config.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class SqsBean {

    @Bean
    public SqsClient getSqs() {
        return SqsClient.builder().build();
    }
}