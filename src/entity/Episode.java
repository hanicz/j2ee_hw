package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EPISODE database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Episode.FIND_ALL, query="SELECT e FROM Episode e"),
	@NamedQuery(name=Episode.FIND_BY_ID, query="SELECT e FROM Episode e where e.id = :id"),
	@NamedQuery(name=Episode.FIND_BY_SERIES_ID, query="SELECT e FROM Episode e where e.sery = :id")
})
public class Episode implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "Episode.findAll";
	public static final String FIND_BY_ID = "Episode.findById";
	public static final String FIND_BY_SERIES_ID = "Episode.findBySeriesId";

	@Id
	@SequenceGenerator(name="EPISODE_ID_GENERATOR", sequenceName="GENERATED_VALUE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPISODE_ID_GENERATOR")
	private int id;

	private String description;

	private String name;
	
	private int season;
	
	private int episode;

	//bi-directional many-to-one association to Series
	@ManyToOne
	@JoinColumn(name="SERIESID")
	private Series sery;

	public Episode() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Series getSery() {
		return this.sery;
	}

	public void setSery(Series sery) {
		this.sery = sery;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	
}