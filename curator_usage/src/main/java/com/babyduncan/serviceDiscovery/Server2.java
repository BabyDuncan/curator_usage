package com.babyduncan.serviceDiscovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;

import java.util.concurrent.CountDownLatch;

/**
 * User: guohaozhao (guohaozhao116008@sohu-inc.com)
 * Date: 12/19/13 17:18
 */
public class Server2 {

    public static void main(String... args) throws Exception {

        CuratorFramework client = null;
        ServiceDiscovery<ExampleService> serviceDiscovery = null;

        client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        serviceDiscovery = ServiceDiscoveryBuilder.builder(ExampleService.class).client(client).basePath("/discovery").build();
        serviceDiscovery.start();

        ExampleServer server = new ExampleServer(client, "/discovery", "babyduncan", "2.2.2.2", 8081, "server2");
        server.start();

        System.out.println("server2 added");

        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();

    }

}
