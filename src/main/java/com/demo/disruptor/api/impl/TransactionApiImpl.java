package com.demo.disruptor.api.impl;

import com.demo.disruptor.api.TransactionApi;
import com.demo.disruptor.envent.TransactionEvent;
import com.demo.disruptor.service.TransactionService;
import com.demo.disruptor.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ZhengYingjie
 * @Date 2019/4/12
 * @Description
 */
@Service
public class TransactionApiImpl implements TransactionApi {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Override
    public void testTransation(TransactionEvent transactionEvent) {
        System.out.println("处理业务逻辑:{"+transactionEvent+"}");
        transactionService
                .getProducter()
                .onData(transactionEvent);
    }
}
