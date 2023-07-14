package cash.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.service.CashbookService;
import cash.vo.Cashbook;
import cash.vo.Member;

@WebServlet("/on/cashbook")
public class CashbookListController extends HttpServlet {
	private CashbookService cashbookService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		String cashbookDate = "" + targetYear;
		if(targetMonth<10) {
			cashbookDate += "-0" + (targetMonth+1);
		} else {
			cashbookDate += "-" + (targetMonth+1);
		}
		if(targetDate<10) {
			cashbookDate += "-0" + targetDate;
		} else {
			cashbookDate += "-" + targetDate;
		}
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		cashbookService = new CashbookService();
		Map<String, Object> map = cashbookService.getCashbookListByDateCnt(currentPage, member.getMemberId(), targetYear, targetMonth, targetDate);
		List<Cashbook> list = cashbookService.getCashbookListByDate(member.getMemberId(), targetYear, targetMonth+1, targetDate, (int)map.get("beginRow"), (int)map.get("rowPerPage"));
		
		request.setAttribute("member", member);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		request.setAttribute("cashbookDate", cashbookDate);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pagePerPage", (int)map.get("pagePerPage"));
		request.setAttribute("startPage", (int)map.get("startPage"));
		request.setAttribute("lastPage", (int)map.get("lastPage"));
		request.setAttribute("endPage", (int)map.get("endPage"));
		request.setAttribute("list", list);
		// 이번달 달력에 가계목록의 모델값을 셋팅
		request.getRequestDispatcher("/WEB-INF/view/cashbookList.jsp").forward(request, response);
	}
}
