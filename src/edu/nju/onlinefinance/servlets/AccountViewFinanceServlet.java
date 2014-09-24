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

/**
 * Servlet implementation class AccountViewFinanceServlet
 */
@WebServlet("/AccountViewFinanceServlet")
public class AccountViewFinanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountViewFinanceServlet() {
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
		List<ApplyItem> items = DaoFactory.getApplyItemDao().getItemsInApply(applyid);
		
		//响应给客户端     
	      response.setContentType("text/html;charset=UTF-8");     
	      PrintWriter out = response.getWriter();     
	      out.append("<table class='table table-striped' id='accountfinancetable'>");
	      out.append("<tr><th>日期</th><th>描述</th><th>金额</th></tr>");
	      for(int i=0; i<items.size(); i++){
	    	  ApplyItem item = items.get(i);
	    	  out.append("<tr>");
	    	  out.append("<td>"+item.getItemdate()+"</td>");
	    	  out.append("<td>"+item.getItemcontent()+"</td>");
	    	  out.append("<td>"+item.getItemmoney()+"</td>");
	    	  out.append("</tr>");
	      }
	      out.append("</table>");
	      out.append("<div class='form-inline'>");
	      out.append("<button type='submit' class='btn' id='agreeButton'>同意</button>");
	      out.append("<button type='submit' class='btn' id='disagreeButton'>拒绝</button>");
	      out.append("<button type='submit' class='btn' id='backButton'>返回</button>");
	      out.append("</div>");
	      out.flush();
	      out.close();
	}

}
