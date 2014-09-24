package edu.nju.onlinefinance.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlinefinance.factory.DaoFactory;
import edu.nju.onlinefinance.model.User;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>"+"Hello World!</title></head>"+"Hello World!</h1></body></html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); 
		String useridString = request.getParameter("newId");
		Long userid = Long.parseLong(useridString);
		String userName = request.getParameter("newName");
		String identity = request.getParameter("newIdentity");
		String moneyString = request.getParameter("newMoney");
		double money = Double.parseDouble(moneyString);
		User user = new User();
		user.setUserid(userid);
		user.setUserName(userName);
		user.setIdentity(identity);
		user.setUsermoney(money);
		Date currentDate = new Date(System.currentTimeMillis());
		user.setCreateDate(currentDate);
		DaoFactory.getUserDao().addUser(user);
		//响应给客户端     
//        response.setContentType("text/html;charset=UTF-8");     
//        PrintWriter out = response.getWriter();     
//        out.append("恭喜你，保存成功，你的基本信息如下：<br/>"); 
//        out.println();
	}

}
