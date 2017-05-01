package service;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import dal.AbstractFacade;
import entity.Client;
import entity.Series;

@Stateless
public class UserService extends AbstractFacade<Client>{
	
	public UserService() {
		super(Client.class);
	}

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager em() {
		return em;
	}
	
	public void createClient(Client client) {
		this.create(client);
	}
	
	public String loginClient(Client client){
		Client c = null;
		try{
			c = em.createNamedQuery(Client.FIND_CLIENT_LOGIN, Client.class)
					.setParameter("username", client.getUsername())
					.setParameter("password", client.getPassword())
					.getSingleResult();
			c.setToken(this.createToken());
			this.edit(c);
			return c.getToken();
		}catch(NoResultException nre){
			return null;
		}
	}
	
	public boolean logoutClient(String token){
		Client c = null;
		try{
			this.getClientByToken(token);
			c.setToken(null);
			this.edit(c);
			return true;
		}catch(NoResultException nre){
			return false;
		}
	}
	
	public Client getClientByToken(String token){
		Client c = null;
		try{
			c = em.createNamedQuery(Client.FIND_BY_TOKEN, Client.class)
					.setParameter("token", token)
					.getSingleResult();
			return c;
		}catch(NoResultException nre){
			return null;
		}
	}
	
	
	private String createToken(){	
		SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(24);
	}
}
