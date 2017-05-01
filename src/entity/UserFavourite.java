package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER_FAVOURITES database table.
 * 
 */
@Entity
@Table(name="USER_FAVOURITES")
@NamedQueries({
	@NamedQuery(name=UserFavourite.FIND_ALL, query="SELECT u FROM UserFavourite u"),
	@NamedQuery(name=UserFavourite.FIND_BY_CLIENT, query="SELECT u FROM UserFavourite u where u.client = :client"),
	@NamedQuery(name=UserFavourite.FIND_BY_ID, query="SELECT u FROM UserFavourite u where u.id = :id")
})
public class UserFavourite implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "UserFavourite.findAll";
	public static final String FIND_BY_CLIENT = "UserFavourite.findByClient";
	public static final String FIND_BY_ID = "UserFavourite.findById";

	@Id
	@SequenceGenerator(name="USER_FAVOURITES_ID_GENERATOR", sequenceName="GENERATED_VALUE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_FAVOURITES_ID_GENERATOR")
	private int id;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Client client;

	//bi-directional many-to-one association to Series
	@ManyToOne
	@JoinColumn(name="SERIESID")
	private Series sery;

	public UserFavourite() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Series getSery() {
		return this.sery;
	}

	public void setSery(Series sery) {
		this.sery = sery;
	}

}