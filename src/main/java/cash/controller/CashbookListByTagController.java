package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.dao.*;
import cash.service.CashbookService;
import cash.vo.*;

import java.util.*;

@WebServlet("/on/cashbookByTag")
public class CashbookListByTagController extends HttpServlet {
	private CashbookService cashbookService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		String word = request.getParameter("word");
		
		cashbookService = new CashbookService();
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int totalRow = cashbookService.getCashbookListByHashtagCnt(member.getMemberId(), targetYear, targetMonth, word);
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
		
		List<Cashbook> list = cashbookService.getCashbookListByHashtag(member.getMemberId(), targetYear, targetMonth+1, word, beginRow, rowPerPage);
		
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("word", word);
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pagePerPage", pagePerPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("endPage", endPage);
		
		request.getRequestDispatcher("/WEB-INF/view/cashbookListByTag.jsp").forward(request, response);
	}
}
