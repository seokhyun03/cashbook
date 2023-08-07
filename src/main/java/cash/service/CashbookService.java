package cash.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cash.dao.CashbookDao;
import cash.dao.HashtagDao;
import cash.vo.Cashbook;
import cash.vo.Hashtag;

public class CashbookService {
	private CashbookDao cashbookDao;
	private HashtagDao hashtagDao;
	private String dbUrl = "jdbc:mariadb://127.0.0.1:3306/cash";
	private String dbId = "root";
	private String dbPw = "java1234";
	
	public List<Cashbook> getPriceByMonth(String memberId, int targetYear, int targetMonth) {
		List<Cashbook> list = null;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			cashbookDao = new CashbookDao();
			list = cashbookDao.selectPriceByMonth(conn, memberId, targetYear, targetMonth);
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
	public List<Cashbook> getCashbookListByDate(String memberId, int targetYear, int targetMonth, int targetDate, int beginRow, int rowPerPage) {
		List<Cashbook> list = null;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			cashbookDao = new CashbookDao();
			list = cashbookDao.selectCashbookListByDate(conn, memberId, targetYear, targetMonth, targetDate, beginRow, rowPerPage);
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
	public Map<String, Object> getCashbookListByDateCnt(int currentPage, String memberId, int targetYear, int targetMonth, int targetDate) {
		int totalRow = 0;
		Connection conn = null;
		Map<String, Object> map = null;
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			cashbookDao = new CashbookDao();
			totalRow = cashbookDao.selectCashbookListByDateCnt(conn, memberId, targetYear, targetMonth, targetDate);
			
			int rowPerPage = 10;
			int beginRow = (currentPage-1)*rowPerPage;
			int lastPage = totalRow / rowPerPage;
			if (totalRow % rowPerPage != 0) {
				lastPage += 1;
			}
		
			int pagePerPage = 10;
			int startPage = (currentPage-1) / pagePerPage * pagePerPage + 1;
			int endPage = startPage + pagePerPage - 1;
			if(endPage > lastPage) {
				endPage = lastPage;
			}
			map = new HashMap<>();
			map.put("beginRow", beginRow);
			map.put("rowPerPage", rowPerPage);
			map.put("pagePerPage", pagePerPage);
			map.put("lastPage", lastPage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}	
	public List<Cashbook> getCashbookListByHashtag(String memberId, int targetYear, int targetMonth, String word, int beginRow, int rowPerPage) {
		List<Cashbook> list = null;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			cashbookDao = new CashbookDao();
			list = cashbookDao.selectCashbookListByHashtag(conn, memberId, targetYear, targetMonth, word, beginRow, rowPerPage);
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
	public int getCashbookListByHashtagCnt(String memberId, int targetYear, int targetMonth, String word) {
		int totalRow = 0;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			cashbookDao = new CashbookDao();
			totalRow = cashbookDao.selectCashbookListByHashtagCnt(conn, memberId, targetYear, targetMonth, word);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return totalRow;
	}
	public int addCashbook(Cashbook cashbook) {
		int row = 0;
		Connection conn = null;
		
		// 메모에 해시태그 중복 및 내용없는 해시태그 제거
		String memo = cashbook.getMemo().replace("#", " #");
		String rememo = "";
		List<String> words = new ArrayList<>();
		for(String w : memo.split(" ")) {
			if(w.startsWith("#")) {
				String word = w.replace("#", "");
				if(word.length() > 0) {
					int check = 0;
					for(String s : words) {
						if(s.equals(word)) {
							check = 1;
							break;
						}
					}
					if(check == 0) {
						rememo +="#" + word + " ";
						words.add(word);
					}
				}
			} else {
				rememo += w + " ";
			}
		}
		cashbook.setMemo(rememo);
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			conn.setAutoCommit(false);
			cashbookDao = new CashbookDao();
			hashtagDao = new HashtagDao();
			int cashbookNo = cashbookDao.insertCashbook(conn, cashbook);
			
			// 입력 성공 시 캐시북 메모에서 해시태그 존재 여부 확인
			// 해시태그 있을 경우 추출하여 데이터베이스에 입력
			for(String w : cashbook.getMemo().split(" ")) {
				if(w.startsWith("#")) {
					String word = w.replace("#", "");
					if(word.length() > 0) {
						Hashtag hashtag = new Hashtag();
						hashtag.setCashbookNo(cashbookNo);
						hashtag.setWord(word);
						row += hashtagDao.insertHashtag(conn, hashtag);
					}
				}
			}
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				row = -1;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}
