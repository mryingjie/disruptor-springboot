package com.demo.disruptor.handler;


import com.demo.disruptor.envent.TransactionEvent;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import org.springframework.stereotype.Component;

/**
 * @Author ZhengYingjie
 * @Date 2019/4/11
 * @Description
 */
@Component
public class TransHandler implements EventHandler<TransactionEvent>, WorkHandler<TransactionEvent> {

    @Override
    public void onEvent(TransactionEvent transactionEvent) throws Exception {
        System.out.println("交易流水号为：" + transactionEvent.getSeq() + "||交易金额为:" + transactionEvent.getAmount()+"with Thread ["+Thread.currentThread().getName()+"]");
    }

    @Override
    public void onEvent(TransactionEvent transactionEvent, long seq, boolean endOfBatch) throws Exception {        // TODO Auto-generated method stub
        this.onEvent(transactionEvent);
    }
}

