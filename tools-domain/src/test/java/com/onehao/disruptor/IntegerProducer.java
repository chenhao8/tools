package com.onehao.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class IntegerProducer {

	private RingBuffer<IntegerEvent> buffer;

	public IntegerProducer(RingBuffer<IntegerEvent> buffer) {
		this.buffer = buffer;
	}

	private EventTranslatorOneArg<IntegerEvent, Integer> translator = new EventTranslatorOneArg<IntegerEvent, Integer>() {
		@Override
		public void translateTo(IntegerEvent event, long sequence, Integer value) {
			event.setValue(value);
		}
	};

	public void pushData(int value) {
		buffer.publishEvent(translator, value);
	}
}