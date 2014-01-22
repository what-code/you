package com.b5m.you.service.raindrop;

import org.apache.log4j.Logger;

import com.b5m.raindrop.tao.client.ICounterService;
import com.b5m.raindrop.tao.client.ITaoClientFactory;
import com.b5m.raindrop.tao.client.metaq.FactoryConfig;
import com.b5m.raindrop.tao.client.metaq.TaoClientMetaqFactory;
import com.taobao.metamorphosis.exception.MetaClientException;

/**
 * <p>
 * 将{@link TaoClientMetaqFactory}创建的TaoClient的相关Service所需要用到的属性封装起来， 以便于spring容器能够管理。
 * 
 * <p>
 * 
 * @author jacky
 * 
 */
public class RaindropFactoryBean {
	private FactoryConfig factoryConfig;

	private ITaoClientFactory taoClientFactory;

	private ICounterService counterService;

	public RaindropFactoryBean() {

	}

	public FactoryConfig getFactoryConfig() {
		return factoryConfig;
	}

	public void setFactoryConfig(FactoryConfig factoryConfig) {
		this.factoryConfig = factoryConfig;
	}

	private ITaoClientFactory getTaoClientFactory() throws MetaClientException {
		if (null == taoClientFactory) {
			taoClientFactory = new TaoClientMetaqFactory(factoryConfig);
		}
		return taoClientFactory;
	}

	/**
	 * 创建计数的服务
	 * 
	 * @return
	 */
	public ICounterService createCounterService() {
		if (null == counterService) {
			try {
				counterService = getTaoClientFactory().createCounterService();
			} catch (MetaClientException e) {
				Logger.getLogger(this.getClass()).error(e.getMessage(), e);
			}
		}
		return counterService;
	}
}
