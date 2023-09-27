package katachi.spring.exercise.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import katachi.spring.exercise.domain.model.TodoList;
import katachi.spring.exercise.domain.service.UserService;
import katachi.spring.exercise.form.SearchForm;

@Controller
public class SearchController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/search")
	public String getSeach(@ModelAttribute SearchForm form,Model model) {
		//検索結果取得
		List<TodoList> todoList = userService.getSearch(form);
		model.addAttribute("todoList",todoList);
		
		//期限判定用
		Date toDay = new Date();
		model.addAttribute("today",toDay);
		
		return "/search";
	}
}
