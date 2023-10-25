package com.pdg.histouric.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Builder
public class FirebaseUrl {
    Date expiration;
    String url;

    public Optional<String> getUrl() {
        if (new Date().before(expiration)) {
            return Optional.of(url);
        }
        return Optional.empty();
    }
}
