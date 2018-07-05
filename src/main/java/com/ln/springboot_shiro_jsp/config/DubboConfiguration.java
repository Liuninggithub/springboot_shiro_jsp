package com.ln.springboot_shiro_jsp.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboConfiguration {



    @Value("${dubbo.register.address}")
    private String registerAddress;

    @Value("${dubbo.register.cacheFile}")
    private String registerCacheFile;

    @Value("${dubbo.application.name}")
    private String applicationName;

    @Value("${dubbo.application.owner:interlink}")
    private String applicationOwner;

    @Value("${dubbp.application.organization:interlink}")
    private String applicationOrganization;

    @Value("${dubbo.protocol.name:dubbo}")
    private String protocolName;

    @Value("${dubbo.protocol.port}")
    private Integer protocolPort;

    @Value("${dubbo.timeout}")
    private Integer timeout;

    @Value("${dubbo.retries}")
    private Integer retries;

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(registerAddress);
        registryConfig.setProtocol("zookeeper");
        registryConfig.setFile(registerCacheFile);
        return registryConfig;
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setLogger("slf4j");
        applicationConfig.setName(applicationName);
        applicationConfig.setOwner(applicationOwner);
        applicationConfig.setOrganization(applicationOrganization);
        return applicationConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig=new ProtocolConfig();
        protocolConfig.setName(protocolName);
        protocolConfig.setPort(protocolPort);
        return protocolConfig;
    }

    @Bean
    public ProviderConfig providerConfig(){
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(timeout);
        providerConfig.setRetries(retries);
        return providerConfig;
    }

}
