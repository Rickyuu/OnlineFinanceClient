package edu.nju.onlinefinance.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.onlinefinance.model.FinanceApply;

@Remote
public interface FinanceApplyDao {
	// 添加一个新申请
	public Long addApply(FinanceApply financeApply);
	
	// 改变某个申请的状态
	public boolean updateApplyState(Long applyid, String applystate);
	
	// 获得所有申请表信息
	public List<FinanceApply> getAllApplies();
	
	// 根据申请表编号获得申请表
	public FinanceApply getFinanceApplyById(Long applyid);
}
