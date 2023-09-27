package katachi.spring.exercise.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.exercise.domain.model.AddTodo;
import katachi.spring.exercise.domain.model.DBUser;
import katachi.spring.exercise.domain.model.TodoList;
import katachi.spring.exercise.form.DisplayUser;
import katachi.spring.exercise.form.SearchForm;

@Mapper
public interface UserMapper {
	
	/*ログインユーザー取得*/
	public DBUser findLoginUser(String userName);
	
	/*担当者取得*/
	public List<DisplayUser> getNames();
	
	/*リスト追加*/
	public void addTodo(AddTodo addTodo);
	
	/*todoリスト取得*/
	public List<TodoList> getList();
	
	/*todo 取得1件*/
	public TodoList getOne(int id);
	
	/*todo 更新*/
	public void updateTodo(TodoList todo);
	
	/*todo 削除フラグon*/
	public void deleteFlgOn(int id);
	
	/*todo 完了フラグOn*/
	public void updateCompletionTodo(int id);
	
	/*todo 項目名検索取得*/
	public List<TodoList> getListSearch(SearchForm form);
}
