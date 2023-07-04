package cash.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.vo.Cashbook;
import cash.vo.Member;

@WebServlet("/cashbook")
public class CashbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 유효성 검사(로그인 확인)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
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

		List<Cashbook> list = new CashbookDao().selectCashbookListByDate(member.getMemberId(), targetYear, targetMonth+1, targetDate);
		
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		request.setAttribute("cashbookDate", cashbookDate);
		request.setAttribute("list", list);
		// 이번달 달력에 가계목록의 모델값을 셋팅
		request.getRequestDispatcher("/WEB-INF/view/cashbookList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
