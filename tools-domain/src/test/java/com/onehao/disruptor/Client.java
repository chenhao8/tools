package com.onehao.disruptor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.lmax.disruptor.dsl.Disruptor;

public class Client {

    private IntegerEventFactory factory;

    private int ringBufferSize;

    private ExecutorService executorService;

    /**
     * 
     * 
     * @throws Exception 
     * void
     * @exception 
     * @since  1.0.0
    */
    @Before
    public void setUp() throws Exception {
        factory = new IntegerEventFactory();
        ringBufferSize = 8;
        executorService = new ThreadPoolExecutor(5, 20, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(20));
    }

    /**
     * 
     *  
     * void
     * @exception 
     * @since  1.0.0
    */
    @Test
    public void client() {
        Disruptor<IntegerEvent> disruptor = new Disruptor<>(factory, ringBufferSize, executorService);
        // 关联消费者
        disruptor.handleEventsWith(new IntegerConsumer());

        // 子线程从buffer消费数据
        disruptor.start();

        // 主线程向buffer生产数据
        IntegerProducer producer = new IntegerProducer(disruptor.getRingBuffer());
        for (int i = 0; i < 100; ++i) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            producer.pushData(i);
        }
        disruptor.shutdown();
        executorService.shutdown();
    }
}