package com.onehao.domain.disruptor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class RuleProcessor implements IRuleProcessor {

	private Logger logger = Logger.getLogger(RuleProcessor.class);

	private RiskEventFactory factory = new RiskEventFactory();

	private Map<String, EventHandler> handlers;

	public void setHandlers(Map<String, EventHandler> handlers) {
		this.handlers = handlers;
	}

	@Autowired
	private RiskConsumer riskConsumer;

	private static int bufferSize = 1024 * 8;
	private static Executor executor = Executors.newFixedThreadPool(400);

	private Map<String, Disruptor<RiskEvent>> disruptorMap = new HashMap<String, Disruptor<RiskEvent>>();

	/**
	 * 创建disruptor
	 * @param application
	 * @return
	 */
	private synchronized Disruptor createDisruptor(RiskApplication application) {
		String temp = application.getIdCard();
		if (disruptorMap.containsKey(temp)) {
			return disruptorMap.get(temp);
		} else {
			Disruptor<RiskEvent> disruptor = new Disruptor<RiskEvent>(factory, bufferSize, executor, ProducerType.MULTI, new BlockingWaitStrategy());
			// 创建一个ExceptionHandler
			disruptor.handleExceptionsWith(new ExceptionHandler() {
				@Override
				public void handleEventException(Throwable ex, long sequence, Object event) {
					logger.error("---Disruptor-----handleEventException---", ex);
				}

				@Override
				public void handleOnStartException(Throwable ex) {
					logger.error("---Disruptor-----handleOnStartException---", ex);
				}

				@Override
				public void handleOnShutdownException(Throwable ex) {
					logger.error("---Disruptor-----handleOnShutdownException---", ex);
				}
			});
			EventHandler[] eh = new EventHandler[handlers.size()];
			disruptor.handleEventsWith(handlers.values().toArray(eh)).then(riskConsumer);
			disruptor.start();
			disruptorMap.put(temp, disruptor);
			return disruptor;
		}
	}

	/**
	 * 销毁disruptor
	 */
	public void destroyDisruptor() {
		logger.info("================销毁disrupor===================");
		try {
			for (Map.Entry<String, Disruptor<RiskEvent>> entry : disruptorMap.entrySet()) {
				entry.getValue().shutdown();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public void check(RiskApplication application) {
		Disruptor<RiskEvent> disruptor = createDisruptor(application);
		//
		logger.info("================RuleMappingResultProcess start,Disruptor=" + disruptor + "============");
		//
		RingBuffer<RiskEvent> ringBuffer = disruptor.getRingBuffer();
		RiskProducer translator = new RiskProducer(ringBuffer);
		translator.pushData(application);
	}

}
