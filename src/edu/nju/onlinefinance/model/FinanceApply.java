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
@Table(name = "financeapply")
public class FinanceApply implements Serializable {
	private Long applyid;
	private Long projectid;
	private Long userid;
	private Date applydate;
	private String applystate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getApplyid() {
		return applyid;
	}
	public void setApplyid(Long applyid) {
		this.applyid = applyid;
	}
	
	@Column
	public Long getProjectid() {
		return projectid;
	}
	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}
	
	@Column
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	@Column
	public Date getApplydate() {
		return applydate;
	}
	public void setApplydate(Date applydate) {
		this.applydate = applydate;
	}
	
	@Column
	public String getApplystate() {
		return applystate;
	}
	public void setApplystate(String applystate) {
		this.applystate = applystate;
	}
	
}
