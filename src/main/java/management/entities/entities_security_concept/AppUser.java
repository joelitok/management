package management.entities.entities_security_concept;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
@Entity
public class AppUser {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<AppRole> roles =new ArrayList<>();
	public AppUser() {
	}
	public AppUser(Long id, String username, String password, 
	Collection<AppRole> roles) {
	this.id = id;
	this.username = username;
	this.password = password;
	this.roles = roles;
	}
	public Long getId() {
	return id;
	}
	public void setId(Long id) {
	this.id = id;
	}
	public String getUsername() {
	return username;
	}
	public void setUsername(String username) {
	this.username = username;
	}
	@JsonIgnore
	public String getPassword() {
	return password;
	}
	@JsonSetter
	public void setPassword(String password) {
	this.password = password;
	}
	public Collection<AppRole> getRoles() {
	return roles;
	}
	public void setRoles(Collection<AppRole> roles) {
	this.roles = roles;
	}
}
