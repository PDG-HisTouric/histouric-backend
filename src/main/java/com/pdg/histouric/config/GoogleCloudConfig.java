package com.pdg.histouric.config;

import com.google.maps.GeoApiContext;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "google.cloud")
public class GoogleCloudConfig {
    private String directionsApiKey;

    @Bean
    public GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey(directionsApiKey)
                .build();
    }
}
