package com.babyduncan.cacheUsage;

import org.apache.curator.framework.recipes.cache.ChildData;
import org.junit.Test;

/**
 * User: guohaozhao (guohaozhao116008@sohu-inc.com)
 * Date: 12/17/13 16:44
 */
public class PathCacheFactoryTest {

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            for (ChildData data : PathCacheFactory.getBabyDuncanPathData()) {
                System.out.println(data.getPath() + " = " + new String(data.getData()));
            }
            Thread.sleep(1000);
            System.out.println("+++++++++++++++++++++++++");
        }
    }

}
