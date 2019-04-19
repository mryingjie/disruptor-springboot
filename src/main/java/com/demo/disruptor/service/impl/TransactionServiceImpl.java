package com.demo.disruptor.service.impl;

import com.alibaba.fastjson.JSON;
import com.demo.disruptor.envent.TransactionEvent;
import com.demo.disruptor.envent.TransactionEventFactory;
import com.demo.disruptor.handler.InnerDBHandler;
import com.demo.disruptor.handler.SendMessageHandler;
import com.demo.disruptor.handler.TransHandler;
import com.demo.disruptor.service.TransactionService;
import com.demo.disruptor.config.CustomThreadFactoryBuilder;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadFactory;

/**
 * @Author ZhengYingjie
 * @Date 2019/4/11
 * @Description
 */
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService, DisposableBean, InitializingBean {

    private Disruptor<TransactionEvent> disruptor;
    private static final int RING_BUFFER_SIZE = 1024 * 1024;

    @Autowired
    private InnerDBHandler innerDBHandler;

    @Autowired
    private SendMessageHandler sendMessageHandler;

    @Autowired
    private TransHandler transHandler;


    private Producer producer;


    public Disruptor<TransactionEvent> getDisruptor() {
        return disruptor;
    }

    @Override
    public void destroy() throws Exception {
        disruptor.shutdown();
        log.info("disruptor of transactionEvent shutdown");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ThreadFactory threadFactory = new CustomThreadFactoryBuilder()
                .setNamePrefix("transactionEvent")
                .setDaemon(false)
                .build();
        disruptor = new Disruptor<>(
                new TransactionEventFactory(),
                RING_BUFFER_SIZE,
                threadFactory,
                ProducerType.SINGLE,
                new YieldingWaitStrategy()
        );
        //创建消费者组，先执行拦截交易流水和入库操作
        disruptor
                .handleEventsWith(innerDBHandler,transHandler)
                .then(sendMessageHandler);
        //启动disruptor
        disruptor.start();
        this.producer = new Producer(disruptor.getRingBuffer());
        log.info("disruptor of transactionEvent start");
    }

    public Producer getProducter() {
        return producer;
    }

    public class Producer {
        private RingBuffer<TransactionEvent> ringBuffer;

        Producer(RingBuffer<TransactionEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void onData(TransactionEvent t) {
            log.info("publish TransactionEvent [{}]", JSON.toJSONString(t));
            long sequence = ringBuffer.next();
            try {
                TransactionEvent t1 = ringBuffer.get(sequence);
                BeanUtils.copyProperties(t, t1);
            } finally {
                ringBuffer.publish(sequence);
            }
        }
    }
}
