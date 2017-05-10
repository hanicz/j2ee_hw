package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import dal.AbstractFacade;
import entity.Series;

@Stateless
public class SeriesService extends AbstractFacade<Series>{

	public SeriesService() {
		super(Series.class);
	}

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager em() {
		return em;
	}

	public List<Series> getAllSeries(){
		List<Series> allSeries = null;
		try{
			allSeries = this.findAll();
			return allSeries;
		}catch(NoResultException nre){
			throw null;
		}
	}
	
	public Series getById(int id){
		Series s = null;
		try{
			s = em.createNamedQuery(Series.FIND_BY_ID, Series.class)
					.setParameter("id", id)
					.getSingleResult();
			return s;
		}catch(NoResultException nre){
			return null;
		}
	}
	
	public void createSeries(Series series) {
		this.create(series);
	}
	
	public void editSeries(Series series) {
		this.edit(series);
	}
	
	public void removeSeries(int id) {
		
		Series s = this.getById(id);
		
		if(s != null) this.remove(s);
	}
}
