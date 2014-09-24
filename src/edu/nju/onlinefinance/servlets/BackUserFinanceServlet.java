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
import edu.nju.onlinefinance.model.FinanceApply;

/**
 * Servlet implementation class BackUserFinanceServlet
 */
@WebServlet("/BackUserFinanceServlet")
public class BackUserFinanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackUserFinanceServlet() {
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
		response.setContentType("text/html;charset=UTF-8");     
	      PrintWriter out = response.getWriter();     
	      out.append("<table class='table table-striped' id='financelisttable'>");
	      out.append("<tr><th>申请表编号</th><th>项目编号</th><th>项目名称</th><th>申请日期</th><th>申请状态</th><th>查看</th></tr>");
	      List<FinanceApply> applies = DaoFactory.getFinanceApplyDao().getAllApplies();	      
	      Long userid = (Long) request.getSession().getAttribute("userid");
	      for(int i=0; i<applies.size(); i++){
	      	if(applies.get(i).getUserid().equals(userid)){
	      		FinanceApply apply = applies.get(i);
	      		out.append("<tr class='viewline'>");
	      		out.append("<td class='viewcolumn'>"+apply.getApplyid()+"</td>");
	      		out.append("<td>"+apply.getProjectid()+"</td>");
	      		out.append("<td>"+DaoFactory.getProjectDao().getProjectById(apply.getProjectid()).getProjectname()+"</td>");
	      	//	Date currentDate = new java.sql.Date(System.currentTimeMillis());
	      		out.append("<td>"+apply.getApplydate()+"</td>");
	      		out.append("<td>"+apply.getApplystate()+"</td>");
	      		out.append("<td>");
	      		out.append("<div class='btn'><i class='icon-camera'></i></div>");
	      		out.append("</td>");
	      		out.append("</tr>");
	      	}
	      }
	      out.append("</table>");
	      out.append("<div class='form-inline'>");
	      out.append("<input type='text' class='input-medium' placeholder='项目编号' name='projectId'>");
	      out.append("<button type='submit' class='btn' id='addButton'>添加</button>");
	      out.append("</div>");
	      out.flush();
	      out.close();
	}

}
