package com.tfr.rms.controller;

import com.tfr.rms.config.Constants;
import com.tfr.rms.config.Routes;
import com.tfr.rms.model.ServiceInfo;
import com.tfr.rms.service.PropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Erik on 9/1/2016.
 */

@RestController
public class ServiceInfoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PropertiesService propertiesService;

    @RequestMapping(value= Routes.SERVICE_INFO,
            produces= Constants.APPLICATION_JSON,
            method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ServiceInfo getServiceInfo() {
        logger.debug("hitting endpoint: " + Routes.SERVICE_INFO);
        return propertiesService.getServiceInfo();
    }


}
