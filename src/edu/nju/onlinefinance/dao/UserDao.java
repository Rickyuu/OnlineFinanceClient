package edu.nju.onlinefinance.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.onlinefinance.model.User;

@Remote
public interface UserDao {
	
	// 根据用户输入的卡号，获得用户
	public User getUserById(Long userid);
	
	// 添加一个新用户
	public boolean addUser(User user);
	
	// 根据要改变的列，该列的新值，更新一个用户的信息
	public boolean updateUser(Long userid, Long rowId, String newValue);
	
	// 删除一个用户
	public boolean removeUser(Long userid);
	
	// 获得所有用户信息
	public List<User> getAllUsers();
	
}
