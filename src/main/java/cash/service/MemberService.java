package cash.service;

import java.sql.*;
import cash.dao.MemberDao;
import cash.vo.Member;

public class MemberService {
	private MemberDao memberDao;
	private String dbUrl = "jdbc:mariadb://127.0.0.1:3306/cash";
	private String dbId = "root";
	private String dbPw = "java1234";
	
	public Member getMemberOne(String memberId) {
		Member member = null;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			member = memberDao.selectMemberOne(conn, memberId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return member;
	}
	public int addMember(Member member) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			row = memberDao.insertMember(conn, member);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	public int removeMember(Member member) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			row = memberDao.deleteMember(conn, member);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	public Member getMemberById(Member paramMember) {
		Member returnMember = null;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			returnMember = memberDao.selectMemberById(conn, paramMember);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnMember;
	}
}
