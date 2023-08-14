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
 * Servlet implementation class RemoveCashbookController
 */
@WebServlet("/on/removeCashbook")
public class RemoveCashbookController extends HttpServlet {
	private CashbookService cashbookService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		String cashbookDate = request.getParameter("cashbookDate");
		
		Cashbook cashbook = new Cashbook();
		cashbook.setCashbookNo(cashbookNo);

		
		cashbookService = new CashbookService();
		int row = cashbookService.removeCashbook(cashbook);
		
		int targetYear = Integer.parseInt(cashbookDate.substring(0, 4));
		int targetMonth = Integer.parseInt(cashbookDate.substring(5, 7)) - 1;
		int targetDate = Integer.parseInt(cashbookDate.substring(8, 10));
		
		// DB삭제 실패
		if(row == -1) {
			System.out.println("삭제 실패");
			response.sendRedirect(request.getContextPath() + "/on/cashbook?targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
			return;
		}
		
		
		response.sendRedirect(request.getContextPath()+"/on/cashbook?targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
	}
}
