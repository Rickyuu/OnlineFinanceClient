package edu.nju.onlinefinance.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlinefinance.factory.DaoFactory;
import edu.nju.onlinefinance.model.ApplyItem;
import edu.nju.onlinefinance.model.FinanceApply;

/**
 * Servlet implementation class AddFinanceApplyServlet
 */
@WebServlet("/AddFinanceApplyServlet")
public class AddFinanceApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFinanceApplyServlet() {
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
		String projectIdString = request.getParameter("projectId");
		Long projectId = Long.parseLong(projectIdString);
		Long userId = (Long) request.getSession().getAttribute("userid");
		Date currentDate = new Date(System.currentTimeMillis());
		
		FinanceApply apply = new FinanceApply();
		apply.setProjectid(projectId);
		apply.setUserid(userId);
		apply.setApplydate(currentDate);
		apply.setApplystate("�ύ����");
		// ���һ���µ���������һ�÷��ص������ID
		Long applyid = DaoFactory.getFinanceApplyDao().addApply(apply);
		request.getSession().setAttribute("applyid", applyid);
		List<ApplyItem> items = DaoFactory.getApplyItemDao().getItemsInApply(applyid);
		
		//��Ӧ���ͻ���     
	      response.setContentType("text/html;charset=UTF-8");     
	      PrintWriter out = response.getWriter();     
	      out.append("<table class='table table-striped' id='applytable'>");
	      out.append("<tr><th>����</th><th>����</th><th>���</th></tr>");
	      for(int i=0; i<items.size(); i++){
	    	  ApplyItem item = items.get(i);
	    	  out.append("<tr class='aline'>");
	    	  out.append("<td>"+item.getItemdate()+"</td>");
	    	  out.append("<td>"+item.getItemcontent()+"</td>");
	    	  out.append("<td>"+item.getItemmoney()+"</td>");
	    	  out.append("</tr>");
	      }
	      out.append("</table>");
	      out.append("<div class='form-inline'>");
	      out.append("<input type='text' class='input-mini' name='newYear'>��");
	      out.append("<input type='text' class='input-mini' name='newMonth'>��");
	      out.append("<input type='text' class='input-mini' name='newDay'>��");
	      out.append("<input type='text' class='input-xxlarge' placeholder='����' name='describe'>");
	      out.append("<input type='text' class='input-small' placeholder='���' name='availMoney'>");      
	      out.append("<button type='submit' class='btn' id='addButton2'>���</button>");
	      out.append("<button type='submit' class='btn' id='backButton'>����</button>");
	      out.append("</div>");
	      out.flush();
	      out.close();
	}

}
