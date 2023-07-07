package cash.dao;

import java.sql.*;
import java.util.*;
import cash.vo.Hashtag;

public class HashtagDao {
	// 월별 해시태크의 수
	public List<Map<String,Object>> selectWordCountByMonth(Connection conn, String memberId, int targetYear, int targetMonth) throws Exception{
		List<Map<String,Object>> list = new ArrayList<>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT word, COUNT(*) cnt FROM hashtag h INNER JOIN cashbook c ON h.cashbook_no = c.cashbook_no WHERE c.member_id = ? AND YEAR(c.cashbook_date) = ? AND MONTH(c.cashbook_date) = ? GROUP BY word ORDER BY COUNT(*) DESC";
		try {
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
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return list;
	}
	// 해시태그 추가
	public int insertHashtag(Connection conn, Hashtag hashtag) throws Exception {
		int row = 0;
		
		PreparedStatement stmt = null;
		String sql = "INSERT INTO hashtag(cashbook_no, word, updatedate, createdate) VALUES(?,?,NOW(),NOW())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, hashtag.getCashbookNo());
			stmt.setString(2, hashtag.getWord());
			row = stmt.executeUpdate();    
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return row;
	}
}
