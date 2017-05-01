package web;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import javax.ejb.EJB;
import service.UserService;
import javax.ws.rs.QueryParam;
import javax.ws.rs.CookieParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ejb.EJBException;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Cookie;

import entity.Client;
import comms.ResponseObject;

@Path("/users")
public class UserResource {
	
	@EJB
	UserService userservice;
	
	@GET
	@Path("/login")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response login(@QueryParam("username") String username ,@QueryParam("password") String password) {
		Client client = new Client();
		client.setPassword(password);
		client.setUsername(username);
		String token = userservice.loginClient(client);
		if(token != null){
			NewCookie cookie = new NewCookie("token",token);
			return Response.status(Response.Status.OK).cookie(cookie).entity(new ResponseObject("Logged in successfully!")).build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).entity(new ResponseObject("Login failed!")).build();
	}
	
	@POST
	@Path("/register")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response register(Client c) {
		try{
			userservice.create(c);
			return Response.status(Response.Status.CREATED).entity(new ResponseObject("User created successfully!")).build();
		}
		catch(EJBException exc){
			return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Creating new user failed!")).build();
		}
	}
	
	@PUT
	@Path("/logout")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response logout(@CookieParam("token") Cookie cookie) {
		if(userservice.logoutClient(cookie.getValue()))
			return Response.status(Response.Status.OK).entity(new ResponseObject("User logged out successfully!")).build();
		return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseObject("Logout failed!")).build();
	}
	
}

