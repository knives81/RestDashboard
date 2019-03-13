package com.dashboard.restservicedashboard.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "app")
public class AppProperties {

	@Getter @Setter private String biDays;
    @Getter @Setter private Boolean almAuthentication;
    @Getter @Setter private String restserviceDashboardVersion;
    @Getter @Setter private String almUrlForAuth;
    @Getter @Setter private String scheduledRateInMs;

    @Getter @Setter private Boolean isSyncFolderActivated;
    @Getter @Setter private String mongoImportPath;
    @Getter @Setter private String collectionsToBeImported;
    @Getter @Setter private String originFolder;
    @Getter @Setter private String mongoUri;

}
