package cn.alauwahios.front.server;

import cn.alauwahios.front.extension.Extensions;

public class ServiceProviderFactory {
	
	/**
	 * 通过名称获取serviceProvider实例
	 * @param providerName
	 * @return
	 */
	public static ServiceProvider getServiceProvider(String providerName){
		ServiceProvider serviceProvider = Extensions.loadExtension(ServiceProvider.class);
		serviceProvider.init();
		return serviceProvider;
	}

}
