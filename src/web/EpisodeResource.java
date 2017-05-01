package web;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;

import comms.ResponseObject;
import entity.Client;
import entity.Episode;

import javax.ws.rs.PathParam;

import service.EpisodeService;
import service.UserService;


@Path("/episodes")
public class EpisodeResource {

	@EJB
	EpisodeService episodeService;
	
	@EJB
	UserService userService;
	
	@GET
	@Path("/{id}")
	public Response getEpisode(@PathParam("id") int id) {
		Episode e = episodeService.getById(id);
		if(e != null)
			return Response.status(Response.Status.OK).entity(e).build();
		return Response.status(Response.Status.NOT_FOUND).entity(new ResponseObject("Episode does not exist!")).build();
	}
	
	@GET
	@Path("/series/{id}")
	public Response getEpisodesBySeriesId(@PathParam("id") int id) {
		List<Episode> e = episodeService.getBySeriesId(id);
		if(e != null)
			return Response.status(Response.Status.OK).entity(e).build();
		return Response.status(Response.Status.NOT_FOUND).entity(new ResponseObject("There are no episodes for this series!")).build();
	}
	
	
	/* Example
	 * {
		"description": "Desc1",
		"name": "TestCreateEp",
		"season": 1,
		"episode": 1,
		"sery": {
		  "id": 100001
			}
		}
	 * */
	
	@POST
	public Response createSeries(@CookieParam("token") Cookie cookie, Episode e) {
		try{
			Client c = userService.getClientByToken(cookie.getValue());
			if(c.getAdmin() != 1){
				return Response.status(Response.Status.UNAUTHORIZED).entity(new ResponseObject("Only admin can invoke this method!")).build();
			}
			episodeService.createEpisode(e);
			return Response.status(Response.Status.CREATED).entity(new ResponseObject("Episode created successfully!")).build();
		}
		catch(EJBException exc){
			return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Creating new episode failed!")).build();
		}
	}
	
	@PUT
	public Response editEpisode(@CookieParam("token") Cookie cookie, Episode e) {
		try{
			Client c = userService.getClientByToken(cookie.getValue());
			if(c.getAdmin() != 1){
				return Response.status(Response.Status.UNAUTHORIZED).entity(new ResponseObject("Only admin can invoke this method!")).build();
			}
			episodeService.editEpisode(e);
			return Response.status(Response.Status.OK).entity(new ResponseObject("Episode updated successfully!")).build();
		}
		catch(EJBException exc){
			return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Updating episode failed!")).build();
		}
	}
	
	/*	{
		"id": 59
		}
	 */
	
	@DELETE
	public Response removeEpisode(@CookieParam("token") Cookie cookie, Episode e) {
		try{
			Client c = userService.getClientByToken(cookie.getValue());
			if(c.getAdmin() != 1){
				return Response.status(Response.Status.UNAUTHORIZED).entity(new ResponseObject("Only admin can invoke this method!")).build();
			}
			episodeService.removeEpisode(e);
			return Response.status(Response.Status.OK).entity(new ResponseObject("Series removed successfully!")).build();
		}
		catch(EJBException exc){
			return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Removing series failed!")).build();
		}
	}
}
