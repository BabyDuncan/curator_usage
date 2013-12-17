package com.babyduncan.cacheUsage;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: guohaozhao (guohaozhao116008@sohu-inc.com)
 * Date: 12/17/13 17:03
 */
@Service
public class PathCacheInitBean implements InitializingBean {

    private static final Logger logger = Logger.getLogger(PathCacheFactory.class);

    private CuratorFramework client;
    private PathChildrenCache babyduncanPathCache;

    @Override
    public void afterPropertiesSet() throws Exception {
        client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        babyduncanPathCache = new PathChildrenCache(client, "/babyduncan", true);
        try {
            babyduncanPathCache.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public List<ChildData> getBabyDuncanPathData() {
        return babyduncanPathCache.getCurrentData();
    }
}
