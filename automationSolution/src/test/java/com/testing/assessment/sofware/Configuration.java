package com.testing.assessment.sofware;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = CheckoutFunctionalityApplication.class)
public class Configuration {
}
