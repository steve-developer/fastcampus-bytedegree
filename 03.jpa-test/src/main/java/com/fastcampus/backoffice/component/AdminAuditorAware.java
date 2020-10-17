package com.fastcampus.backoffice.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminAuditorAware implements AuditorAware<String> {

    @Value("${spring.application.name}")        // @Value Annotation으로 application.yaml 에 정의된 내용 import
    private String serverName;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(serverName);         // serverName이 없을수도 있음으로 Optional 로 return
    }
}
