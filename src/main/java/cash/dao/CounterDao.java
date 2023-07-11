package cash.dao;

import java.sql.*;

public class CounterDao {
	// 오늘날짜 첫번째 점속 -> insert
	public void insertCounter(Connection conn) throws Exception {
		
		PreparedStatement stmt = null;
		String sql = "INSERT INTO counter(counter_date, counter_num) VALUES(CURDATE(), 1)";
		try {
			stmt = conn.prepareStatement(sql);
	        int row = stmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
			// 예외를 던져야할 때 강제로 예외를 발생
			throw new Exception(); 
		}finally {
			try {
				stmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
	}
	// 오늘날짜 첫번째 접속X -> update
	public void updateCounter(Connection conn) throws Exception {
		
		PreparedStatement stmt = null;
		String sql = "UPDATE counter SET counter_num = counter_num+1 WHERE counter_date = CURDATE()";
		try {
			stmt = conn.prepareStatement(sql);
	        int row = stmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
			// 예외를 던져야할 때 강제로 예외를 발생
			throw new Exception(); 
		}finally {
			try {
				stmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
	}
	// 오늘날짜 카운터
	public int selectCounterCurdate(Connection conn) throws Exception {
		int count = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT counter_num FROM counter WHERE counter_date = CURDATE()";
		try {
			stmt = conn.prepareStatement(sql);
	        rs = stmt.executeQuery();
	        if(rs.next()) {
	        	count = rs.getInt(1);
	        }
		} catch(SQLException e){
			e.printStackTrace();
			// 예외를 던져야할 때 강제로 예외를 발생
			throw new Exception(); 
		}finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
		return count;
	}
	// 누적 카운터
	public int selectCounterAll(Connection conn) throws Exception {
		int count = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT SUM(counter_num) FROM counter";
		try {
			stmt = conn.prepareStatement(sql);
	        rs = stmt.executeQuery();
	        if(rs.next()) {
	        	count = rs.getInt(1);
	        }
		} catch(SQLException e){
			e.printStackTrace();
			// 예외를 던져야할 때 강제로 예외를 발생
			throw new Exception(); 
		}finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
		return count;
	}
}
