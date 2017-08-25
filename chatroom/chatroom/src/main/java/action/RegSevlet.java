package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import service.UserService;
import service.UserServiceImpl;
import vo.User;
import vo.UserForm;

/**
 * Servlet implementation class reg
 */
//@WebServlet("/reg")
public class RegSevlet extends HttpServlet {
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//获取表单数据
		UserForm uf = new UserForm();
		
		try {
			BeanUtils.populate(uf, request.getParameterMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(!uf.validate()){//如果Map中不为空，则说明有错误信息
			request.setAttribute("uf", uf);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//调用业务逻辑
		UserService us = new UserServiceImpl();
		//查看用户名是否已经被注册
		if(us.findUserByName(user.getUsername())){
			//为真，此用户已被注册
			response.getWriter().write("该用户名已被使用，请重新注册");
			response.setHeader("refresh", "1;url="+request.getContextPath()+"/register.jsp");
		}else{
			us.register(user);
			//分发转向
			response.getWriter().write("注册成功，1秒钟后跳转到主页。");
			response.setHeader("refresh", "1;url="+request.getContextPath()+"/index.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
