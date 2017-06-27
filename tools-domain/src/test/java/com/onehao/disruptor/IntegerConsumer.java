package com.onehao.disruptor;

import com.lmax.disruptor.EventHandler;

public class IntegerConsumer implements EventHandler<IntegerEvent> {

    @Override
    public void onEvent(IntegerEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("sequence: " + sequence);
        System.out.println("endOfBatch: " + endOfBatch);
        System.out.println("event-value: " + event.getValue());
        System.out.println("\t" + Thread.currentThread().getName());
    }
}