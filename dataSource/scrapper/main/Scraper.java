package com.corona.scrapper.main;

import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import com.corona.scrapper.db.DBconnector;

public class Scraper {
    final static Statement st = DBconnector.getStatement();
	
    public static void main(String args[]) {
		Scraper scrap = new Scraper();
		scrap.india("https://www.mohfw.gov.in");
		scrap.world("https://www.worldometers.info/coronavirus/");
		
	}//end of main method
	
    public void world(final String url) {
    	//deleteAll();
    	try {
    		final Document document = Jsoup.connect(url).get();
			Elements rows =document.select("table#main_table_countries_today tr");
			for(int i=0,j=0; j<=7 ;j++)
			{
			  rows.remove(i);
			}
			for(int i=0;i<=7;i++)
			{
				rows.remove(rows.size()-1);
			}
			System.out.println("world stats");
			deleteAll();
			for(Element row : rows)
			{
				String country_name,confirmed,death,recovered,active;
			    country_name=row.select("td:nth-of-type(1)").text();
				
				confirmed=row.select("td:nth-of-type(2)").text();
				
				if(row.select("td:nth-of-type(4)").text().equals(""))
					death ="0";
				else	
				 death=row.select("td:nth-of-type(4)").text();
				
				if(row.select("td:nth-of-type(6)").text().equals(""))
					recovered ="0";
				else	
				recovered=row.select("td:nth-of-type(6)").text();
				
				if(row.select("td:nth-of-type(7)").text().equals(""))
					active ="0";
				else	
				 active =row.select("td:nth-of-type(7)").text();
				
				insertData(country_name, confirmed, recovered,active, death);
				
				System.out.println(country_name+" "+ confirmed+" " +active+" "+recovered+" "+death );
			}
		} catch (Exception e) {
			System.out.println(e);
		}
    }// end of world method
    public void insertData(String country_name, String confirmed, String recovered,String active, String death) {
    	
    	try {
			String query="INSERT into world(country_name, confirmed,active, recovered, death)"
	   	 	 + "values('"+country_name+"','"+confirmed+"','"+active+"','"+recovered+"','"+death+"')";
		 
			st.execute(query);
    	} catch (SQLException e) {
    		System.out.println(e);
		}
    }
	public void india(final String url) 
	{
		
		try {
			final Document document = Jsoup.connect(url).get();
			Elements row1 =document.select("section#state-data tr");
			row1.remove(row1.size()-1);	
			row1.remove(row1.size()-1);
			Element total = row1.last();
			String total_confirmed=total.select("td:nth-of-type(2)").text();
			total_confirmed = total_confirmed.substring(0, total_confirmed.length()-1);
			String total_recovered=total.select("td:nth-of-type(3)").text();
			String total_death=total.select("td:nth-of-type(4)").text();
			System.out.println("india stats");
			System.out.println(total_confirmed+" "+total_recovered+" "+total_death);
			updateData("Total", total_confirmed, total_recovered, total_death);
			row1.remove(row1.size()-1);
			
			for(Element row : row1)
		     {
				
				if (row.select("td:nth-of-type(1)").text().equals("")) {
					continue;
				} else {
	               // final String Sno=row.select("td:nth-of-type(1)").text();  
	                
	                
	                final String state =row.select("td:nth-of-type(2)").text();
	                
	                
	                final String confirm =row.select("td:nth-of-type(3)").text();
	                
	                
	                final String recovered =row.select("td:nth-of-type(4)").text();
	                
	               
	               final String death =row.select("td:nth-of-type(5)").text();
	                
	                updateData(state, confirm, recovered, death);
	                System.out.println(state +" " +confirm + " " +recovered+ " " +death);
	                
				}// end of else
		     }//end of foreach
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}// end of india method
	
	public void updateData(String state_name , String confirmed , String recovered , String death) 
	{
		
		int itne = Integer.parseInt(confirmed);
		int utne = Math.abs(Integer.parseInt(recovered) +Integer.parseInt(death));
		String active =Integer.toString(itne - utne);
		
		try
		{
			
		  String query="UPDATE india SET confirmed='"+confirmed+"',active='"+active+"',"
		  		                         + "recovered='"+recovered+"', death='"+death+"'"
		  		                         + "WHERE state_name='"+state_name+"'";
		  
		 
			st.execute(query);
		}catch(SQLException e)
		{
			System.out.println(e);
		}
	}// end of insert data
	public void deleteAll()
	{
		
		String query="delete from world";
		try {
		st.execute(query);
		}catch (SQLException e) {
			System.out.println(e);
			// TODO: handle exception
		}
	}//end of delete method
}//end of scrapper class
