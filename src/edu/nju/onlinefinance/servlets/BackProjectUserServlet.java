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
import edu.nju.onlinefinance.model.Project;

/**
 * Servlet implementation class BackProjectUserServlet
 */
@WebServlet("/BackProjectUserServlet")
public class BackProjectUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackProjectUserServlet() {
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
		  //响应给客户端     
	      response.setContentType("text/html;charset=UTF-8");     
	      PrintWriter out = response.getWriter();     
	      out.append("<table class='table table-striped' id='hostprotable'>");
	      out.append("<tr><th>项目编号</th><th>项目名称</th><th>项目金额</th><th>截止日期</th><th>查看</th></tr>");
	      List<Project> projects = DaoFactory.getProjectDao().getAllProjects();
	      Long hostId = (Long) request.getSession().getAttribute("userid");
	      for(int i=0; i<projects.size(); i++){
	      	if(projects.get(i).getHostid().equals(hostId)){
	      		Project project = projects.get(i);
	      		out.append("<tr class='viewline'>");
	      		out.append("<td class='viewcolumn'>"+project.getProjectid()+"</td>");
	      		out.append("<td>"+project.getProjectname()+"</td>");
	      		out.append("<td>"+project.getProjectmoney()+"</td>");
	      		out.append("<td>"+project.getDeadline()+"</td>");
	      		out.append("<td>");
	      		out.append("<div class='btn'><i class='icon-camera'></i></div>");
	      		out.append("</td>");
	      		out.append("</tr>");
	      	}
	      }
	      out.append("</table>");
	      out.flush();
	      out.close();
	}

}
