package katachi.spring.exercise.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="users")
public class DBUser {
	@Id
	private String userName;
	private String pass;
	private String role;
	private String manager;
	private int userId;
}
