package com.babyduncan.serviceDiscovery;

/**
 * User: guohaozhao (guohaozhao116008@sohu-inc.com)
 * Date: 12/19/13 16:05
 * only an example service
 */
public class ExampleService {

    private String description;

    public ExampleService() {
        this("");
    }

    public ExampleService(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
