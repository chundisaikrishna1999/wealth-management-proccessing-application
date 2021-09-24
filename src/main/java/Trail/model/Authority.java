package Trail.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class Authority {

	@Id
	private String username;
	private String authority = "ROLE_USER";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = "ROLE_" + authority;
	}

	public Authority(String username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}

}