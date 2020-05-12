package com.corona.tracker.api.status.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.corona.tracker.api.status.db.DBconnector;
import com.corona.tracker.api.status.dto.TotalDTO;
import com.corona.tracker.api.status.dto.WorldDTO;

public class WorldDAO {
  Statement st = DBconnector.getStatement();
  
  public ArrayList<Object> fetchData(){
	  ArrayList<Object> status_world = new ArrayList<Object>();
	  
	  try {
		  ResultSet total = data("country_name = 'World'");
		  if(total.next())
			{
				TotalDTO worldTotal = new TotalDTO();
				worldTotal.setStatus("World");
				worldTotal.setTotal_confirmed(total.getString(2));
				worldTotal.setTotal_active(total.getString(3));
				worldTotal.setTotal_recovered(total.getString(4));
				worldTotal.setTotal_death(total.getString(5));
				
				status_world.add(worldTotal);
			}
			ResultSet countries = data("country_name != 'World'");
			while(countries.next()) {
				WorldDTO countryWise = new WorldDTO();
				countryWise.setCountry(countries.getString(1));
				countryWise.setConfirmed(countries.getString(2));
				countryWise.setActive(countries.getString(3));
				countryWise.setRecovered(countries.getString(4));
				countryWise.setDeath(countries.getString(5));
				
				status_world.add(countryWise);
			}
		
	} catch (Exception e) {
		
	}
	return status_world;  
  }

	public ResultSet data(String filter) 
	{
		try {
			String query= "select * from world where "+ filter;
			ResultSet data=st.executeQuery(query);
			return data;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	} 
}
