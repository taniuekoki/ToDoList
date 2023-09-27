package katachi.spring.exercise.controller;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import katachi.spring.exercise.domain.model.AddTodo;
import katachi.spring.exercise.domain.model.TodoList;
import katachi.spring.exercise.domain.service.UserService;
import katachi.spring.exercise.form.DisplayUser;
import katachi.spring.exercise.form.TodoForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public String getTodo(@ModelAttribute TodoList form,Model model,
			@AuthenticationPrincipal UserDetails loginUser) {
		//ログイン情報をmodelに追加
		model.addAttribute("loginUser",loginUser);
		
		//todoリスト一覧取得
		List<TodoList> todoList = userService.getTodoList();
		model.addAttribute("todoList",todoList);
		
		//期限判定用
		Date toDay = new Date();
		model.addAttribute("today",toDay);
		
		return "todo";
	}
	
	@GetMapping("/register")
	public String getRegister(@ModelAttribute TodoForm form,Model model,
			@AuthenticationPrincipal UserDetails loginUser) {
		//ログイン情報をmodelに追加
		model.addAttribute("loginUser",loginUser);
		
		//担当者取得
		List<DisplayUser> users = userService.getManager();
		model.addAttribute("users",users);
		
		return "todo/register";
	}
	
	@PostMapping("/register")
	public String postRegister(@ModelAttribute @Validated TodoForm form,
			BindingResult bindingResult,Model model,
			@AuthenticationPrincipal UserDetails loginUser) {
		//入力チェック結果
		if(bindingResult.hasErrors()) {
			//NG:ユーザー登録画面に戻ります
			return getRegister(form,model,loginUser);
		}
		
		AddTodo addTodo = modelMapper.map(form,AddTodo.class);
		
		//DBに追加
		userService.addList(addTodo);
		
		return ("redirect:/todo");
	}
	
	/*作業編集*/
	@GetMapping("/edit/{id:.+}")
	public String getEditTodo(@ModelAttribute TodoForm form,Model model,
			@PathVariable("id")int id,
			@AuthenticationPrincipal UserDetails loginUser) {
		//ログイン情報をmodelに追加
		model.addAttribute("loginUser",loginUser);
		
		
		TodoList todo = userService.getTodoOne(id);
		
		TodoForm todoForm = modelMapper.map(todo,TodoForm.class);
		
		model.addAttribute("todoForm",todoForm);
		
		//担当者取得
		List<DisplayUser> users = userService.getManager();
		model.addAttribute("users",users);
		
		return "/todo/edit";
	}
	
	/*作業編集*/
	@PostMapping("/edit")
	public String postEditTodo(@ModelAttribute @Validated TodoForm form,
			BindingResult bindingResult,Model model,
			@AuthenticationPrincipal UserDetails loginUser) {
		//入力チェック結果
		if(bindingResult.hasErrors()) {
			//NG:ユーザー登録画面に戻ります
			return getRegister(form,model,loginUser);
		}
		
		TodoList todo = modelMapper.map(form,TodoList.class);

		System.out.println("現在の時刻は" + new Date() + "です。");
		
		log.info(todo.toString());
		
		userService.editTodo(todo);
		
		return "redirect:/todo";
	}
	
	/*作業削除*/
	@GetMapping("/delete/{id:.+}")
	public String getDeleteTodo(@ModelAttribute TodoForm form,Model model,
			@PathVariable("id")int id) {
		TodoList todo = userService.getTodoOne(id);
		TodoForm todoForm = modelMapper.map(todo,TodoForm.class);
		model.addAttribute("todoForm",todoForm);
		
		return "/todo/delete";
	}
	
	/*作業削除*/
	@PostMapping("/delete")
	public String postDeleteTodo(TodoForm form) {
		userService.deleteTodo(form.getId());
		return "redirect:/todo";
	}
	
	/*完了ボタン*/
	@GetMapping("/completion/{id:.+}")
	public String getCompletion(@PathVariable("id")int id) {
		userService.completionTodo(id);
		return "redirect:/todo";
	}
	
	
}
