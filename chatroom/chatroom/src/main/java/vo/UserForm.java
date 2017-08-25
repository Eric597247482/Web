package vo;

import java.util.HashMap;
import java.util.Map;

public class UserForm {
	private int id;
	private String username;
	private String password;
	private String repassword;
	private String type;
	
	Map<String, String> msg = new HashMap<String, String>();
	/*用户名：必须输入，且由3~8位的字母组成
	 * 密码：必须输入，3~8位的数字组成
	 * 确认密码：和密码保持一致
	 * 权限类型必须为：admin或user
	 * */
	
	public boolean validate(){
		
		if("".equals(username)){
			msg.put("username", "用户名不能为空！");
		}else if(!username.matches("\\w{3,8}")){//用户名：必须输入，且由2~8位的字母组成
			msg.put("username", "用户名为3~8位的字母组成");
		}
		
		if("".equals(password)){
			msg.put("password", "密码不能为空！");
		}else if(!password.matches("\\w{2,8}")){//密码：必须输入，3~8位的数字组成
			msg.put("password", "密码为3~8位的数字组成");
		}
		
		//确认密码：和密码一致
		if(!repassword.equals(password)){
			msg.put("repassword", "两次密码不一致！");
		}
		
		//权限
		if(!type.equals("admin") && !type.equals("user")){
			msg.put("type", "权限只能为admin或user！");
		}
		
		return msg.isEmpty();//当map集合中没有数据时，返回true
		
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

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public Map<String, String> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, String> msg) {
		this.msg = msg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
