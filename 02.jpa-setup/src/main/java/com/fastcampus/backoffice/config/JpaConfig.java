package com.fastcampus.backoffice.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configurable           // Spring에서 Config를 적용할때 설정하는 annotation
@EnableJpaAuditing      // Jpa Auditing 활성화 
public class JpaConfig {
}
