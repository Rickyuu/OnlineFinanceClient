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
import edu.nju.onlinefinance.model.ApplyItem;

/**
 * Servlet implementation class AddApplyItemServlet
 */
@WebServlet("/AddApplyItemServlet")
public class AddApplyItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddApplyItemServlet() {
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
		Long applyid = (Long) request.getSession().getAttribute("applyid");
		
		int year=Integer.valueOf(request.getParameter("newYear")).intValue();
		int month=Integer.valueOf(request.getParameter("newMonth")).intValue()-1;
		int day=Integer.valueOf(request.getParameter("newDay")).intValue();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		Date applyDate = new Date(calendar.getTimeInMillis());
		
		String moneyString = request.getParameter("availMoney");
		double money = Double.parseDouble(moneyString);
		
		String describe = request.getParameter("describe");
		
		ApplyItem applyItem = new ApplyItem();
		applyItem.setApplyid(applyid);
		applyItem.setItemdate(applyDate);
		applyItem.setItemmoney(money);
		applyItem.setItemcontent(describe);
		DaoFactory.getApplyItemDao().addApplyItem(applyItem);
		
		// œÏ”¶
		response.setContentType("text/html;charset=UTF-8");     
		PrintWriter out = response.getWriter(); 
		out.append("<tr class='aline'>");
  	  	out.append("<td>"+applyItem.getItemdate()+"</td>");
  	  	out.append("<td>"+applyItem.getItemcontent()+"</td>");
  	  	out.append("<td>"+applyItem.getItemmoney()+"</td>");
  	  	out.append("</tr>");
  	  	out.flush();
	    out.close();
		
	}

}
