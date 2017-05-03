package service;

import javax.ejb.Stateless;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import dal.AbstractFacade;
import entity.Client;
import entity.Series;
import entity.UserFavourite;

@Stateless
public class UserFavouriteService extends AbstractFacade<UserFavourite>{

	public UserFavouriteService() {
		super(UserFavourite.class);
	}

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager em() {
		return em;
	}
	
	@EJB
	UserService userService;
	
	public List<Series> getUserFavourites(String token){
		Client c = userService.getClientByToken(token);
		List<UserFavourite> uf = null;
		if(c != null){
			uf = em.createNamedQuery(UserFavourite.FIND_BY_CLIENT, UserFavourite.class)
					.setParameter("client", c)
					.getResultList();
		}
		List<Series> series = null;
		for(UserFavourite u : uf){
			series.add(u.getSery());
		}
		return uf;
	}
	
	public void newFavourite(String token, UserFavourite uf){
		Client c = userService.getClientByToken(token);
		uf.setClient(c);
		this.create(uf);
	}
	
	public void deleteFavourite(String token, UserFavourite uf){
		Client c = userService.getClientByToken(token);
		
		UserFavourite userFav = this.getById(uf.getId());
		
		if(userFav.getClient().getId() == c.getId()){
			uf.setClient(c);
			this.remove(uf);
		}

	}
	
	public UserFavourite getById(int id){
		try{
			return em.createNamedQuery(UserFavourite.FIND_BY_ID, UserFavourite.class)
					.setParameter("id", id)
					.getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}
}
