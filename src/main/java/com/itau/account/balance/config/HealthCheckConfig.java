package com.itau.account.balance.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class HealthCheckConfig {

    private final DataSource dataSource;

    @Bean
    public HealthIndicator livenessHealthIndicator() {
        return () -> {
            log.debug("Liveness health check executed");
            return Health.up().withDetail("service", "account-balance").build();
        };
    }

    @Bean
    public HealthIndicator readinessHealthIndicator() {
        return () -> {
            log.debug("Readiness health check executed");
            try {
                DataSourceHealthIndicator dbHealth = new DataSourceHealthIndicator(dataSource);
                Health dbHealthStatus = dbHealth.health();

                if (dbHealthStatus.getStatus().equals("ok")) {
                    return Health.up()
                            .withDetail("database", "connected")
                            .withDetail("service", "ready")
                            .build();
                } else {
                    return Health.down()
                            .withDetail("database", "disconnected")
                            .withDetail("service", "not-ready")
                            .build();
                }
            } catch (Exception e) {
                log.error("Readiness health check failed", e);
                return Health.down(e).build();
            }
        };
    }
}