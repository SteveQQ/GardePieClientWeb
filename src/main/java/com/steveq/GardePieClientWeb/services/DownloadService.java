package com.steveq.GardePieClientWeb.services;

import com.steveq.GardePieClientWeb.communication.JsonProcessor;
import com.steveq.GardePieClientWeb.communication.models.FromClientRequest;
import com.steveq.GardePieClientWeb.communication.models.Section;
import com.steveq.GardePieClientWeb.communication.models.ToClientResponse;
import com.steveq.GardePieClientWeb.database.Repository;
import com.steveq.GardePieClientWeb.database.SectionsRepository;
import com.steveq.GardePieClientWeb.weather.model.WeatherOutputModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2017-08-20.
 */
@Service("download_service")
public class DownloadService implements MyService {
    private Repository repository;

    public DownloadService(){
        repository = new SectionsRepository();
    }

    @Override
    public ToClientResponse processRequest(FromClientRequest request) {
        List<Section> requestedSections = request.getPayload();
        List<Integer> sectionsNums = new ArrayList<>();
        for(Section section : requestedSections){
            sectionsNums.add(section.getNumber());
        }

        List<Section> downloadedSections = new ArrayList<>();

        for(Integer i : sectionsNums){
            downloadedSections.add(repository.getSectionById(i));
        }

        ToClientResponse response = new ToClientResponse();
        response.setMethod(JsonProcessor.Method.DOWNLOAD.toString());
        response.setPayload(downloadedSections);
        response.setWeather(new WeatherOutputModel());

        return response;
    }
}
