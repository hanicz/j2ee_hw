package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the CLIENT database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Client.FIND_ALL, query="SELECT c FROM Client c"),
	@NamedQuery(name=Client.FIND_CLIENT_LOGIN, query = "SELECT c from Client c WHERE c.username = :username and c.password = :password"),
	@NamedQuery(name=Client.FIND_BY_TOKEN, query = "SELECT c from Client c WHERE c.token = :token")
})
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "Client.findAll";
	public static final String FIND_CLIENT_LOGIN = "Client.findClientLogin";
	public static final String FIND_BY_TOKEN = "Client.findByToken";

	@Id
	@SequenceGenerator(name="CLIENT_ID_GENERATOR", sequenceName="GENERATED_VALUE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENT_ID_GENERATOR")
	private int id;

	private String password;

	private String username;
	
	private String token;
	
	private int admin;

	//bi-directional many-to-one association to UserFavourite
	@OneToMany(mappedBy="client")
	private transient List<UserFavourite> userFavourites;

	public Client() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserFavourite> getUserFavourites() {
		return this.userFavourites;
	}

	public void setUserFavourites(List<UserFavourite> userFavourites) {
		this.userFavourites = userFavourites;
	}

	public UserFavourite addUserFavourite(UserFavourite userFavourite) {
		getUserFavourites().add(userFavourite);
		userFavourite.setClient(this);

		return userFavourite;
	}

	public UserFavourite removeUserFavourite(UserFavourite userFavourite) {
		getUserFavourites().remove(userFavourite);
		userFavourite.setClient(null);

		return userFavourite;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}
}