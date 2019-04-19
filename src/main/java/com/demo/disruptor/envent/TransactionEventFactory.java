package com.demo.disruptor.envent;

import com.lmax.disruptor.EventFactory;

/**
 * @Author ZhengYingjie
 * @Date 2019/4/11
 * @Description
 */
public class TransactionEventFactory implements EventFactory<TransactionEvent> {

    @Override
    public TransactionEvent newInstance() {
        return new TransactionEvent();
    }
}
