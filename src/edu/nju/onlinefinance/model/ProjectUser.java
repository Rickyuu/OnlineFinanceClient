package edu.nju.onlinefinance.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "projectuser")
public class ProjectUser implements Serializable {
	private Long projectuserid;
	private Long projectid;
	private Long attendid;
	private double remainmoney;
	private double spendmoney;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProjectuserid() {
		return projectuserid;
	}
	public void setProjectuserid(Long projectuserid) {
		this.projectuserid = projectuserid;
	}
	
	@Column
	public Long getProjectid() {
		return projectid;
	}
	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}
	
	@Column
	public Long getAttendid() {
		return attendid;
	}
	public void setAttendid(Long attendid) {
		this.attendid = attendid;
	}
	
	@Column
	public double getRemainmoney() {
		return remainmoney;
	}
	public void setRemainmoney(double remainmoney) {
		this.remainmoney = remainmoney;
	}
	
	@Column
	public double getSpendmoney() {
		return spendmoney;
	}
	public void setSpendmoney(double spendmoney) {
		this.spendmoney = spendmoney;
	}
	
}
