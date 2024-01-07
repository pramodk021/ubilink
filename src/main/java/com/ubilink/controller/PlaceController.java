package com.ubilink.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubilink.model.Event;
import com.ubilink.model.Favoritetype;
import com.ubilink.model.Mapfavourite;
import com.ubilink.model.Mapplacecategory;
import com.ubilink.model.Place;
import com.ubilink.model.Placecategory;
import com.ubilink.model.User;
import com.ubilink.service.MapfavouriteService;
import com.ubilink.service.MapplacecategoryService;
import com.ubilink.service.OfferService;
import com.ubilink.service.PlaceCatService;
import com.ubilink.service.PlaceService;
import com.ubilink.util.CommonUtilities;
import com.ubilink.util.DateUtil;
import com.ubilink.util.PlacesFinder;
import com.ubilink.util.UbiConstants;

@Controller
@RequestMapping(value="/requestPlace")
public class PlaceController {
	private static final Logger logger = Logger.getLogger(PlaceController.class);
	@Autowired
	private PlaceService mallService;
	@Autowired
	private OfferService offerService;
	@Autowired
	private PlaceCatService placeCatService;
	@Autowired
	private MapplacecategoryService mapplacecategoryService;
	@Autowired
	private MapfavouriteService mapfavouriteService;
	
