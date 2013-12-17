package com.babyduncan.cacheUsage;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * User: guohaozhao (guohaozhao116008@sohu-inc.com)
 * Date: 12/17/13 15:52
 * pathCache的使用方法
 */
public class PathCacheUsage {

    public static void main(String... args) throws Exception {
        CuratorFramework client = null;
        PathChildrenCache cache = null;

        client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();

        // in this example we will cache data. Notice that this is optional.
        cache = new PathChildrenCache(client, "/babyduncan", true);
        cache.start();
        // sleep sometime ,no sleep no data .
        Thread.sleep(3000);

        if (cache.getCurrentData().size() == 0) {
            System.out.println("* empty *");
        } else {
            for (ChildData data : cache.getCurrentData()) {
                System.out.println(data.getPath() + " = " + new String(data.getData()));
            }
        }
    }

}
