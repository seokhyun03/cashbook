package cash.service;

import java.sql.*;
import java.util.*;

import cash.dao.HashtagDao;
import cash.vo.Cashbook;

public class HashtagService {
	private HashtagDao hashtagDao;
	private String dbUrl = "jdbc:mariadb://127.0.0.1:3306/cash";
	private String dbId = "root";
	private String dbPw = "java1234";
	
	public List<Map<String,Object>> getWordCountByMonth(String memberId, int targetYear, int targetMonth) {
		List<Map<String,Object>> list = null;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			hashtagDao = new HashtagDao();
			list = hashtagDao.selectWordCountByMonth(conn, memberId, targetYear, targetMonth);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
