package com.babyduncan.cacheUsage;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * User: guohaozhao (guohaozhao116008@sohu-inc.com)
 * Date: 12/17/13 16:40
 */

public class PathCacheFactory {

    private static final Logger logger = Logger.getLogger(PathCacheFactory.class);

    private static final CuratorFramework client;
    private static final PathChildrenCache babyduncanPathCache;

    static {
        client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        babyduncanPathCache = new PathChildrenCache(client, "/babyduncan", true);
        try {
            babyduncanPathCache.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static List<ChildData> getBabyDuncanPathData() {
        return babyduncanPathCache.getCurrentData();
    }

}
