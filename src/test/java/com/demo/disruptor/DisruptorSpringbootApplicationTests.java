package com.demo.disruptor;

import com.demo.disruptor.api.TransactionApi;
import com.demo.disruptor.envent.TransactionEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DisruptorSpringbootApplicationTests {

    @Autowired
    private TransactionApi transactionApi;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 10; i++) {
            ((Runnable) () -> {
                TransactionEvent transactionEvent = new TransactionEvent();
                Random random = new Random();
                for (int j = 0; j < 100; j++) {
                    transactionEvent.setSeq(j);
                    transactionEvent.setAmount(Double.valueOf(random.nextInt(1000)+"."+random.nextInt(100)));
                    transactionEvent.setCallNumber(random.nextInt(10));
                    transactionApi.testTransation(transactionEvent);
                }
            }).run();
        }

    }



}
