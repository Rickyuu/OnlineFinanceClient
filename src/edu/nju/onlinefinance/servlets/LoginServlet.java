package edu.nju.onlinefinance.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlinefinance.factory.DaoFactory;
import edu.nju.onlinefinance.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>"+"Hello World!</title></head>"+"Hello World!</h1></body></html>");

		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
//		testProDeadline();
		String useridString = request.getParameter("userid");
		Long userid = Long.parseLong(useridString);
		if(userid == 1012500){
			request.getSession().setAttribute("userid", userid);
			request.getRequestDispatcher("/manager/manage_user.jsp").forward(request, response);
		}else{
			User user = DaoFactory.getUserDao().getUserById(userid);
			if(user == null){
				// TODO 没有找到这个user的时候怎么办
			}else{
				// TODO 找到但不是管理员账号
				String userIdentity = user.getIdentity();
				if(userIdentity.equals("项目主持")){
					request.getSession().setAttribute("userid", userid);
					request.getRequestDispatcher("/user/host_project.jsp").forward(request, response);
				}else if(userIdentity.equals("普通用户")){
					request.getSession().setAttribute("userid", userid);
					request.getRequestDispatcher("/user/user_finance.jsp").forward(request, response);
				}else if(userIdentity.equals("财务审核")){
					request.getSession().setAttribute("userid", userid);
					request.getRequestDispatcher("/user/account_finance.jsp").forward(request, response);
				}else if(userIdentity.equals("财务主管")){
					request.getSession().setAttribute("userid", userid);
					request.getRequestDispatcher("/user/gov_project.jsp").forward(request, response);
				}else{
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<html><head><title>"+"Hello World!</title></head>"+"Hello World!</h1></body></html>");
				}			
			}
		}	
	}
	
	// 检测项目是否过期
//	private void testProDeadline(){
//		Date currentDate = new Date(System.currentTimeMillis());
//		List<Project> projects = DaoFactory.getProjectDao().getAllProjects();
//		for(int i=0; i<projects.size(); i++) {
//			Date proDeadline = projects.get(i).getDeadline();
//			if(proDeadline.compareTo(currentDate) < 0) {
//				DaoFactory.getProjectDao().removeProject(projects.get(i).getProjectid());
//			}
//		}
//	}
	

}
