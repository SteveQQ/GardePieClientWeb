package com.steveq.GardePieClientWeb;

import com.steveq.GardePieClientWeb.alarms.AlarmsCreator;
import com.steveq.GardePieClientWeb.utils.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GardePieClientWebApplication {

	private static AlarmsCreator alarmsCreator;
	public static void main(String[] args) {
		alarmsCreator = new AlarmsCreator();

		alarmsCreator.registerAlarmForSection(Integer.valueOf(FileUtils.getDurationProperty()));
		SpringApplication.run(GardePieClientWebApplication.class, args);
	}
}
