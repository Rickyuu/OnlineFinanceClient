package edu.nju.onlinefinance.factory;

import edu.nju.onlinefinance.dao.ApplyItemDao;
import edu.nju.onlinefinance.dao.FinanceApplyDao;
import edu.nju.onlinefinance.dao.ProjectDao;
import edu.nju.onlinefinance.dao.ProjectUserDao;
import edu.nju.onlinefinance.dao.UserDao;

public class DaoFactory {

	public static UserDao getUserDao() {
		UserDao userDao = (UserDao) EJBFactory
				.getEJB("ejb:/OnlineFinanceEJB//UserDaoBean!edu.nju.onlinefinance.dao.UserDao");
		return userDao;
	}
	
	public static ProjectDao getProjectDao() {
		ProjectDao projectDao = (ProjectDao) EJBFactory
				.getEJB("ejb:/OnlineFinanceEJB//ProjectDaoBean!edu.nju.onlinefinance.dao.ProjectDao");
		return projectDao;
	}
	
	public static ProjectUserDao getProjectUserDao() {
		ProjectUserDao projectUserDao = (ProjectUserDao) EJBFactory
				.getEJB("ejb:/OnlineFinanceEJB//ProjectUserDaoBean!edu.nju.onlinefinance.dao.ProjectUserDao");
		return projectUserDao;
	}
	
	public static FinanceApplyDao getFinanceApplyDao() {
		FinanceApplyDao financeApplyDao = (FinanceApplyDao) EJBFactory
				.getEJB("ejb:/OnlineFinanceEJB//FinanceApplyDaoBean!edu.nju.onlinefinance.dao.FinanceApplyDao");
		return financeApplyDao;
	}
	
	public static ApplyItemDao getApplyItemDao() {
		ApplyItemDao applyItemDao = (ApplyItemDao) EJBFactory
				.getEJB("ejb:/OnlineFinanceEJB//ApplyItemDaoBean!edu.nju.onlinefinance.dao.ApplyItemDao");
		return applyItemDao;
	}
	
}
