package katachi.spring.exercise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import katachi.spring.exercise.form.LoginForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String getLogin(@ModelAttribute LoginForm form) {
		return "login";
	}
	
	@PostMapping("/login")
	public String postLogin(@ModelAttribute LoginForm form,
			BindingResult bindingResult,Model model) {
		
		log.info(form.toString());
		
		
		return ("/todo");
	}
}
