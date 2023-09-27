package katachi.spring.exercise.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import katachi.spring.exercise.domain.model.DBUser;
import lombok.Data;

@Data
public class TodoForm {
	private int id;
	
	@NotBlank
	@Length(min = 1,max = 100)
	private String itemName;
	
	private DBUser dbUser;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date deadline;
	
	private int completion;
}
