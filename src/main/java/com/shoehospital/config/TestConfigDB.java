package com.shoehospital.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({"file:src/main/resources/DB.properties"})
public interface TestConfigDB extends Config {

    TestConfigDB testConfig = ConfigFactory.create(TestConfigDB.class);
    String url();
    String user();
    String password();

}
