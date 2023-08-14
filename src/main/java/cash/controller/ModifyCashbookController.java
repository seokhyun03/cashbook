package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.service.CashbookService;
import cash.vo.Cashbook;
import cash.vo.Member;

/**
 * Servlet implementation class ModifyCashbookController
 */
@WebServlet("/on/modifyCashbook")
public class ModifyCashbookController extends HttpServlet {
	private CashbookService cashbookService;
	
	@Override // 캐시북 수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 아이디 변수 저장
		HttpSession session = request.getSession();
		String memberId = ((Member)session.getAttribute("loginMember")).getMemberId();
		
		// 파라미터 유효성 검사
		if(request.getParameter("cashbookNo") == null) {
			response.sendRedirect(request.getContextPath()+"/on/calendar");
			return;
		}
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		Cashbook cashbook = new Cashbook();
		cashbook.setCashbookNo(cashbookNo);
		cashbookService = new CashbookService();
		cashbook = cashbookService.getCashbookOne(cashbook);
		
		request.setAttribute("memberId", memberId);
		request.setAttribute("cashbook", cashbook);
		request.getRequestDispatcher("/WEB-INF/view/modifyCashbook.jsp").forward(request, response);

	}

	@Override // 캐시북 수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 유효성 검사(로그인 확인)
		HttpSession session = request.getSession();

		
		String memberId = ((Member)session.getAttribute("loginMember")).getMemberId();
		String category = request.getParameter("category");
		String cashbookDate = request.getParameter("cashbookDate");
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		
		Cashbook cashbook = new Cashbook();
		cashbook.setMemberId(memberId);
		cashbook.setCategory(category);
		cashbook.setCashbookDate(cashbookDate);
		cashbook.setPrice(price);
		cashbook.setMemo(memo);
		
		cashbookService = new CashbookService();
		int row = cashbookService.modifyCashbook(cashbook);
		// DB입력 실패
		if(row == -1) {
			System.out.println("입력 실패");
			response.sendRedirect(request.getContextPath() + "/addCashbook?cashbookDate="+cashbook.getCashbookDate());
			return;
		}
		
		int targetYear = Integer.parseInt(cashbook.getCashbookDate().substring(0, 4));
		int targetMonth = Integer.parseInt(cashbook.getCashbookDate().substring(5, 7)) - 1;
		int targetDate = Integer.parseInt(cashbook.getCashbookDate().substring(8, 10));
		response.sendRedirect(request.getContextPath()+"/on/cashbook?targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
	}

}
