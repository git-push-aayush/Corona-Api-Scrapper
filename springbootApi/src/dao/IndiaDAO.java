package com.corona.tracker.api.status.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import com.corona.tracker.api.status.db.DBconnector;
import com.corona.tracker.api.status.dto.IndiaDTO;
import com.corona.tracker.api.status.dto.TotalDTO;


public class IndiaDAO {
	Statement st= DBconnector.getStatement();
	
	public ArrayList<Object> fetchData(){
		ArrayList<Object> status_india = new ArrayList<Object>();
		
		
		try {
			ResultSet total = data("state_name = 'Total'");
			if(total.next())
			{
				TotalDTO indiaTotal = new TotalDTO();
				indiaTotal.setStatus("India");
				indiaTotal.setTotal_confirmed(total.getString(3));
				indiaTotal.setTotal_active(total.getString(4));
				indiaTotal.setTotal_recovered(total.getString(5));
				indiaTotal.setTotal_death(total.getString(6));
				status_india.add(indiaTotal);
			}
			ResultSet states = data("state_name != 'Total'");
			while(states.next()) {
				IndiaDTO stateWise = new IndiaDTO();
				stateWise.setId(states.getString(1));
				stateWise.setState(states.getString(2));
				stateWise.setConfirmed(states.getString(3));
				stateWise.setActive(states.getString(4));
				stateWise.setRecovered(states.getString(5));
				stateWise.setDeath(states.getString(6));
				
				status_india.add(stateWise);
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
		return status_india;
	} 

	
	public ResultSet data(String filter) 
	{
		try {
			String query= "select * from india where "+ filter;
			ResultSet data=st.executeQuery(query);
			return data;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	} 
}
