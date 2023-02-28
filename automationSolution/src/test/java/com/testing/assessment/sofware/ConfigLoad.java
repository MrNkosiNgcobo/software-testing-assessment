package com.testing.assessment.sofware;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({"classpath:config/runconfigurations.properties"})
public class ConfigLoad {
}