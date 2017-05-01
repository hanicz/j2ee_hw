package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SERIES database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Series.FIND_ALL, query="SELECT s FROM Series s"),
	@NamedQuery(name=Series.FIND_BY_ID, query="SELECT s FROM Series s where s.id = :id")
})
public class Series implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "Series.findAll";
	public static final String FIND_BY_ID = "Series.findById";

	@Id
	@SequenceGenerator(name="SERIES_ID_GENERATOR", sequenceName="GENERATED_VALUE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERIES_ID_GENERATOR")
	private int id;

	private String description;

	private String name;

	//bi-directional many-to-one association to Episode
	@OneToMany(mappedBy="sery")
	private transient List<Episode> episodes;

	//bi-directional many-to-one association to UserFavourite
	@OneToMany(mappedBy="sery")
	private transient List<UserFavourite> userFavourites;

	public Series() {
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

	public List<Episode> getEpisodes() {
		return this.episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}

	public Episode addEpisode(Episode episode) {
		getEpisodes().add(episode);
		episode.setSery(this);

		return episode;
	}

	public Episode removeEpisode(Episode episode) {
		getEpisodes().remove(episode);
		episode.setSery(null);

		return episode;
	}

	public List<UserFavourite> getUserFavourites() {
		return this.userFavourites;
	}

	public void setUserFavourites(List<UserFavourite> userFavourites) {
		this.userFavourites = userFavourites;
	}

	public UserFavourite addUserFavourite(UserFavourite userFavourite) {
		getUserFavourites().add(userFavourite);
		userFavourite.setSery(this);

		return userFavourite;
	}

	public UserFavourite removeUserFavourite(UserFavourite userFavourite) {
		getUserFavourites().remove(userFavourite);
		userFavourite.setSery(null);

		return userFavourite;
	}

}