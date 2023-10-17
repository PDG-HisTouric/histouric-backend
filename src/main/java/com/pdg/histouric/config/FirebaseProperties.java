package com.pdg.histouric.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Data
@Configuration
@ConfigurationProperties(prefix = "firebase")
public class FirebaseProperties {
    private String type;
    private String projectId;
    private String privateKeyId;
    private String privateKey;
    private String clientEmail;
    private String clientId;
    private String authUri;
    private String tokenUri;
    private String authProviderX509CertUrl;
    private String clientX509CertUrl;
    private String universeDomain;
    private String bucketName;

    public void saveJson() throws IOException {
        String json = "{\n" +
                "  \"type\": \"" + type + "\",\n" +
                "  \"project_id\": \"" + projectId + "\",\n" +
                "  \"private_key_id\": \"" + privateKeyId + "\",\n" +
                "  \"private_key\": \"" + privateKey + "\",\n" +
                "  \"client_email\": \"" + clientEmail + "\",\n" +
                "  \"client_id\": \"" + clientId + "\",\n" +
                "  \"auth_uri\": \"" + authUri + "\",\n" +
                "  \"token_uri\": \"" + tokenUri + "\",\n" +
                "  \"auth_provider_x509_cert_url\": \"" + authProviderX509CertUrl + "\",\n" +
                "  \"client_x509_cert_url\": \"" + clientX509CertUrl + "\",\n" +
                "  \"universe_domain\": \"" + universeDomain + "\"\n" +
                "}";
        String filePath = FirebaseProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath+"/firebase-adminsdk.json"));
        writer.write(json);
        writer.close();
    }
}