package com.steveq.GardePieClientWeb.controller;

import com.steveq.GardePieClientWeb.communication.models.FromClientRequest;
import com.steveq.GardePieClientWeb.communication.models.ToClientResponse;
import com.steveq.GardePieClientWeb.services.MyService;
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
public class SectionsUploadController {

    @Autowired
    @Qualifier("upload_service")
    private MyService service;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ToClientResponse uploadSectionsData(FromClientRequest request){
        System.out.println("UPLOADING...");
        System.out.println("REQUEST : " + request.toString());
        return service.processRequest(request);
    }
}
