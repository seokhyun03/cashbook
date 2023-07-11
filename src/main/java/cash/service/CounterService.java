package cash.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cash.dao.CounterDao;

public class CounterService {
	private CounterDao counterDao;
	private String dbUrl = "jdbc:mariadb://127.0.0.1:3306/cash";
	private String dbId = "root";
	private String dbPw = "java1234";
	
	public void addCounter() {
		this.counterDao = new CounterDao();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			counterDao.insertCounter(conn);
		} catch(Exception e){	
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void modifyCounter() {
		this.counterDao = new CounterDao();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			counterDao.updateCounter(conn);
		} catch(Exception e){	
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getCounter() {
		int count = 0;
		this.counterDao = new CounterDao();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			count = counterDao.selectCounterCurdate(conn);
		} catch(Exception e){	
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int getCounterAll() {
		int count = 0;
		this.counterDao = new CounterDao();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			count = counterDao.selectCounterAll(conn);
		} catch(Exception e){	
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
