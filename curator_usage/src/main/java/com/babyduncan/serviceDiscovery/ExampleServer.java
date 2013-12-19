package com.babyduncan.serviceDiscovery;

import com.google.common.io.Closeables;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;

import java.io.Closeable;
import java.io.IOException;

/**
 * User: guohaozhao (guohaozhao116008@sohu-inc.com)
 * Date: 12/19/13 16:08
 */
public class ExampleServer implements Closeable {

    private final ServiceDiscovery<ExampleService> serviceDiscovery;
    private final ServiceInstance<ExampleService> thisInstance;

    public ExampleServer(CuratorFramework client, String path, String serviceName, String description) throws Exception {
        // in a real application, you'd have a convention of some kind for the URI layout
        UriSpec uriSpec = new UriSpec("{scheme}://foobar.com:{port}");

        thisInstance = ServiceInstance.<ExampleService>builder()
                .name(serviceName)
                .payload(new ExampleService())
                .port((int) (65535 * Math.random())) // in a real application, you'd use a common port
                .uriSpec(uriSpec)
                .build();


        serviceDiscovery = ServiceDiscoveryBuilder.builder(ExampleService.class)
                .client(client)
                .basePath(path)
                .thisInstance(thisInstance)
                .build();
    }

    public ServiceInstance<ExampleService> getThisInstance() {
        return thisInstance;
    }

    public void start() throws Exception {
        serviceDiscovery.start();
    }

    @Override
    public void close() throws IOException {
        Closeables.closeQuietly(serviceDiscovery);
    }

}
