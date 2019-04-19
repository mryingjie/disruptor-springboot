package com.demo.disruptor.handler;


import com.demo.disruptor.envent.TransactionEvent;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Author ZhengYingjie
 * @Date 2019/4/11
 * @Description
 */
@Component
public class InnerDBHandler implements EventHandler<TransactionEvent>, WorkHandler<TransactionEvent> {

    @Override
    public void onEvent(TransactionEvent arg0, long arg1, boolean arg2) throws Exception {
        this.onEvent(arg0);
    }

    @Override
    public void onEvent(TransactionEvent arg0) throws Exception {
        arg0.setSeq(arg0.getSeq() * 10000);
        System.out.println("拦截入库流水号------------  " + arg0.getSeq()+"with Thread ["+Thread.currentThread().getName()+"]");
    }
}


