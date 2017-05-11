package web;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import entity.Client;
import service.UserService;

@WebService()
public class Soap {

	@EJB
	UserService userService;
	
	@WebMethod()
	public String login(String password, String username) {
		
		Client client = new Client();
		client.setPassword(password);
		client.setUsername(username);
		String token = userService.loginClient(client);
		
	    return token;
	}
}
