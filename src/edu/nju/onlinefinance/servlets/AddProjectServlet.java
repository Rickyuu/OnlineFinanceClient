package edu.nju.onlinefinance.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlinefinance.factory.DaoFactory;
import edu.nju.onlinefinance.model.Project;

/**
 * Servlet implementation class AddProjectServlet
 */
@WebServlet("/AddProjectServlet")
public class AddProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProjectServlet() {
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
		
		//("newProjectName", "newHostId", "newProjectMoney", "newYear", "newMonth", "newDay"); 
		// Calendar calendar=Calendar.getInstance();
		// calendar.set(Calendar.YEAR,year);
		// calendar.set(Calendar.MONTH,month);
		// calendar.set(Calendar.DAY_OF_MONTH,day);
		// Date date=new Date(calendar.getTimeInMillis());
		// user.setBirthday(date);
		String newProjectName = request.getParameter("newProjectName");
		String newHostIdString = request.getParameter("newHostId");
		Long newHostId = Long.parseLong(newHostIdString);
		String newProjectMoneyString = request.getParameter("newProjectMoney");
		double newProjectMoney = Double.parseDouble(newProjectMoneyString);
		
		int year=Integer.valueOf(request.getParameter("newYear")).intValue();
		int month=Integer.valueOf(request.getParameter("newMonth")).intValue()-1;
		int day=Integer.valueOf(request.getParameter("newDay")).intValue();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		Date deadline = new Date(calendar.getTimeInMillis());
		
		Project project = new Project();
		project.setProjectname(newProjectName);
		project.setHostid(newHostId);
		project.setProjectmoney(newProjectMoney);
		project.setDeadline(deadline);
		Date currentDate = new Date(System.currentTimeMillis());
		project.setCreateDate(currentDate);
		
		Long projectid = DaoFactory.getProjectDao().addProject(project);
		Project newProject = DaoFactory.getProjectDao().getProjectById(projectid);
		
		//响应给客户端     
      response.setContentType("text/html;charset=UTF-8");     
      PrintWriter out = response.getWriter();     
      out.append("<tr class='line'>");
      out.append("<td>"+newProject.getProjectid()+"</td>");
      out.append("<td class='column'>"+newProject.getProjectname()+"</td>");
      out.append("<td class='column'>"+newProject.getHostid()+"</td>");
      out.append("<td class='column'>"+newProject.getProjectmoney()+"</td>");
      out.append("<td class='column'>"+newProject.getDeadline()+"</td>");
      out.append("</tr>");
      out.flush();
      out.close();
	}

}
