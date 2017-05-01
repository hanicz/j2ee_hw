package web;


import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import comms.ResponseObject;
import entity.Series;

import javax.ws.rs.PathParam;

import service.SeriesService;


@Path("/series")
public class SeriesResource {

	@EJB
	SeriesService seriesService;
	
	@GET
	public Response getAllSeries() {
		return Response.status(Response.Status.OK).entity(seriesService.getAllSeries()).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getSeries(@PathParam("id") int id) {
		Series s = seriesService.getById(id);
		if(s != null)
			return Response.status(Response.Status.OK).entity(s).build();
		return Response.status(Response.Status.NOT_FOUND).entity(new ResponseObject("Series does not exist!")).build();
	}
	
	@POST
	public Response createSeries(Series s) {
		try{
			seriesService.createSeries(s);
			return Response.status(Response.Status.CREATED).entity(new ResponseObject("Series created successfully!")).build();
		}
		catch(EJBException exc){
			return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Creating new series failed!")).build();
		}
	}
	
	@PUT
	public Response editSeries(Series s) {
		try{
			seriesService.editSeries(s);
			return Response.status(Response.Status.OK).entity(new ResponseObject("Series updated successfully!")).build();
		}
		catch(EJBException exc){
			return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Updating series failed!")).build();
		}
	}
	
	@DELETE
	public Response removeSeries(Series s) {
		try{
			seriesService.removeSeries(s);
			return Response.status(Response.Status.OK).entity(new ResponseObject("Series removed successfully!")).build();
		}
		catch(EJBException exc){
			return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Removing series failed!")).build();
		}
	}
	
}
