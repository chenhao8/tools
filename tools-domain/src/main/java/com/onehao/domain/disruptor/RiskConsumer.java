package com.onehao.domain.disruptor;

import com.lmax.disruptor.EventHandler;

public class RiskConsumer implements EventHandler<RiskEvent> {

	@Override
	public void onEvent(RiskEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("sequence: " + sequence);
		System.out.println("endOfBatch: " + endOfBatch);
		System.out.println("event-value: " + event.getRiskApplication().toString());
		System.out.println("\t" + Thread.currentThread().getName());
	}
}