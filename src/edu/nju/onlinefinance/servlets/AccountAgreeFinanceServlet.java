package edu.nju.onlinefinance.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlinefinance.factory.DaoFactory;
import edu.nju.onlinefinance.model.ApplyItem;
import edu.nju.onlinefinance.model.FinanceApply;
import edu.nju.onlinefinance.model.Project;

/**
 * Servlet implementation class AccountAgreeFinanceServlet
 */
@WebServlet("/AccountAgreeFinanceServlet")
public class AccountAgreeFinanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountAgreeFinanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String applyidString = request.getParameter("viewId");
		Long applyid = Long.parseLong(applyidString);
		// 计算报销金额
		List<ApplyItem> items = DaoFactory.getApplyItemDao().getItemsInApply(applyid);
		double moneyToAdd = 0;
		for(int i=0; i<items.size(); i++){
			double currentMoney = items.get(i).getItemmoney();
			moneyToAdd += currentMoney;
		}
		// 给该申请表用户打钱
		Long applicantId = DaoFactory.getFinanceApplyDao().getFinanceApplyById(applyid).getUserid();
		double oldMoney = DaoFactory.getUserDao().getUserById(applicantId).getUsermoney();
		double moneyNow = oldMoney + moneyToAdd;
		Long changeNum = new Long((long)3);
		DaoFactory.getUserDao().updateUser(applicantId, changeNum, String.valueOf(moneyNow));
		// 减少财务审核自己的钱
		Long accountId = (Long) request.getSession().getAttribute("userid");
		double oldAccountMoney = DaoFactory.getUserDao().getUserById(accountId).getUsermoney();
		double moneyAccountNow = oldAccountMoney - moneyToAdd;
		DaoFactory.getUserDao().updateUser(accountId, changeNum, String.valueOf(moneyAccountNow));
		// 更改该用户在项目中的可用金额
		Long projectId = DaoFactory.getFinanceApplyDao().getFinanceApplyById(applyid).getProjectid();
		DaoFactory.getProjectUserDao().updateProject(projectId, applicantId, moneyToAdd);
		// 改变申请表状态
		DaoFactory.getFinanceApplyDao().updateApplyState(applyid, "已报销");
		
		//响应给客户端     
	      response.setContentType("text/html;charset=UTF-8");     
	      PrintWriter out = response.getWriter();     
	      out.append("<table class='table table-striped' id='accountfinancetable'>");
	      out.append("<tr><th>申请表编号</th><th>项目编号</th><th>项目名称</th><th>申请人卡号</th><th>申请人名称</th><th>申请日期</th><th>查看</th></tr>");
	      List<Project> projects = DaoFactory.getProjectDao().getAllProjects();
	      for(int i=0; i<projects.size(); i++){
	      		Project project = projects.get(i);
	      		Long projectid = project.getProjectid();
	      		// 获得这个项目所有的申请表，并列出其中“提交财务”的部分
	      		List<FinanceApply> applies = DaoFactory.getFinanceApplyDao().getAllApplies();
	      		for(int j=0; j<applies.size(); j++){
	      			// 是这个项目,并且是"提交财务"
	      			if(applies.get(j).getProjectid().equals(projectid) && applies.get(j).getApplystate().equals("提交财务")){
	      				FinanceApply apply = applies.get(j);
	      				out.print("<tr class='viewline'>");
	      	    		out.print("<td class='viewcolumn'>"+apply.getApplyid()+"</td>");
	      	    		out.print("<td>"+project.getProjectid()+"</td>");
	      	    		out.print("<td>"+project.getProjectname()+"</td>");
	      	    		out.print("<td>"+apply.getUserid()+"</td>");
	      	    		out.print("<td>"+DaoFactory.getUserDao().getUserById(apply.getUserid()).getUserName()+"</td>");
	      	    		out.print("<td>"+apply.getApplydate()+"</td>");
	      	    		out.print("<td>");
	      	    		out.print("<div class='btn'><i class='icon-camera'></i></div>");
	      	    		out.print("</td>");
	      	    		out.print("</tr>");
	      			}
	      		}
	      }
	      out.append("</table>");
	      out.flush();
	      out.close();
	}

}
