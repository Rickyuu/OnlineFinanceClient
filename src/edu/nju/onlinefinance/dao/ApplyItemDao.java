package edu.nju.onlinefinance.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.onlinefinance.model.ApplyItem;
import edu.nju.onlinefinance.model.ProjectUser;

@Remote
public interface ApplyItemDao {

	// 添加一个申请条目
	public boolean addApplyItem(ApplyItem applyItem);
	
	// 根据申请表编号，获取当前申请表所包含的条目
	public List<ApplyItem> getItemsInApply(Long applyid);
	
}
