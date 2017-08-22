package com.steveq.GardePieClientWeb.controller;

import com.steveq.GardePieClientWeb.communication.models.FromClientRequest;
import com.steveq.GardePieClientWeb.communication.models.ToClientResponse;
import com.steveq.GardePieClientWeb.services.DownloadService;
import com.steveq.GardePieClientWeb.services.MyService;
import com.steveq.GardePieClientWeb.services.ScanService;
import com.steveq.GardePieClientWeb.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Adam on 2017-08-20.
 */
@RestController
public class SectionsScanController {

    @Autowired
    @Qualifier("scan_service")
    private MyService service;

    @RequestMapping(value = "/scan", method = RequestMethod.POST)
    public ToClientResponse scanSectionsConnected(FromClientRequest request){
        System.out.println("SCANNING...");
        System.out.println("REQUEST : " + request);
        return service.processRequest(request);
    }

}
