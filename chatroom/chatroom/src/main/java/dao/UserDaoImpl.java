package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
