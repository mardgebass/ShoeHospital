package com.shoehospital.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({"file:src/test/resources/config.properties"})
public interface TestConfig extends Config {

    TestConfig testConfig = ConfigFactory.create(TestConfig.class);
    String baseUrl();
    String username();
    String password();

}
