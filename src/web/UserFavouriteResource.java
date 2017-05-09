package web;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import javax.ejb.EJB;

import service.UserFavouriteService;
import javax.ws.rs.PathParam;
import javax.ws.rs.CookieParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import comms.ResponseObject;
import javax.ws.rs.core.Cookie;


@Path("/userFavourites")
public class UserFavouriteResource {
	
	@EJB
	UserFavouriteService userFavouriteService;

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUserFavourites(@CookieParam("token") Cookie cookie) {
		return Response.status(Response.Status.OK).entity(userFavouriteService.getUserFavourites(cookie.getValue())).build();
	}
	
	
	@POST
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response newFavourite(@CookieParam("token") Cookie cookie, @PathParam("id") int id) {
		userFavouriteService.newFavourite(cookie.getValue(), id);
		return Response.status(Response.Status.OK).entity(new ResponseObject("Success!")).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteFavourite(@CookieParam("token") Cookie cookie, @PathParam("id") int id) {		
		userFavouriteService.deleteFavourite(cookie.getValue(), id);
		return Response.status(Response.Status.OK).entity(new ResponseObject("Success!")).build();
	}
}
