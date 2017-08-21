package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class C3P0Utils {
	
private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
//得到一个数据源
	private static DataSource DataSource = new ComboPooledDataSource();
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	//从数据源中得到一个连接对象
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("服务器错误");
		}
		return conn;
	}
	
	public static void release(Connection conn, Statement stmt, ResultSet rs){
		//关闭资源
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
	}
}
