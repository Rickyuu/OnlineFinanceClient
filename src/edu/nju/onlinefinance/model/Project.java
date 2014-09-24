package edu.nju.onlinefinance.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "projects")
public class Project implements Serializable {
	private Long projectid;
	private String projectname;
	private Long hostid;
	private double projectmoney;
	private Date deadline;
	private Date createDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProjectid() {
		return projectid;
	}
	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}
	
	@Column
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	
	@Column
	public Long getHostid() {
		return hostid;
	}
	public void setHostid(Long hostid) {
		this.hostid = hostid;
	}
	
	@Column
	public double getProjectmoney() {
		return projectmoney;
	}
	public void setProjectmoney(double projectmoney) {
		this.projectmoney = projectmoney;
	}
	
	@Column
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	@Column
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
