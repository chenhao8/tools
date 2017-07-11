package com.onehao.domain.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class RiskProducer {

	private final RingBuffer<RiskEvent> ringBuffer;

	public RiskProducer(RingBuffer<RiskEvent> buffer) {
		this.ringBuffer = buffer;
	}

	private static final EventTranslatorOneArg<RiskEvent, RiskApplication> translator = new EventTranslatorOneArg<RiskEvent, RiskApplication>() {
		@Override
		public void translateTo(RiskEvent event, long sequence, RiskApplication riskApplication) {
			event.setRiskApplication(riskApplication);
		}
	};

	public void pushData(RiskApplication riskApplication) {
		ringBuffer.publishEvent(translator, riskApplication);
	}
}