package com.steveq.GardePieClientWeb.controller;

import com.steveq.GardePieClientWeb.communication.models.FromClientRequest;
import com.steveq.GardePieClientWeb.communication.models.ToClientResponse;
import com.steveq.GardePieClientWeb.services.DownloadService;
import com.steveq.GardePieClientWeb.services.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Adam on 2017-08-20.
 */
@RestController
public class SectionsDownloadController {

    @Autowired
    @Qualifier("download_service")
    private MyService service;

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ToClientResponse downloadSectionsData(FromClientRequest request){
        System.out.println("DOWNLOADING...");
        System.out.println("REQUEST : " + request);
        return service.processRequest(request);
    }
}
