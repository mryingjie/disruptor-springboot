package com.demo.disruptor.handler;

import com.demo.disruptor.envent.TransactionEvent;
import com.lmax.disruptor.ExceptionHandler;

/**
 * @Author ZhengYingjie
 * @Date 2019/4/14
 * @Description
 */
public class CustomerExceptionHandler implements ExceptionHandler<TransactionEvent> {
    @Override
    public void handleEventException(Throwable ex, long sequence, TransactionEvent event) {

    }

    @Override
    public void handleOnStartException(Throwable ex) {

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}
