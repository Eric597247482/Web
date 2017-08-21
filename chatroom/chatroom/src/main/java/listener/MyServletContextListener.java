package listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

import vo.User;
/**
 * 监听ServletContext对象的创建与销毁
 * @author Administrator
 *
 */
public class MyServletContextListener implements ServletContextListener {

	//ServletContext对象创建，下面这个方法就会执行
	//ServletContextEvent事件对象，监听对象--->ServletContext对象（事件源）
	public void contextInitialized(ServletContextEvent sce) {
		Map<User, HttpSession> userMap = new HashMap<User, HttpSession>();
		sce.getServletContext().setAttribute("userMap", userMap);
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	

}
