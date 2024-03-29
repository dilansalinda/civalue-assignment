package org.civalue.apiservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AppConfig {

    @Value("${data.team.service.base.url}")
    private String dataTeamServiceBaseURL;
}
