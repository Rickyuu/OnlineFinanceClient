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
		apply.setApplystate("提交主持");
		// 添加一张新的申请表，并且获得返回的申请表ID
		Long applyid = DaoFactory.getFinanceApplyDao().addApply(apply);
		request.getSession().setAttribute("applyid", applyid);
		List<ApplyItem> items = DaoFactory.getApplyItemDao().getItemsInApply(applyid);
		
		//响应给客户端     
	      response.setContentType("text/html;charset=UTF-8");     
	      PrintWriter out = response.getWriter();     
	      out.append("<table class='table table-striped' id='applytable'>");
	      out.append("<tr><th>日期</th><th>描述</th><th>金额</th></tr>");
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
	      out.append("<input type='text' class='input-mini' name='newYear'>年");
	      out.append("<input type='text' class='input-mini' name='newMonth'>月");
	      out.append("<input type='text' class='input-mini' name='newDay'>日");
	      out.append("<input type='text' class='input-xxlarge' placeholder='描述' name='describe'>");
	      out.append("<input type='text' class='input-small' placeholder='金额' name='availMoney'>");      
	      out.append("<button type='submit' class='btn' id='addButton2'>添加</button>");
	      out.append("<button type='submit' class='btn' id='backButton'>返回</button>");
	      out.append("</div>");
	      out.flush();
	      out.close();
	}

}