	@RequestMapping(value = "/createPlace", method = RequestMethod.POST, consumes="multipart/form-data", produces="application/json")
	public ResponseEntity<String> createPlace(
	        @RequestParam(value="placeImg" ,required=false) MultipartFile placeImg,
	        @RequestParam(value ="logoImg",required=false ) MultipartFile logoImg,
	        @RequestParam(value="name" ,required=true) String name,
	        @RequestParam(value="location",required=true) String location,
			@RequestParam(value="address",required=true) String address,
			@RequestParam(value="description",required=false) String description,
			@RequestParam(value="hotspotId",required=true) int hotspotId,
			@RequestParam(value="placecategoryId",required=true) int placecategoryId,
			@RequestParam(value="workingTime",required=false) String workingTime,
			@RequestParam(value="contact",required=false) String contact,
			@RequestParam(value="userId",required=false) int userId) {
		HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.set("Access-Control-Allow-Origin", "*"); 
    	responseHeaders.set("Cache-Control", "private");
    	responseHeaders.set("Content-Type", "application/json");
    	responseHeaders.set("Accept", "application/json");
		logger.debug("Create place requested");
	    int imageSizeToScale=500;
	    InputStream inputStream = null;
		Place place=new Place();
		Place createdMall=null;
		
		try {
			//Reject request if Image size > 4 MB
			if(placeImg!=null && placeImg.getBytes().length>4194304)
				return new ResponseEntity<String>("Image size should less than 4 MB", responseHeaders, HttpStatus.CONFLICT);
			if(logoImg!=null && logoImg.getBytes().length>4194304)
				return new ResponseEntity<String>("Image size should less than 4 MB", responseHeaders, HttpStatus.CONFLICT);
			
			place.setName(name);
			place.setDescription(description);
			Placecategory placecategory=new Placecategory();
			placecategory.setId(placecategoryId);
			place.setCreatedBy(userId);
			place.setWorkingTime(workingTime);
			place.setAddress(address);
			place.setLocation(location);
			place.setContact(contact);
			place.setHotspotId(hotspotId);
			if(placeImg!=null && !placeImg.isEmpty())
			{
				inputStream = placeImg.getInputStream();
				//Compress/Resize Image before saving in DB
				//byte[] placeImgBytes=CommonUtilities.scaleImage(ImageIO.read(inputStream), imageSizeToScale);
				//place.setImgFile(placeImgBytes);
			}
			if(logoImg!=null && !logoImg.isEmpty())
			{
				inputStream=logoImg.getInputStream();
				//Compress/Resize Image before saving in DB
				byte[] logoImgBytes=CommonUtilities.scaleImage(ImageIO.read(inputStream), imageSizeToScale);
				place.setLogoImg(logoImgBytes);
			}
			createdMall=mallService.create(place);
			if(createdMall!=null && createdMall.getId()>0)
			{
				Mapplacecategory mapplacecategory=new Mapplacecategory();
				mapplacecategory.setPlace(place);
				mapplacecategory.setPlacecategory(placecategory);
				mapplacecategoryService.create(mapplacecategory);
			}
			
		} catch (Exception e) {
				logger.error("Exception in create place "+e.toString()+"place values="+place.toString());
				
			e.printStackTrace();
			return new ResponseEntity<String>("Internal Server Error", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
    		logger.info(place.getName()+" has been created successfully!");
        return new ResponseEntity<String>(createdMall.getName()+" has been created successfully!", responseHeaders, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/place",method=RequestMethod.GET)
    public ResponseEntity<String> getMallById(@RequestParam(value="placeId", required=true) int placeId,
    		@RequestParam(value="userId", required=false) int userId) {
		String result="failed";
			logger.debug("Requested place's id is = "+placeId);
    	try {
    		Place place=mallService.findOne(placeId);
    		//List<Event>events=mallService.findAllEventsByMall(placeId);
    		if(place!=null )
    			result=writePlaceToJsonObject(place,null,userId);
		} catch (Exception e) {
				logger.error("Exception in getMallById with placeId= "+placeId);
				//result="Exception in getMallById with placeId= "+placeId +" Exception trace:=  "+e.getMessage();
			e.printStackTrace();
		}
    	HttpHeaders responseHeaders = new HttpHeaders();
    	
    	responseHeaders.set("Access-Control-Allow-Origin", "*"); 
    	responseHeaders.set("Cache-Control", "private");
    	responseHeaders.set("Content-Type", "plain/text; charset=UTF-8");
    	
    		logger.info("returning place :" +result);
       
        return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
    }
	
	@RequestMapping(value="/places",method=RequestMethod.GET)
    public ResponseEntity<String> getPlacesAroundMe(@RequestParam(value="lat", required=true) String lat,
    		@RequestParam(value="lon", required=true) String lon,
    		@RequestParam(value="userId", required=true) int userId
    		) {
		
			logger.debug("requested getPlacesAroundMe :- lat , lon ="+lat+","+lon);
		
		String result="failed";
		int hotspotId=createHotspot(new Place());
		List<Place>mallsToReturn=new ArrayList<Place>();
		
		List<Place>malls=new PlacesFinder().getMalls(lat, lon);
		List<String>nonApplicableCats=placeCatService.findCategoryByIsApplicable(false);
		for(Place place:malls)
		{
			String[]categories=(String[])place.getPlaceCategories().toArray(new String[place.getPlaceCategories().size()]);
			boolean isPlaceApplicable=true;
			for(String category:categories)
			{
				if(isPlaceApplicable==false)
					continue;
				if(nonApplicableCats.contains(category))
				{
					isPlaceApplicable=false;
				}
			}
			if(isPlaceApplicable)
			{
				mallsToReturn.add(place);
			}
			
		}
		
		mallsToReturn=getPlaces(mallsToReturn,hotspotId);
			logger.debug("Requested lat lon is = "+lat+" , "+lon);
    	try {
    			result=writePlaceToJsonArray(mallsToReturn,"place",userId);
		} catch (Exception e) {
				logger.error("Exception in getPlacesAroundMe");
			e.printStackTrace();
		}
    	HttpHeaders responseHeaders = new HttpHeaders();
    	
    	
    	responseHeaders.set("Access-Control-Allow-Origin", "*"); 
    	responseHeaders.set("Cache-Control", "private");
    	responseHeaders.set("Content-Type", "plain/text; charset=UTF-8");
    	
    	logger.info("getPlacesAroundMe result = "+result);
       
        return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
    }
	
	
	
	private int createHotspot(Place place)
	{
		return 611;
	}
	
	@RequestMapping(value="/hotspots",method=RequestMethod.GET)
    public ResponseEntity<String> getHotSpotsAroundMe() {
		
		logger.debug("requested getHotSpotsAroundMe");
		String result="failed";
		
    	try {
    			result=writePlaceToJsonArray(mallService.getHotspotsById(),"hotspot",0);
		} catch (Exception e) {
			logger.error("Exception in getHotSpotsAroundMe");
			e.printStackTrace();
		}
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.set("Access-Control-Allow-Origin", "*"); 
    	responseHeaders.set("Cache-Control", "private");
    	responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
    	
    	logger.info("hotspots : ="+ result);
       
        return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
    }
	
	@RequestMapping(value="/placesByHotspot",method=RequestMethod.GET)
    public ResponseEntity<String> getPlacesByHotspot(
    		@RequestParam(value="hotspotId", required=true) int hotspotId,
    		@RequestParam(value="pageNum", required=false) int pageNum,
    		@RequestParam(value="pageSize", required=false) int pageSize,
    		@RequestParam(value="userId", required=false) int userId
    		) {
		
		logger.debug("requested getPlacesByHotspot() with hotspotId , pageNum ,pageSize : "+ hotspotId+" ,"+pageNum+" ,"+pageSize);
		String result="failed";
		
    	try {
    			result=writePlaceToJsonArray(mallService.findMallByMall(hotspotId,pageNum,pageSize),"place",userId);
		} catch (Exception e) {
			logger.error("Exception in getPlacesByHotspot() with hotspotId , pageNum ,pageSize : "+ hotspotId+" ,"+pageNum+" ,"+pageSize);
			e.printStackTrace();
			
		}
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.set("Access-Control-Allow-Origin", "*"); 
    	responseHeaders.set("Cache-Control", "private");
    	responseHeaders.set("Content-Type", "plain/text; charset=UTF-8");
    	
    	logger.debug("place under requested hotspots are : ="+ result);
       
        return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
    }

	@RequestMapping(value="/favouritePlaces",method=RequestMethod.GET)
    public ResponseEntity<String> favouritePlaces(
    		@RequestParam(value="userId", required=true) int userId,
    		@RequestParam(value="pageNum", required=false) int pageNum,
    		@RequestParam(value="pageSize", required=false) int pageSize
    		) {
		
		logger.debug("requested favouritePlaces() with userId , pageNum ,pageSize : "+ userId+" ,"+pageNum+" ,"+pageSize);
		String result="failed";
		
    	try {
    			result=writePlaceToJsonArray(mallService.findFavouritePlaces(userId,"place",pageNum,pageSize),"place",userId);
		} catch (Exception e) {
			logger.error("Exception in favouritePlaces() with userId , pageNum ,pageSize : "+ userId+" ,"+pageNum+" ,"+pageSize);
			e.printStackTrace();
			
		}
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.set("Access-Control-Allow-Origin", "*"); 
    	responseHeaders.set("Cache-Control", "private");
    	responseHeaders.set("Content-Type", "plain/text; charset=UTF-8");
    	
    	logger.debug("place under requested hotspots are : ="+ result);
       
        return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
    }
	
	
	public String writePlaceToJsonObject(Place place,List<Event>mallEvents,int userId) throws IOException {  
		   
		    final ByteArrayOutputStream out = new ByteArrayOutputStream();
		    final ObjectMapper mapper = new ObjectMapper();
		    try
		    {
			    MallSer mallSer=new MallSer(place.getId(),place.getName(),place.getLocation(),place.getAddress(),place.getDescription());
			    mallSer.setOfferCount(offerService.getOfferCountByPlace(place.getId()));
			    
			    List<Mapplacecategory>mapplacecategories=new ArrayList<Mapplacecategory>();
			    		mapplacecategories.addAll(place.getMapplacecategories());
			    		
			     if(mapplacecategories.size()>0 && mapplacecategories.get(0).getPlacecategory().getCategoryImg()!=null)
			    	 mallSer.setImgPlaceCat(""+mapplacecategories.get(0).getPlacecategory().getId());
			    if(place.getImgFile()!=null)
			    mallSer.setImgURL(""+place.getId());
			    if(place.getLogoImg()!=null)
			    	mallSer.setLogoImg(""+place.getId());
			    
			    if(findMapfavouriteByUserAndFavoritetypeAndMapFavouriteEntityId(userId, place.getId())!=null)
			    {
			    	mallSer.setTracked(true);
			    }
			    
			    mallEvents=new ArrayList<Event>();
			    mallEvents.addAll(place.getEvents());
			    
			    List<EventSer> eventSers=new ArrayList<EventSer>();
			    
			    if(mallEvents.size()>0)
			    {
				    for(Event event: mallEvents)
				    {
				    	EventSer eventSer=new EventSer();
				    	eventSer.setDescription(event.getDescription());
				    	eventSer.setStartTime(DateUtil.getStringFromDate(event.getStartTime()));
				    	eventSer.setEndTime(DateUtil.getStringFromDate(event.getEndTime()));
				    	eventSer.setId(event.getId());
				    	eventSer.setPlaceId(place.getId());
				    	eventSer.setName(event.getName());
				    	eventSer.setEventImgURL(UbiConstants.EVENT_IMG+event.getId());
				    	eventSers.add(eventSer);
				    }
			    }
			    if(eventSers.size()>0)
			    mallSer.setEvents(eventSers);
			    mapper.writeValue(out, mallSer);
			    
		    }
		    catch(Exception exception)
		    {
		    	exception.printStackTrace();
		    	//return exception.getMessage();
		    }
		    final byte[] data = out.toByteArray();
		    out.flush();
		    return new String(data);
	}
		
	public String writePlaceToJsonArray(List<Place> malls,String listType,int userId) throws IOException {  
		   
	    final ByteArrayOutputStream out = new ByteArrayOutputStream();
	    final ObjectMapper mapper = new ObjectMapper();
	    List<MallSer>mallSerList=new ArrayList<MallSer>();
	    for(Place place:malls)
	    {
	    	String address=place.getAddress()!=null?place.getAddress():"";
	    	String[]addrArray=address.split(",");
			if(addrArray.length>=3)
			address=addrArray[addrArray.length-3]+", "+addrArray[addrArray.length-2]+", "+addrArray[addrArray.length-1];
	    	MallSer mallSer=new MallSer(place.getId(),place.getName(),place.getLocation(),address,place.getDescription());
	    	
	    	if(findMapfavouriteByUserAndFavoritetypeAndMapFavouriteEntityId(userId, place.getId())!=null)
		    {
		    	mallSer.setTracked(true);
		    }
	    	
	    	if(listType.equals("hotspot"))
	    	mallSer.setOfferCount(offerService.getOfferCountByHotspot(place.getId()));
	    	
	    	if(listType.equals("place"))
		    	mallSer.setOfferCount(offerService.getOfferCountByPlace(place.getId()));
	    	List<Mapplacecategory>mapplacecategories=new ArrayList<Mapplacecategory>();
    		mapplacecategories.addAll(place.getMapplacecategories());
    		
    		if(mapplacecategories.size()>0 && mapplacecategories.get(0).getPlacecategory().getCategoryImg()!=null)
    			mallSer.setImgPlaceCat(""+mapplacecategories.get(0).getPlacecategory().getId());
    		
	    	if(place.getLogoImg()!=null)
	    	{
	    		mallSer.setLogoImg(""+place.getId());
	    	}
	    	if(place.getImgFile()!=null)
	    	mallSer.setImgURL(""+place.getId());
	    	
	    	mallSerList.add(mallSer);
	    }
	    mapper.writeValue(out, mallSerList);
	    

	    final byte[] data = out.toByteArray();
	    return new String(data);
}
	
	private Mapfavourite findMapfavouriteByUserAndFavoritetypeAndMapFavouriteEntityId(int userId,int placeId)
	{
		Mapfavourite mapfavourite=new Mapfavourite();
		User trackedByUser=new User();
		trackedByUser.setId(userId);
		mapfavourite.setUser(trackedByUser);
		Favoritetype favoritetype=new Favoritetype();
		favoritetype.setId(2);
		mapfavourite.setFavoritetype(favoritetype);
		mapfavourite.setMapFavouriteEntityId(placeId);
		
		return mapfavouriteService.findByUserAndFavoritetypeAndMapFavouriteEntityId(trackedByUser,favoritetype,placeId);
		
	}
	
    public List<Place> getPlaces(List<Place>places,int hotspotId)
	{
		List<Place>placesToReturn=new ArrayList<Place>();
		for(Place place:places)
		{
			place.setHotspotId(hotspotId);
			Place availableMall=mallService.findByGpId(place.getGpId());
			
			//String[]categories=(String[])place.getMapplacecategories().toArray(new String[place.getMapplacecategories().size()]);
			if(availableMall==null)
			{
				List<String>placeCategories=place.getPlaceCategories();
				availableMall=mallService.create(place);
				if(availableMall.getId()>0)
				{
					Set<Mapplacecategory> mapplacecategories=new HashSet<Mapplacecategory>();
					Map<String,Integer>placeCats=placeCatService.findCategoryAndIdByIsApplicable(true);
					for(String category:placeCategories)
					{
						if(placeCats.containsKey(category))
						{
							Placecategory placecategory=new Placecategory();
							placecategory.setId(placeCats.get(category));
							Mapplacecategory mapplacecategory=new Mapplacecategory();
							mapplacecategory.setPlace(place);
							mapplacecategory.setPlacecategory(placecategory);
							mapplacecategoryService.create(mapplacecategory);
							if(mapplacecategory.getId()>0)
								mapplacecategories.add(mapplacecategory);
						}
					}
					availableMall.setMapplacecategories(mapplacecategories);
				}
			}
			if(availableMall!=null)
			{
				
				/*if(availableMall.getPlaceCategories().size()<1)
				availableMall.setPlaceCategories(mapplacecategoryService.findCategoryByPlaceId(availableMall.getId()));*/
				placesToReturn.add(availableMall);
			}
		}
		return placesToReturn;
	}

	public static class MallSer implements Serializable
	{
		private int id;
		private boolean isTracked=false;
		private String name;
		private String location;
		private String address;
        private String imgPlaceCat="";
		private String description;
		private String logoImg="";
		private String imgURL="";
		private List<EventSer>events=new ArrayList<EventSer>() ;
		private long offerCount;
		public MallSer()
		{
			
		}
		
		public MallSer(int id, String name, String location, String address,
				String description) {
			super();
			this.id = id;
			this.name = name;
			this.location = location;
			this.address = address;
			this.description=description;
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getLogoImg() {
			return logoImg;
		}

		public void setLogoImg(String logoImg) {
			this.logoImg = logoImg;
		}

		public long getOfferCount() {
			return offerCount;
		}

		public void setOfferCount(long offerCount) {
			this.offerCount = offerCount;
		}


		public List<EventSer> getEvents() {
			return events;
		}

		public void setEvents(List<EventSer> events) {
			this.events = events;
		}

		public String getImgURL() {
			return imgURL;
		}

		public void setImgURL(String imgURL) {
			this.imgURL = imgURL;
		}

		public String getImgPlaceCat() {
			return imgPlaceCat;
		}

		public void setImgPlaceCat(String imgPlaceCat) {
			this.imgPlaceCat = imgPlaceCat;
		}

		public boolean isTracked() {
			return isTracked;
		}

		public void setTracked(boolean isTracked) {
			this.isTracked = isTracked;
		}
		
	}
	
	public static class EventSer implements Serializable
	{
		private int id;
		private String startTime;
		private String endTime;
		private String description;
		private String eventImgURL;
		private byte[]imgEvent;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public byte[] getImgEvent() {
			return imgEvent;
		}
		public void setImgEvent(byte[] imgEvent) {
			this.imgEvent = imgEvent;
		}
		
		public String getEventImgURL() {
			return eventImgURL;
		}
		public void setEventImgURL(String eventImgURL) {
			this.eventImgURL = eventImgURL;
		}

		public int getPlaceId() {
			return placeId;
		}
		public void setPlaceId(int placeId) {
			this.placeId = placeId;
		}

		private int placeId;
		private String name;
	}
	
}
	

