package katachi.spring.exercise.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class TodoList {
	private int id;
	private String itemName;
	private DBUser dbUser;
	private Date registerDate;
	private Date deadline;
	private int completion;
}
