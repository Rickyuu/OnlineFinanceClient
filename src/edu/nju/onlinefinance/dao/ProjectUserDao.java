package edu.nju.onlinefinance.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.onlinefinance.model.ProjectUser;

@Remote
public interface ProjectUserDao {
	
	// 根据项目编号获得所有的该项目参与者卡号，已用金额，剩余金额
	public List<ProjectUser> getUsersInProject(Long projectid);
	
	// 根据用户卡号查看与该用户相关的项目
	public List<ProjectUser> getProjectsByUser(Long userid);
	
	// 为某个项目添加一个新用户,返回新条目编号
	public Long addProjectUser(ProjectUser projectUser);
	
	// 为用户调整在某个项目中的剩余金额、已用金额
	public boolean updateProject(Long projectid, Long attendid, double money);
	
}
