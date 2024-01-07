/*
 * package com.ubilink.controller;
 * 
 * import org.apache.log4j.Logger; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RequestParam;
 * 
 * import com.ubilink.model.Favoritetype; import com.ubilink.model.Mapfavourite;
 * import com.ubilink.model.User; import
 * com.ubilink.service.MapfavouriteServiceImpl;
 * 
 * @Controller
 * 
 * @RequestMapping(value="/requestFavourite") public class FavouriteController {
 * 
 * @Autowired private MapfavouriteServiceImpl mapfavouriteService;
 * 
 * private static final Logger logger =
 * Logger.getLogger(FavouriteController.class);
 * 
 * @RequestMapping(value="/markFavourite",method=RequestMethod.GET) public
 * ResponseEntity<String> markFavourite(@RequestParam(value="entityId",
 * required=true) int entityId ,
 * 
 * @RequestParam(value="favouriteEntity", required=true) String favouriteEntity,
 * 
 * @RequestParam(value="userId", required=true) int userId){
 * logger.debug("Requested markFavourite to with entityId "
 * +entityId+" and entity type "+favouriteEntity); String result="failed";
 * Mapfavourite mapfavourite=new Mapfavourite(); User trackedByUser=new User();
 * trackedByUser.setId(userId); mapfavourite.setUser(trackedByUser);
 * 
 * Favoritetype favoritetype=mapfavouriteService.findByType(favouriteEntity);
 * mapfavourite.setFavoritetype(favoritetype);
 * mapfavourite.setMapFavouriteEntityId(entityId);
 * mapfavourite.setIsActive(true); try { synchronized( this ) { Mapfavourite
 * mapfavouriteToUpdate=mapfavouriteService.
 * findByUserAndFavoritetypeAndMapFavouriteEntityId(trackedByUser, favoritetype,
 * entityId); if(mapfavouriteToUpdate!=null ) { try {
 * mapfavouriteService.delete(mapfavouriteToUpdate.getId());
 * result=favouriteEntity+" untracked"; } catch (Exception e) {
 * 
 * result="Failed"; }
 * 
 * } else { Mapfavourite
 * mapfavouriteCreated=mapfavouriteService.create(mapfavourite);
 * if(mapfavouriteCreated!=null) result=favouriteEntity+" Tracked"; } }
 * 
 * } catch (Exception e) {
 * logger.error("Requested markFavourite to with entityId "
 * +entityId+" and entity type "+favouriteEntity); e.printStackTrace(); }
 * HttpHeaders responseHeaders = new HttpHeaders();
 * responseHeaders.set("Access-Control-Allow-Origin", "*");
 * responseHeaders.set("Cache-Control", "private");
 * responseHeaders.set("Content-Type", "plain/text; charset=UTF-8");
 * logger.info("Result of markFavourite "+result); return new
 * ResponseEntity<String>(result, responseHeaders, HttpStatus.OK); }
 * 
 * 
 * }
 */