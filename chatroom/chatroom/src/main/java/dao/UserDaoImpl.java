package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DBUtils;
import vo.User;

public class UserDaoImpl implements UserDao {

	public User login(User user){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User existUser = null;
		
		try {
			conn = DBUtils.getConnection();
			String sql = "select * from user where username=? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			
			if(rs.first()){
				existUser = new User();
				existUser.setId(rs.getInt(1));
				existUser.setUsername(rs.getString(2));
				existUser.setPassword(rs.getString(3));
				existUser.setType(rs.getString(4));
			}
//			if(existUser==null)
//				System.out.print("根本没查到数据！！！");
		}  catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("用户登录失败!");
		}finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return existUser;
	}

	//添加注册用户
	@Override
	public void addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtils.getConnection();
			String sql = "insert into user(username,password,type) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getType());
			
//			int i = ps.executeUpdate();
			ps.execute();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("注册失败！");
		}finally{
			DBUtils.closeAll(null, ps, conn);
		}
		
		
	}

	//以用户名查找用户,是否已经存在该用户？
	@Override
	public boolean findUserByName(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			String sql = "select * from user where username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(rs.next())
				return true;//查到该用户，返回true
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(null, ps, conn);
		}
		return false;//未查到该用户，该用户名未被注册
	
	}
	
	
	
	
//	public User login(User user) {
//		//创建QueryRunner对象
//		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
//		//编写SQL语句
//		String sql = "select * from user where username = ? and password = ? ";
//		User existUser;
//		
//		try {
//			existUser = queryRunner.query(sql, new BeanHandler<User>(User.class), user.getUsername(),user.getPassword());
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException("用户登录失败!");
//		}
//		
//		return existUser;
//	}


	
}
