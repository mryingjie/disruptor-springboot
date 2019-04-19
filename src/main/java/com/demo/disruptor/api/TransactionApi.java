package com.demo.disruptor.api;

import com.demo.disruptor.envent.TransactionEvent;

/**
 * @Author ZhengYingjie
 * @Date 2019/4/12
 * @Description
 */
public interface TransactionApi {

    void testTransation(TransactionEvent transactionEvent);

}
