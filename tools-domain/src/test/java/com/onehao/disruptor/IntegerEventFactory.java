package com.onehao.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 用来产生Event事件(填充默认的buffer)
 *
 * @author jifang
 * @since 16/1/22 下午8:50.
 */
public class IntegerEventFactory implements EventFactory<IntegerEvent> {

    @Override
    public IntegerEvent newInstance() {
        return new IntegerEvent();
    }
}