package katachi.spring.exercise.domain.service;

import java.util.List;

import katachi.spring.exercise.domain.model.AddTodo;
import katachi.spring.exercise.domain.model.DBUser;
import katachi.spring.exercise.domain.model.TodoList;
import katachi.spring.exercise.form.DisplayUser;
import katachi.spring.exercise.form.SearchForm;

public interface UserService {
	
	/*ログインチェック*/
	public DBUser getLoginUser(String userName);
	
	/*担当者取得*/
	public List<DisplayUser>getManager();
	
	/*リスト追加*/
	public void addList(AddTodo addTodo);
	
	/*todoリスト取得*/
	public List<TodoList> getTodoList();
	
	/*todo編集表示*/
	public TodoList getTodoOne(int id);
	
	/*todo編集*/
	public void editTodo(TodoList todo);
	
	/*todo削除*/
	public void deleteTodo(int id);
	
	/*todo完了処理*/
	public void completionTodo(int id);
	
	/*todoリスト検索*/
	public List<TodoList> getSearch(SearchForm form);
}
