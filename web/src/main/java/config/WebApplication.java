package config;

import org.glassfish.jersey.server.ResourceConfig;

public class WebApplication extends ResourceConfig {
    public WebApplication() {
        packages("jersey");
    }
}
