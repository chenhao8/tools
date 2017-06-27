package com.onehao.disruptor;

import com.lmax.disruptor.RingBuffer;

public class IntegerProducer1 {

    private RingBuffer<IntegerEvent> buffer;

    public IntegerProducer1(RingBuffer<IntegerEvent> buffer) {
        this.buffer = buffer;
    }

    public void pushData(int value) {
        long sequence = buffer.next();
        try {
            //该event从buffer中拿出(当环形buffer填满时, 你能看到event里面的值)
            IntegerEvent event = buffer.get(sequence);
            event.setValue(value);
        } finally {
            buffer.publish(sequence);
        }
    }
}