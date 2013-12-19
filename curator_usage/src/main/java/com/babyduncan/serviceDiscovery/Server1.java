package com.babyduncan.serviceDiscovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;

import java.util.concurrent.CountDownLatch;

/**
 * User: guohaozhao (guohaozhao116008@sohu-inc.com)
 * Date: 12/19/13 17:17
 */
public class Server1 {

    public static void main(String... args) throws Exception {

        CuratorFramework client = null;
        ServiceDiscovery<ExampleService> serviceDiscovery = null;

        client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        serviceDiscovery = ServiceDiscoveryBuilder.builder(ExampleService.class).client(client).basePath("/discovery").build();
        serviceDiscovery.start();

        ExampleServer server = new ExampleServer(client, "/discovery", "babyduncan", "1.1.1.1", 8080, "server1");
        server.start();

        System.out.println("server1 added");

        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();

    }

}
