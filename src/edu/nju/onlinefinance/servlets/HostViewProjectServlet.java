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
import edu.nju.onlinefinance.model.ProjectUser;

/**
 * Servlet implementation class HostViewProjectServlet
 */
@WebServlet("/HostViewProjectServlet")
public class HostViewProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HostViewProjectServlet() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); 
		String projectIdString = request.getParameter("viewId");
		Long projectId = Long.parseLong(projectIdString);
		List<ProjectUser> projectUsers = DaoFactory.getProjectUserDao().getUsersInProject(projectId);
		
		//响应给客户端     
	      response.setContentType("text/html;charset=UTF-8");     
	      PrintWriter out = response.getWriter();     
	      out.append("<table class='table table-striped' id='putable'>");
	      out.append("<tr><th>用户卡号</th><th>用户姓名</th><th>已用金额</th><th>剩余金额</th></tr>");
	      for(int i=0; i<projectUsers.size(); i++){
	    	  ProjectUser projectUser = projectUsers.get(i);
	    	  out.append("<tr class='puline'>");
	    	  out.append("<td>"+projectUser.getAttendid()+"</td>");
	    	  out.append("<td>"+DaoFactory.getUserDao().getUserById(projectUser.getAttendid()).getUserName()+"</td>");
	    	  out.append("<td>"+projectUser.getSpendmoney()+"</td>");
	    	  out.append("<td>"+projectUser.getRemainmoney()+"</td>");
	    	  out.append("</tr>");
	      }
	      out.append("</table>");
	      out.append("<div class='form-inline'>");
	      out.append("<input type='text' class='input-medium' placeholder='用户卡号' name='userId'>");
	      out.append("<input type='text' class='input-medium' placeholder='可用金额' name='availMoney'>");
	      out.append("<button type='submit' class='btn' id='addButton'>添加</button>");
	      out.append("<button type='submit' class='btn' id='backButton'>返回</button>");
	      out.append("</div>");
	      out.flush();
	      out.close();
	      
	}

}
