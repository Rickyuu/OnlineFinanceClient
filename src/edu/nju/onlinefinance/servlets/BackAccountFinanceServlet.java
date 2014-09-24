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
import edu.nju.onlinefinance.model.Project;

/**
 * Servlet implementation class BackAccountFinanceServlet
 */
@WebServlet("/BackAccountFinanceServlet")
public class BackAccountFinanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackAccountFinanceServlet() {
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
		//��Ӧ���ͻ���     
	      response.setContentType("text/html;charset=UTF-8");     
	      PrintWriter out = response.getWriter();     
	      out.append("<table class='table table-striped' id='accountfinancetable'>");
	      out.append("<tr><th>�������</th><th>��Ŀ���</th><th>��Ŀ����</th><th>�����˿���</th><th>����������</th><th>��������</th><th>�鿴</th></tr>");
	      List<Project> projects = DaoFactory.getProjectDao().getAllProjects();
	      for(int i=0; i<projects.size(); i++){
	      		Project project = projects.get(i);
	      		Long projectid = project.getProjectid();
	      		// ��������Ŀ���е���������г����С��ύ���񡱵Ĳ���
	      		List<FinanceApply> applies = DaoFactory.getFinanceApplyDao().getAllApplies();
	      		for(int j=0; j<applies.size(); j++){
	      			// �������Ŀ,������"�ύ����"
	      			if(applies.get(j).getProjectid().equals(projectid) && applies.get(j).getApplystate().equals("�ύ����")){
	      				FinanceApply apply = applies.get(j);
	      				out.print("<tr class='viewline'>");
	      	    		out.print("<td class='viewcolumn'>"+apply.getApplyid()+"</td>");
	      	    		out.print("<td>"+project.getProjectid()+"</td>");
	      	    		out.print("<td>"+project.getProjectname()+"</td>");
	      	    		out.print("<td>"+apply.getUserid()+"</td>");
	      	    		out.print("<td>"+DaoFactory.getUserDao().getUserById(apply.getUserid()).getUserName()+"</td>");
	      	    		out.print("<td>"+apply.getApplydate()+"</td>");
	      	    		out.print("<td>");
	      	    		out.print("<div class='btn'><i class='icon-camera'></i></div>");
	      	    		out.print("</td>");
	      	    		out.print("</tr>");
	      			}
	      		}
	      }
	      out.append("</table>");
	      out.flush();
	      out.close();
	}

}
