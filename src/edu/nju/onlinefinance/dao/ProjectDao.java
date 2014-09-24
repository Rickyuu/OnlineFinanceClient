package edu.nju.onlinefinance.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.onlinefinance.model.Project;

@Remote
public interface ProjectDao {
	
	// 根据用户输入的项目编号，获得项目
	public Project getProjectById(Long projectid);
	
	// 添加一个新项目
	public Long addProject(Project project);
	
	// 根据要改变的列，该列的新值，更新一个项目的信息
	public boolean updateProject(Long projectid, Long rowId, String newValue);
	
	// 删除一个项目
	public boolean removeProject(Long projectid);
	
	// 获得所有项目信息
	public List<Project> getAllProjects();
	
}
