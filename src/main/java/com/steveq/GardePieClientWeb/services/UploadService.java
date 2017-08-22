package com.steveq.GardePieClientWeb.services;

import com.steveq.GardePieClientWeb.alarms.AlarmsCreator;
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
@Service("upload_service")
public class UploadService implements MyService {
    private Repository repository;
    private AlarmsCreator alarmsCreator;

    public UploadService(){
        repository = new SectionsRepository();
        alarmsCreator = new AlarmsCreator();
    }

    @Override
    public ToClientResponse processRequest(FromClientRequest request) {
        List<Section> affectedSections = new ArrayList<>();
        List<Section> sections = request.getPayload();
        alarmsCreator.resetTimer();
        for(Section section : sections){
            Section sectionFromDB = repository.getSectionById(section.getNumber());
            if(sectionFromDB.getDays() == null){
                System.out.println("CREATE SECTION ON UPLOAD");
                repository.createSection(section);
                repository.createSectionDays(section, section.getDays());
                repository.createSectionTimes(section, section.getTimes());
            } else {
                System.out.println("UPDATE SECTION ON UPLOAD");
                repository.updateSection(section);
                repository.updateSectionDays(section, section.getDays());
                repository.updateSectionTimes(section, section.getTimes());
            }
            affectedSections.add(section);
        }
        alarmsCreator.registerAlarmForSection(Integer.valueOf(request.getDuration()));
        ToClientResponse response = new ToClientResponse();
        response.setMethod(JsonProcessor.Method.UPLOAD.toString());
        response.setPayload(affectedSections);
        response.setWeather(new WeatherOutputModel());
        return response;
    }
}
