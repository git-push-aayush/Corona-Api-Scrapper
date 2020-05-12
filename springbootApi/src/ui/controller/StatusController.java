package com.corona.tracker.api.status.ui.controller;

import java.util.ArrayList;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corona.tracker.api.status.dao.IndiaDAO;
import com.corona.tracker.api.status.dao.WorldDAO;

@RestController
@RequestMapping("")
public class StatusController {

	@GetMapping("/")
	public String root() {
		return "\t\tCovid-19 Tracker \n For India Stats:https://covid-19-status-tracker.herokuapp.com/india \n\n"
				+ "For World Stats:https://covid-19-status-tracker.herokuapp.com/world";
	}
	@GetMapping("/india")
	public ArrayList<Object> getIndiaStatus()
	{
		
		IndiaDAO indiaStatus = new IndiaDAO();
		return indiaStatus.fetchData();
	}
	
	@GetMapping("/world")
	public ArrayList<Object> getWorldStatus()
	{
	    WorldDAO worldStatus = new WorldDAO();	
		return worldStatus.fetchData();
	}
}
