package cash.dao;

import java.sql.*;
import cash.vo.Member;

public class MemberDao {
	// 회원 정보 조회
	public Member selectMemberOne(Connection conn, String memberId) throws Exception {
		Member member = null;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId, member_pw memeberPw, updatedate, createdate FROM member WHERE member_id = ?";
		try {
			stmt = conn.prepareStatement(sql);
		    stmt.setString(1, memberId);
	        rs = stmt.executeQuery();
	        if(rs.next()) {
	        	member = new Member();
	        	member.setMemberId(rs.getString("memberId"));
	        	member.setMemberPw(rs.getString("memeberPw"));
	        	member.setUpdatedate(rs.getString("updatedate"));
	        	member.setCreatedate(rs.getString("createdate"));
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
		return member;
	}
	
	// 회원 가입
	public int insertMember(Connection conn, Member member) throws Exception {
		int row = 0;
		
		PreparedStatement stmt = null;
		String sql = "INSERT INTO member(member_id, member_pw, updatedate, createdate) VALUES(?,PASSWORD(?),NOW(),NOW())";
		try {
			stmt = conn.prepareStatement(sql);
		    stmt.setString(1,member.getMemberId());
	        stmt.setString(2, member.getMemberPw());
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
	// 회원 탈퇴
	public int deleteMember(Connection conn, Member member) throws Exception {
		int row = 0;
		
		PreparedStatement stmt = null;
		String sql = "DELETE FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		try {
			stmt = conn.prepareStatement(sql);
		    stmt.setString(1,member.getMemberId());
	        stmt.setString(2, member.getMemberPw());
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
	// 로그인 시 아이디 및 비번 확인
	public Member selectMemberById(Connection conn, Member paramMember) throws Exception {
		Member returnMember = null;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		try {
			stmt = conn.prepareStatement(sql);
		    stmt.setString(1, paramMember.getMemberId());
	        stmt.setString(2, paramMember.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("memberId"));
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
		return returnMember;
	}
}
