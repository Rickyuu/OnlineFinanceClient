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
@Table(name = "applyitem")
public class ApplyItem implements Serializable {
	private Long applyitemid;
	private Long applyid;
	private Date itemdate;
	private String itemcontent;
	private double itemmoney;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getApplyitemid() {
		return applyitemid;
	}
	public void setApplyitemid(Long applyitemid) {
		this.applyitemid = applyitemid;
	}
	
	@Column
	public Long getApplyid() {
		return applyid;
	}
	public void setApplyid(Long applyid) {
		this.applyid = applyid;
	}
	
	@Column
	public Date getItemdate() {
		return itemdate;
	}
	public void setItemdate(Date itemdate) {
		this.itemdate = itemdate;
	}
	
	@Column
	public String getItemcontent() {
		return itemcontent;
	}
	public void setItemcontent(String itemcontent) {
		this.itemcontent = itemcontent;
	}
	
	@Column
	public double getItemmoney() {
		return itemmoney;
	}
	public void setItemmoney(double itemmoney) {
		this.itemmoney = itemmoney;
	}
	
}
