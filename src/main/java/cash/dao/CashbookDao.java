package cash.dao;

import java.sql.*;
import java.util.*;
import cash.vo.Cashbook;

public class CashbookDao {
	// 월별 수입/지출 금액 조회
	public List<Cashbook> selectPriceByMonth(Connection conn, String memberId, int targetYear, int targetMonth) throws Exception {
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, category, price, cashbook_date cashbookDate FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? ORDER BY cashbook_date ASC";
		try {
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
		} catch (SQLException e) {
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
	// 일별 캐시북리스트 조회
	public List<Cashbook> selectCashbookListByDate(Connection conn, String memberId, int targetYear, int targetMonth, int targetDate, int beginRow, int rowPerPage) throws Exception {
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, category, price, memo, cashbook_date cashbookDate, updatedate, createdate FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ? ORDER BY cashbook_date ASC";
		try {
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
		} catch (SQLException e) {
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
	// 일별 캐시북 수
	public int selectCashbookListByDateCnt(Connection conn, String memberId, int targetYear, int targetMonth, int targetDate) throws Exception {
		int cnt = 0;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ?";
		try {
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
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return cnt;
	}
	// 해시태그별 캐시북 조회
	public List<Cashbook> selectCashbookListByHashtag(Connection conn, String memberId, int targetYear, int targetMonth, String word, int beginRow, int rowPerPage) throws Exception {
		List<Cashbook> list = new ArrayList<Cashbook>();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT c.cashbook_no cashbookNo, c.category, c.price, c.memo, c.cashbook_date cashbookDate, c.updatedate, c.createdate FROM cashbook c INNER JOIN hashtag h ON c.cashbook_no = h.cashbook_no WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND h.word = ? ORDER BY c.cashbook_date ASC LIMIT ?,?";
		try {
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
		} catch (SQLException e) {
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
	// 해시태그별 캐시북 수
	public int selectCashbookListByHashtagCnt(Connection conn, String memberId, int targetYear, int targetMonth, String word) throws Exception {
		int cnt = 0;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM cashbook c INNER JOIN hashtag h ON c.cashbook_no = h.cashbook_no WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND h.word = ?";
		try {
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
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return cnt;
	}
	// 캐시북 추가
	public int insertCashbook(Connection conn, Cashbook cashbook) throws Exception {
		// 반환값 : cashbook_no -> 키 값
		int cashbookNo = 0;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO cashbook(member_id, category, cashbook_date, price, memo, updatedate, createdate) VALUES(?,?,?,?,?,NOW(),NOW())";
		try {
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
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return cashbookNo;
	}
	// 캐시북 수정
	public int updateCashbook(Connection conn, Cashbook cashbook) throws Exception {
		int row = 0;
		
		PreparedStatement stmt = null;
		String sql = "UPDATE cashbook SET category=?, price=?, memo=?, updatedate=NOW() WHERE cashbook_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cashbook.getCategory());
			stmt.setInt(2, cashbook.getPrice());
			stmt.setString(3, cashbook.getMemo());
			stmt.setInt(4, cashbook.getCashbookNo());
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
	
	// 캐시북 1개 조회
	public Cashbook selectCashbookOne(Connection conn, Cashbook cashbook) throws Exception {
		Cashbook c = null;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT category, cashbook_date cashbookDate, price, memo FROM cashbook WHERE cashbook_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbook.getCashbookNo());
			rs = stmt.executeQuery();
			if(rs.next()) {
	        	c = new Cashbook();
	        	c.setCashbookNo(cashbook.getCashbookNo());
	        	c.setCategory(rs.getString("category"));
	        	c.setCashbookDate(rs.getString("cashbookDate"));
	        	c.setPrice(rs.getInt("price"));
	        	c.setMemo(rs.getString("memo"));
	        }
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return c;
	}
	
	public int deleteCashbook(Connection conn, Cashbook cashbook)  throws Exception {
		int row = 0;
		
		PreparedStatement stmt = null;
		String sql = "DELETE FROM cashbook WHERE cashbook_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbook.getCashbookNo());
			row = stmt.executeUpdate();
			System.out.println(stmt);
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
