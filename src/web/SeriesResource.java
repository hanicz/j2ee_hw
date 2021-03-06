package web;


import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.CookieParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import comms.ResponseObject;
import entity.Client;
import entity.Series;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import service.SeriesService;
import service.UserService;


@Path("/series")
public class SeriesResource {

	@EJB
	SeriesService seriesService;
	
	@EJB
	UserService userService;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAllSeries() {
		return Response.status(Response.Status.OK).entity(seriesService.getAllSeries()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getSeries(@PathParam("id") int id) {
		Series s = seriesService.getById(id);
		if(s != null)
			return Response.status(Response.Status.OK).entity(s).build();
		return Response.status(Response.Status.NOT_FOUND).entity(new ResponseObject("Series does not exist!")).build();
	}
	
	@POST
	public Response createSeries(@CookieParam("token") Cookie cookie, Series s) {
		try{
			Client c = userService.getClientByToken(cookie.getValue());
			if(c.getAdmin() != 1){
				return Response.status(Response.Status.UNAUTHORIZED).entity(new ResponseObject("Only admin can invoke this method!")).build();
			}
			if(s.getDescription() == null || s.getName() == null)
				return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Creating new series failed!")).build();
			seriesService.createSeries(s);
			return Response.status(Response.Status.CREATED).entity(new ResponseObject("Series created successfully!")).build();
		}
		catch(EJBException exc){
			return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Creating new series failed!")).build();
		}
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response editSeries(@CookieParam("token") Cookie cookie, Series s) {
		try{
			Client c = userService.getClientByToken(cookie.getValue());
			if(c.getAdmin() != 1){
				return Response.status(Response.Status.UNAUTHORIZED).entity(new ResponseObject("Only admin can invoke this method!")).build();
			}
			if(s.getDescription() == null || s.getName() == null || s.getId() == 0)
				return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Updating series failed!")).build();
			seriesService.editSeries(s);
			return Response.status(Response.Status.OK).entity(new ResponseObject("Series updated successfully!")).build();
		}
		catch(EJBException exc){
			return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Updating series failed!")).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response removeSeries(@CookieParam("token") Cookie cookie,@PathParam("id") int id) {
		try{
			Client c = userService.getClientByToken(cookie.getValue());
			if(c.getAdmin() != 1){
				return Response.status(Response.Status.UNAUTHORIZED).entity(new ResponseObject("Only admin can invoke this method!")).build();
			}
			seriesService.removeSeries(id);
			return Response.status(Response.Status.OK).entity(new ResponseObject("Series removed successfully!")).build();
		}
		catch(EJBException exc){
			return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Removing series failed!")).build();
		}
	}
	
}
