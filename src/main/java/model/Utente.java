package model;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "utente")
public class Utente {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "utente", fetch = FetchType.EAGER)
    private List<Pizza> pizze;

	public Utente() {}
	
	public Utente(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	public List<Pizza> getPizze() {
        return pizze;
    }

    public void setPizze(List<Pizza> pizze) {
        this.pizze = pizze;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, pizze);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return id == other.id && Objects.equals(password, other.password) && Objects.equals(pizze, other.pizze)
				&& Objects.equals(username, other.username);
	}
}