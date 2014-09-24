package edu.nju.onlinefinance.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlinefinance.factory.DaoFactory;

/**
 * Servlet implementation class DelUserServlet
 */
@WebServlet("/DelUserServlet")
public class DelUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelUserServlet() {
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
		String useridString = request.getParameter("delId");
		Long userid = Long.parseLong(useridString);
		DaoFactory.getUserDao().removeUser(userid);
		//响应给客户端     
//        response.setContentType("text/html;charset=UTF-8");     
//        PrintWriter out = response.getWriter();     
//        out.append("恭喜你，保存成功，你的基本信息如下：<br/>"); 
//        out.println();
	}

}
