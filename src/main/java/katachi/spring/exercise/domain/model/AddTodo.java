package katachi.spring.exercise.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class AddTodo {
	private String itemName;
	private DBUser dbUser;
	private Date deadline;
	private int completion;
}
