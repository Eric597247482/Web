package vo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener{
	private int id;
	private String username;
	private String password;
	private String type;
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	//将javaBean对象与session绑定
	//session,setAttribute();
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("进入了...");
		//通过事件对象获得事件源对象
		HttpSession session = event.getSession();
		//从ServletContext中获得人员列表的Map集合
		Map<User, HttpSession> userMap = (Map<User, HttpSession>) session
				.getServletContext().getAttribute("userMap");
		//将用户对象与其对应session存入map集合中
		userMap.put(this, session);
	}
	
	//将JavaBean对象与session解除绑定
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("退出了...");
		//通过事件对象获得事件源对象
		HttpSession session = event.getSession();
		//从ServletContext中获得人员列表的Map集合
		Map<User, HttpSession> userMap = (Map<User, HttpSession>) session
				.getServletContext().getAttribute("userMap");
		//将用户从map集合中移除
		userMap.remove(this);
	}
	
	
	
}
