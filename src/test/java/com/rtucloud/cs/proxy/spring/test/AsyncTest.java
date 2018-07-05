package com.rtucloud.cs.proxy.spring.test;

import com.rtucloud.cs.proxy.StartProgram;
import com.rtucloud.cs.proxy.server.ProxyServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = StartProgram.class) // 指定我们SpringBoot工程的Application启动类
public class AsyncTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTest.class);

    @Autowired
    ProxyServer proxyServer;

    @Test
    public void test1() throws Exception{

       proxyServer.startProxyServer();
        Thread.sleep(5000);
        proxyServer.stopProxyServer();
        Thread.sleep(5000);
        proxyServer.startProxyServer();


        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
