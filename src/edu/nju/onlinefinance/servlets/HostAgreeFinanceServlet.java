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
import edu.nju.onlinefinance.model.FinanceApply;
import edu.nju.onlinefinance.model.Project;

/**
 * Servlet implementation class HostAgreeFinanceServlet
 */
@WebServlet("/HostAgreeFinanceServlet")
public class HostAgreeFinanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HostAgreeFinanceServlet() {
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
		DaoFactory.getFinanceApplyDao().updateApplyState(applyid, "提交财务");
		
		//响应给客户端     
	      response.setContentType("text/html;charset=UTF-8");     
	      PrintWriter out = response.getWriter();     
	      out.append("<table class='table table-striped' id='hostfinancetable'>");
	      out.append("<tr><th>申请表编号</th><th>项目编号</th><th>项目名称</th><th>申请人卡号</th><th>申请人名称</th><th>申请日期</th><th>查看</th></tr>");
	      List<Project> projects = DaoFactory.getProjectDao().getAllProjects();
	  	Long hostId = (Long)request.getSession().getAttribute("userid");
	      for(int i=0; i<projects.size(); i++){
	      	// 是这个人主持的项目
	      	if(projects.get(i).getHostid().equals(hostId)){
	      		Project project = projects.get(i);
	      		Long projectid = project.getProjectid();
	      		// 获得这个项目所有的申请表，并列出其中“提交主持”的部分
	      		List<FinanceApply> applies = DaoFactory.getFinanceApplyDao().getAllApplies();
	      		for(int j=0; j<applies.size(); j++){
	      			// 是这个项目,并且是"提交主持"
	      			if(applies.get(j).getProjectid() == projectid && applies.get(j).getApplystate().equals("提交主持")){
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
	      }
	      out.append("</table>");
	      out.flush();
	      out.close();
	      
	}

}
