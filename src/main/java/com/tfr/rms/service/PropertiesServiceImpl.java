package com.tfr.rms.service;

import com.tfr.rms.config.ServiceProperties;
import com.tfr.rms.model.ServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Erik on 9/1/2016.
 */

@Service
public class PropertiesServiceImpl implements PropertiesService {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesServiceImpl.class);

    private ServiceProperties properties = new ServiceProperties();

    @Override
    public ServiceInfo getServiceInfo() {
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setName(properties.getProperty("rms.name"));
        serviceInfo.setDescription(properties.getProperty("rms.description"));
        serviceInfo.setVersion(properties.getProperty("rms.version"));
        return serviceInfo;
    }
}
