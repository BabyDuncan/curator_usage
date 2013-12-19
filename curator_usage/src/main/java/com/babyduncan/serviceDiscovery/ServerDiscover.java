package com.babyduncan.serviceDiscovery;

import com.google.common.collect.Maps;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;
import org.apache.curator.x.discovery.strategies.RandomStrategy;

import java.util.Map;

/**
 * User: guohaozhao (guohaozhao116008@sohu-inc.com)
 * Date: 12/19/13 17:18
 */
public class ServerDiscover {

    public static void main(String... args) throws Exception {
        Map<String, ServiceProvider<ExampleService>> providers = Maps.newHashMap();
        CuratorFramework client = null;
        ServiceDiscovery<ExampleService> serviceDiscovery = null;

        client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        serviceDiscovery = ServiceDiscoveryBuilder.builder(ExampleService.class).client(client).basePath("/discovery").build();
        serviceDiscovery.start();

        ServiceProvider<ExampleService> provider = providers.get("babyduncan");
        if (provider == null) {
            provider = serviceDiscovery.serviceProviderBuilder().serviceName("babyduncan").providerStrategy(new RandomStrategy<ExampleService>()).build();
            providers.put("babyduncan", provider);
            provider.start();
        }

        for (int i = 0; i < 100; i++) {

            ServiceInstance<ExampleService> instance = provider.getInstance();
            if (instance == null) {
                System.err.println("No instances named: babyduncan");
            } else {
                System.out.println("\t" + instance.getAddress() + ":" + instance.getPort() + ": " + instance.buildUriSpec());
            }

            Thread.sleep(2000);

        }
    }

}
