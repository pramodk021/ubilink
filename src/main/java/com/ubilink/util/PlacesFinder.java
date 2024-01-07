package com.ubilink.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ubilink.model.Place;

public class PlacesFinder 
{
	private static final Logger logger = Logger.getLogger(PlacesFinder.class);
	public List<Place> getMalls(String atLat,String atLong)
	{
		ArrayList<Place>malls=new ArrayList<Place>();
		List<Place>currentMalls=new ArrayList<Place>();
		StringBuilder builder=new StringBuilder();
		Map<String,double[]> geoPoints=new HashMap<String,double[]>();
		String getMallsURL="https://maps.googleapis.com/maps/api/place/search/json?location="+atLat+","+atLong+"&radius=1000&sensor=true&key=AIzaSyBxUP3IgJv6lzywN9VWjWaB7ggRDi90T8Q";
	try {
		 
		URL url = new URL(getMallsURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
 
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			builder.append(output);
			//System.out.println(output);
		}
		JSONObject result=new JSONObject(builder.toString());
		 
		conn.disconnect();
		JSONArray jsonArray=(JSONArray)result.getJSONArray("results");
		
		Place place=null;
		for(int i=0;i<jsonArray.length();i++)
		{
			try
			{
			place=new Place();
			JSONObject jsonObject=(JSONObject)jsonArray.get(i);
			place.setName(((String)jsonObject.get("name")));
			//place.setCategoryImg(jsonObject.getString("icon"));
			String address=jsonObject.getString("vicinity");
			
			place.setAddress(address);
			place.setGpId((((String)jsonObject.get("id"))));
			JSONObject jsonGeo=jsonObject.getJSONObject("geometry").getJSONObject("location");
			
				
				String lat=jsonGeo.get("lat")+"";
				String log=jsonGeo.get("lng")+"";
				place.setLocation(lat+","+log);
				
				if(logger.isDebugEnabled()){
					logger.debug("Place lat lon  = "+lat+" , "+log);
				}
				JSONArray types=(JSONArray)jsonObject.getJSONArray("types");
				List<String> categories=place.getPlaceCategories();
				for(int j=0;j<types.length();j++)
				{
					
					String category=(String)types.get(j);
					categories.add(category);
				}
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
			malls.add(place);
		}
 
		
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	  } catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return malls;
	}
	
	/*public int[] getMallIds(List<Place> malls)
	{
		//String couponCode="empty";
		Connection connection=null;
		Statement statement=null; 
		int[] mallIds=new int[malls.size()];
		try
		{
			connection=DBConnection.getConnection();
			statement=connection.createStatement();
			
			for(int i=0;i<malls.size();i++)
			{
				String query="Select mallid from place where gpaMallid='"+malls.get(i).getGPAMallId()+"'";
				ResultSet resultSet=statement.executeQuery(query);
				while(resultSet.next())
				{
					mallIds[i]=resultSet.getInt("mallid"); 
				}
				System.out.println(query);
			}
			//
			
			
		}
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(statement!=null)
		        	 statement.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(connection!=null)
		        	 statement.close();
		         statement=null;
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
		
		return mallIds;
	}*/
	
	/*public static class MallList
	{
		private List<MallSer>results;

		public List<MallSer> getResults() {
			return results;
		}

		public void setResults(List<MallSer> results) {
			this.results = results;
		}
		
		public static class MallSer
		{
			private int id;
			private String name;
			private String location;
			private String address;
			private String description;
		}
		
	}*/
}
