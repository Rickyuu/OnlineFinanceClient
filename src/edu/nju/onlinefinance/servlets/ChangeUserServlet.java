package edu.nju.onlinefinance.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlinefinance.factory.DaoFactory;

/**
 * Servlet implementation class ChangeUserServlet
 */
@WebServlet("/ChangeUserServlet")
public class ChangeUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUserServlet() {
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
		String useridString = request.getParameter("changeId");
		Long userid = Long.parseLong(useridString);
		String rowIdString = request.getParameter("whichRow");
		Long rowId = Long.parseLong(rowIdString);
		String newValue = request.getParameter("inputText");
		DaoFactory.getUserDao().updateUser(userid, rowId, newValue);
	}

}
