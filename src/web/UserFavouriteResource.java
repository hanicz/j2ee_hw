package web;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import javax.ejb.EJB;

import service.UserFavouriteService;
import service.UserService;
import javax.ws.rs.QueryParam;
import javax.ws.rs.CookieParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import comms.ResponseObject;
import entity.UserFavourite;

import javax.ejb.EJBException;
import javax.ws.rs.core.NewCookie;
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
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response newFavourite(@CookieParam("token") Cookie cookie, UserFavourite uf) {
		userFavouriteService.newFavourite(cookie.getValue(), uf);
		return Response.status(Response.Status.OK).entity(new ResponseObject("Success!")).build();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteFavourite(@CookieParam("token") Cookie cookie, UserFavourite uf) {
		
		if(uf.getId() == 0){
			return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Bad request!")).build();
		}
		
		userFavouriteService.deleteFavourite(cookie.getValue(), uf);
		return Response.status(Response.Status.OK).entity(new ResponseObject("Success!")).build();
	}
}
