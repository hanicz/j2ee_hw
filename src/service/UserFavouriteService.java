package service;

import javax.ejb.Stateless;

import java.util.ArrayList;
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

	@EJB
	SeriesService seriesService;
	
	public List<Series> getUserFavourites(String token){
		Client c = userService.getClientByToken(token);
		List<UserFavourite> uf = null;
		if(c != null){
			uf = em.createNamedQuery(UserFavourite.FIND_BY_CLIENT, UserFavourite.class)
					.setParameter("client", c)
					.getResultList();
		}
		else{
			return null;
		}
		List<Series> series = new ArrayList<Series>();
		for(UserFavourite u : uf){
			series.add(u.getSery());
		}
		return series;
	}
	
	public void newFavourite(String token, int seriesId){
		Client c = userService.getClientByToken(token);
		Series s = seriesService.getById(seriesId);
		UserFavourite uf = new UserFavourite();
		try{
			UserFavourite existing = em.createNamedQuery(UserFavourite.FIND_BY_SERIES, UserFavourite.class)
				.setParameter("c", c)
				.setParameter("s", s).getSingleResult();
		}catch(NoResultException nre){
			uf.setClient(c);
			uf.setSery(s);
			this.create(uf);
		}
	}
	
	public void deleteFavourite(String token, int seriesId){
		Client c = userService.getClientByToken(token);
		Series s = seriesService.getById(seriesId);
		UserFavourite userFav = this.getBySeries(c, s);

		this.remove(userFav);
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

	public UserFavourite getBySeries(Client c, Series s){
		try{
			return em.createNamedQuery(UserFavourite.FIND_BY_SERIES, UserFavourite.class)
					.setParameter("c", c)
					.setParameter("s", s)
					.getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}
}
