package com.demo.disruptor.handler;

import com.demo.disruptor.envent.TransactionEvent;
import com.lmax.disruptor.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @Author ZhengYingjie
 * @Date 2019/4/11
 * @Description
 */
@Component
public class SendMessageHandler implements EventHandler<TransactionEvent> {

    @Override
    public void onEvent(TransactionEvent transactionEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("向手机号:" + transactionEvent.getCallNumber() + "发送验证短信......"+"with Thread ["+Thread.currentThread().getName()+"]");
        System.out.println("================================================================");
    }
}

