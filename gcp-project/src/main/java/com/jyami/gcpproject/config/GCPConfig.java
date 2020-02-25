package com.jyami.gcpproject.config;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Created by jyami on 2020/02/25
 */
@Configuration
public class GCPConfig {

    @Value("${spring.cloud.gcp.project-id}")
    private String projectID;

    @Value("${spring.cloud.gcp.storage.credentials.location}")
    private Resource credentialLocation;

    @Bean
    public Storage injectGCPStorage() throws IOException {
        return StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(credentialLocation.getInputStream()))
                .setProjectId(projectID).build().getService();
    }

}