package com.onehao.domain.disruptor;

import com.lmax.disruptor.EventFactory;

public class RiskEventFactory implements EventFactory<RiskEvent> {

	@Override
	public RiskEvent newInstance() {
		return new RiskEvent();
	}
}