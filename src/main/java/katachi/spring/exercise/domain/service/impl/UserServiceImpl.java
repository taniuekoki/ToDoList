package katachi.spring.exercise.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.exercise.domain.model.AddTodo;
import katachi.spring.exercise.domain.model.DBUser;
import katachi.spring.exercise.domain.model.TodoList;
import katachi.spring.exercise.domain.service.UserService;
import katachi.spring.exercise.form.DisplayUser;
import katachi.spring.exercise.form.SearchForm;
import katachi.spring.exercise.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper mapper;
	
	/*ログインユーザー情報取得*/
	@Override
	public DBUser getLoginUser(String userName) {
		return mapper.findLoginUser(userName);
	}
	
	/*担当者取得*/
	@Override
	public List<DisplayUser>getManager(){
		return mapper.getNames();
	}
	
	/*リスト追加*/
	@Override
	public void addList(AddTodo addTodo) {
		mapper.addTodo(addTodo);
	}
	
	/*todoリスト取得*/
	@Override
	public List<TodoList> getTodoList(){
		return mapper.getList();
	}
	
	/*todo編集*/
	@Override
	public TodoList getTodoOne(int id) {
		return mapper.getOne(id);
	}
	
	/*todo編集*/
	@Override
	public void editTodo(TodoList todo) {
		mapper.updateTodo(todo);
	}
	
	/*todo削除*/
	@Override
	public void deleteTodo(int id) {
		mapper.deleteFlgOn(id);
	}
	
	/*todo完了処理*/
	@Override
	public void completionTodo(int id) {
		mapper.updateCompletionTodo(id);
	}
	
	/*todoリスト検索*/
	@Override
	public List<TodoList> getSearch(SearchForm form){
		return mapper.getListSearch(form);
	}
}
