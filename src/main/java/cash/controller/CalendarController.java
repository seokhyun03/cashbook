package cash.controller;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import cash.service.CashbookService;
import cash.service.HashtagService;
import cash.vo.*;
import cash.service.CounterService;

@WebServlet("/on/calendar")
public class CalendarController extends HttpServlet {
	private CashbookService cashbookService;
	private HashtagService hashtagService;
	private CounterService counterService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		
		// view에 넘겨줄 달력정보(모델값)
		Calendar firstDay = Calendar.getInstance(); // 오늘 날짜
		int targetYear = firstDay.get(Calendar.YEAR);
		int targetMonth = firstDay.get(Calendar.MONTH);
		
		// 출력하고자하는 년도와 월이 매개값으로 넘어왔다면
		if(request.getParameter("targetYear") != null) {
			targetYear = Integer.parseInt(request.getParameter("targetYear"));
			firstDay.set(Calendar.YEAR, targetYear);
			targetYear = firstDay.get(Calendar.YEAR);
		}
		if(request.getParameter("targetMonth") != null) {
			targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
			// API에서 자동으로 Calendar.MONTH값으로 12가 입력되면 월 1, 년 + 1
			// API에서 자동으로 Calendar.MONTH값으로 -1가 입력되면 월 12, 년 - 1
			firstDay.set(Calendar.MONTH, targetMonth);
			targetMonth = firstDay.get(Calendar.MONTH);
			targetYear = firstDay.get(Calendar.YEAR);
		}
		firstDay.set(Calendar.DATE, 1);	// 1일
		
		// 달력 출력시 시작공백 -> 1일날짜의 요일(일1, 월2, ..., 토6) - 1
		int beginBlank = firstDay.get(Calendar.DAY_OF_WEEK) - 1; 
		System.out.println(beginBlank +" <- beginBlank");
		
		// 출력되는 월의 마지막 날짜
		int lastDate = firstDay.getActualMaximum(Calendar.DATE);
		System.out.println(lastDate +" <- lastDate");
		
		// 달력출력시 마지막 날짜 출력 후 공백 개수 -> 전체 출력 셀의 개수가 7로 나누어 떨어져야 한다
		int endBlank = 0;
		if ((beginBlank + lastDate) % 7 != 0) {
			endBlank = 7 - ((beginBlank + lastDate) % 7);
		}
		int totalCell = beginBlank + lastDate + endBlank;
		System.out.println(endBlank +" <- endBlank");
		System.out.println(totalCell +" <- totalCell");
		
		// 전달 마지막 날
		int preMonthLastDate = 0;
		Calendar PreMonth = Calendar.getInstance();
		PreMonth.set(Calendar.YEAR, targetYear);
		PreMonth.set(Calendar.MONTH, targetMonth-1);
		preMonthLastDate = PreMonth.getActualMaximum(Calendar.DATE);
		
		// 오늘 날짜
		Calendar today = Calendar.getInstance();
		int todayYear = today.get(Calendar.YEAR);
		int todayMonth = today.get(Calendar.MONTH);
		int todayDate = today.get(Calendar.DATE);
		
		// 모델을 호출(DAO 타겟 월의 수입/지출 데이터)
		cashbookService = new CashbookService();
		hashtagService = new HashtagService();
		List<Cashbook> list = cashbookService.getPriceByMonth(member.getMemberId(), targetYear, targetMonth+1);
		List<Map<String, Object>> hashtagList = hashtagService.getWordCountByMonth(member.getMemberId(), targetYear, targetMonth+1);
		
		counterService = new CounterService();
		int count = counterService.getCounter();
		int totalCount = counterService.getCounterAll();
		
		// 뷰에 값넘기기(request 속성)
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("beginBlank", beginBlank);
		request.setAttribute("lastDate", lastDate);
		request.setAttribute("endBlank", endBlank);
		request.setAttribute("totalCell", totalCell);
		
		request.setAttribute("preMonthLastDate", preMonthLastDate);
		
		request.setAttribute("todayYear", todayYear);
		request.setAttribute("todayMonth", todayMonth);
		request.setAttribute("todayDate", todayDate);
		
		request.setAttribute("list", list);
		request.setAttribute("hashtagList", hashtagList);
		
		request.setAttribute("count", count);
		request.setAttribute("totalCount", totalCount);
		// 달력을 출력하는 뷰
		request.getRequestDispatcher("/WEB-INF/view/calendar.jsp").forward(request, response);
	}
}
