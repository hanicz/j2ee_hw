package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import dal.AbstractFacade;
import entity.Episode;
import entity.Series;
import javax.ejb.EJB;

@Stateless
public class EpisodeService extends AbstractFacade<Episode>{
	
	@EJB
	SeriesService seriesService;
	
	public EpisodeService() {
		super(Episode.class);
	}

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager em() {
		return em;
	}
	
	public Episode getById(int id){
		Episode e = null;
		try{
			e = em.createNamedQuery(Episode.FIND_BY_ID, Episode.class)
					.setParameter("id", id)
					.getSingleResult();
			return e;
		}catch(NoResultException nre){
			return null;
		}
	}
	
	public List<Episode> getBySeriesId(int id){
		List<Episode> e = null;
		try{
			Series s = seriesService.getById(id);
			e = em.createNamedQuery(Episode.FIND_BY_SERIES_ID, Episode.class)
					.setParameter("id", s)
					.getResultList();
			return e;
		}catch(NoResultException nre){
			return null;
		}
	}
	
	public void createEpisode(Episode episode) {
		this.create(episode);
	}
	
	public void editEpisode(Episode episode) {
		this.edit(episode);
	}
	
	public void removeEpisode(Episode episode) {
		this.remove(episode);
	}
}
