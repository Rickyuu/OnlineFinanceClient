package edu.nju.onlinefinance.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlinefinance.factory.DaoFactory;
import edu.nju.onlinefinance.model.ProjectUser;

/**
 * Servlet implementation class AddProjectUserServlet
 */
@WebServlet("/AddProjectUserServlet")
public class AddProjectUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProjectUserServlet() {
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
		String userIdString = request.getParameter("userId");
		Long userId = Long.parseLong(userIdString);
		String availMoneyString = request.getParameter("availMoney");
		double remainMoney = Double.parseDouble(availMoneyString);
		String projectIdString = request.getParameter("viewId");
		Long projectId = Long.parseLong(projectIdString);
		ProjectUser projectUser = new ProjectUser();
		projectUser.setProjectid(projectId);
		projectUser.setAttendid(userId);
		projectUser.setRemainmoney(remainMoney);
		projectUser.setSpendmoney(0);
		DaoFactory.getProjectUserDao().addProjectUser(projectUser);
		// œÏ”¶
		response.setContentType("text/html;charset=UTF-8");     
	    PrintWriter out = response.getWriter(); 
	    out.append("<tr class='puline'>");
  	  	out.append("<td>"+userId+"</td>");
  	  	out.append("<td>"+DaoFactory.getUserDao().getUserById(userId).getUserName()+"</td>");
  	  	out.append("<td>"+projectUser.getSpendmoney()+"</td>");
  	  	out.append("<td>"+projectUser.getRemainmoney()+"</td>");
  	  	out.append("</tr>");
	    out.flush();
	    out.close();
	}

}
