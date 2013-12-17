package com.babyduncan.cacheUsage;

import org.apache.curator.framework.recipes.cache.ChildData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: guohaozhao (guohaozhao116008@sohu-inc.com)
 * Date: 12/17/13 17:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:curator-applicationContext.xml")
public class PathCacheInitBeanTest {

    @Autowired
    PathCacheInitBean pathCacheInitBean;

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
