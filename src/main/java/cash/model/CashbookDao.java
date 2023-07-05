package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cash.vo.Cashbook;

public class CashbookDao {
	public List<Cashbook> selectCashbookListByHashtag(String memberId, int targetYear, int targetMonth, String word, int beginRow, int rowPerPage){
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT c.cashbook_no cashbookNo, c.category, c.price, c.memo, c.cashbook_date cashbookDate, c.updatedate, c.createdate FROM cashbook c INNER JOIN hashtag h ON c.cashbook_no = h.cashbook_no WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND h.word = ? ORDER BY c.cashbook_date ASC LIMIT ?,?";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
	        stmt.setInt(2, targetYear);
	        stmt.setInt(3, targetMonth);
	        stmt.setString(4, word);
	        stmt.setInt(5, beginRow);
	        stmt.setInt(6, rowPerPage);
	        rs = stmt.executeQuery();
	        System.out.println(stmt);
	        while(rs.next()) {
	        	Cashbook c = new Cashbook();
	         	c.setCashbookNo(rs.getInt("cashbookNo"));
	        	c.setCategory(rs.getString("category"));
	        	c.setPrice(rs.getInt("price"));
	        	c.setMemo(rs.getString("memo"));
	        	c.setCashbookDate(rs.getString("cashbookDate"));
	        	c.setUpdatedate(rs.getString("updatedate"));
	        	c.setCreatedate(rs.getString("createdate"));
	        	list.add(c);
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
	
	public int selectCashbookListByHashtagCnt(String memberId, int targetYear, int targetMonth, String word){
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM cashbook c INNER JOIN hashtag h ON c.cashbook_no = h.cashbook_no WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND h.word = ?";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
	        stmt.setInt(2, targetYear);
	        stmt.setInt(3, targetMonth);
	        stmt.setString(4, word);
	        rs = stmt.executeQuery();
	        System.out.println(stmt);
	        if(rs.next()) {
	        	cnt = rs.getInt(1);
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
		return cnt;
	}
	
	// 반환값 : cashbook_no 키 값
	public int insertCashbook(Cashbook cashbook) {
		int cashbookNo = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO cashbook(member_id, category, cashbook_date, price, memo, updatedate, createdate) VALUES(?,?,?,?,?,NOW(),NOW())";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cashbook.getMemberId());
			stmt.setString(2, cashbook.getCategory());
			stmt.setString(3, cashbook.getCashbookDate());
			stmt.setInt(4, cashbook.getPrice());
			stmt.setString(5, cashbook.getMemo());
			int row = stmt.executeUpdate();
	        rs = stmt.getGeneratedKeys();
	        if(rs.next()) {
	        	cashbookNo = rs.getInt(1);
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
		return cashbookNo;
	}
	
	public List<Cashbook> selectCashbookListByMonth(String memberId, int targetYear, int targetMonth){
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, category, price, cashbook_date cashbookDate FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? ORDER BY cashbook_date ASC";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
		    stmt.setString(1, memberId);
	        stmt.setInt(2, targetYear);
	        stmt.setInt(3, targetMonth);
	        rs = stmt.executeQuery();
	        while(rs.next()) {
	        	Cashbook c = new Cashbook();
	        	c.setCashbookNo(rs.getInt("cashbookNo"));
	        	c.setCategory(rs.getString("category"));
	        	c.setPrice(rs.getInt("price"));
	        	c.setCashbookDate(rs.getString("cashbookDate"));
	        	list.add(c);
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
	
	public List<Cashbook> selectCashbookListByDate(String memberId, int targetYear, int targetMonth, int targetDate, int beginRow, int rowPerPage){
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, category, price, memo, cashbook_date cashbookDate, updatedate, createdate FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ? ORDER BY cashbook_date ASC";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
		    stmt.setString(1, memberId);
	        stmt.setInt(2, targetYear);
	        stmt.setInt(3, targetMonth);
	        stmt.setInt(4, targetDate);
	        stmt.setInt(5, beginRow);
	        stmt.setInt(6, rowPerPage);
	        rs = stmt.executeQuery();
	        System.out.println(stmt);
	        while(rs.next()) {
	        	Cashbook c = new Cashbook();
	        	c.setCashbookNo(rs.getInt("cashbookNo"));
	        	c.setCategory(rs.getString("category"));
	        	c.setPrice(rs.getInt("price"));
	        	c.setMemo(rs.getString("memo"));
	        	c.setCashbookDate(rs.getString("cashbookDate"));
	        	c.setUpdatedate(rs.getString("updatedate"));
	        	c.setCreatedate(rs.getString("createdate"));
	        	list.add(c);
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
	
	public int selectCashbookListByDateCnt(String memberId, int targetYear, int targetMonth, int targetDate){
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ?";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
		    stmt.setString(1, memberId);
	        stmt.setInt(2, targetYear);
	        stmt.setInt(3, targetMonth);
	        stmt.setInt(4, targetDate);
	        rs = stmt.executeQuery();
	        System.out.println(stmt);
	        if(rs.next()) {
	        	cnt = rs.getInt(1);
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
		return cnt;
	}
}
