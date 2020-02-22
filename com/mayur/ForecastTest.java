package com.mayur;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.forecastquery.AmazonForecastQueryClientBuilder;
import com.amazonaws.services.forecastquery.model.DataPoint;
import com.amazonaws.services.forecastquery.model.Forecast;
import com.amazonaws.services.forecastquery.model.QueryForecastRequest;
import com.amazonaws.services.forecastquery.model.QueryForecastResult;

public class ForecastTest {
	AmazonForecastQueryClientBuilder ar = AmazonForecastQueryClientBuilder.standard();
	QueryForecastResult test(QueryForecastRequest fr) {
		AWSCredentialsProvider awsCreds = new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIAICKU5XLI4PASGDBQ", "FV7FdqfrPVQuISjuHqmZ67/uDpFHbDmdsd2U8DyM"));
		
		 ar.setCredentials(awsCreds);
		 ar.setRegion("ap-southeast-1");
		 //ar.setEndpointConfiguration(new EndpointConfiguration("forecastquery", "ap-southeast-1"));
		 
		 return ar.build().queryForecast(fr);		 
	}
	
	public static void main(String ar[]) {
		Map<String, String> filters= new HashMap<String, String>();
		filters.put("item_id", "Pr1");
		
		
		QueryForecastRequest fr = new QueryForecastRequest(); 
		fr.setForecastArn("arn:aws:forecast:ap-southeast-1:291590127568:forecast/budget_forecast");
		fr.setFilters(filters);
		fr.setStartDate(null);
		fr.setEndDate(null);
		
		ForecastTest t = new ForecastTest();
		 QueryForecastResult res = t.test(fr);
		 Forecast f = res.getForecast();
		 
		 Map<String,List<DataPoint>> predictions = f.getPredictions();
		 
		 for (Entry<String, List<DataPoint>> entry : predictions.entrySet())  
	            System.out.println("Key = " + entry.getKey() + 
	                             ", Value = " + entry.getValue()); 
		 
		/*
		 * for (Regions c : Regions.values())
		 * 
		 * System.out.println("Key = " + c.getName() + ", Value = " + c.getName());
		 */
		 
	}
	
	
}
