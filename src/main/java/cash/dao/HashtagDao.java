package cash.dao;

import java.sql.*;
import java.util.*;
import cash.vo.*;

public class HashtagDao {
	public List<Map<String,Object>> selectWordCountByMonth(String memberId, int targetYear, int targetMonth){
		List<Map<String,Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT word, COUNT(*) cnt FROM hashtag h INNER JOIN cashbook c ON h.cashbook_no = c.cashbook_no WHERE c.member_id = ? AND YEAR(c.cashbook_date) = ? AND MONTH(c.cashbook_date) = ? GROUP BY word ORDER BY COUNT(*) DESC";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			 stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
	        stmt.setInt(3, targetMonth);
	        rs = stmt.executeQuery();
	        System.out.println(stmt);
	        while(rs.next()) {
	        	Map<String,Object> m = new HashMap<String, Object>();
	        	m.put("word", rs.getString("word"));
	        	m.put("cnt", rs.getInt("cnt"));
	        	list.add(m);
	        }
		} catch (Exception e1) {
			e1.printStackTrace();	
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}	
		}
		return list;
	}
	public int insertHashtag(Hashtag hashtag) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO hashtag(cashbook_no, word, updatedate, createdate) VALUES(?,?,NOW(),NOW())";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, hashtag.getCashbookNo());
			stmt.setString(2, hashtag.getWord());
			row = stmt.executeUpdate();    
		} catch (Exception e1) {
			e1.printStackTrace();	
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}	
		}
		return row;
	}
}
