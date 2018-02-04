package config;

import org.glassfish.jersey.server.ResourceConfig;

public class WorkerApplication extends ResourceConfig {
    public WorkerApplication() {
        packages("jersey");
    }
}
