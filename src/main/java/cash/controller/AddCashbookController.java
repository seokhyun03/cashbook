package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import cash.model.*;
import cash.vo.*;


@WebServlet("/addCashbook")
public class AddCashbookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 유효성 검사(로그인 확인)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		String memberId = ((Member)session.getAttribute("loginMember")).getMemberId();
		// 파라미터 유효성 검사
		if(request.getParameter("cashbookDate") == null) {
			response.sendRedirect(request.getContextPath()+"/calendar");
			return;
		}
		String cashbookDate = request.getParameter("cashbookDate");
		
		request.setAttribute("memberId", memberId);
		request.setAttribute("cashbookDate", cashbookDate);
		request.getRequestDispatcher("/WEB-INF/view/addCashbook.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 유효성 검사(로그인 확인)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		
		String memberId = ((Member)session.getAttribute("loginMember")).getMemberId();
		String category = request.getParameter("category");
		String cashbookDate = request.getParameter("cashbookDate");
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		// 메모에 해시태그 중복 및 내용없는 해시태그 제거
		String rememo = memo.replace("#", " #");
		memo = "";
		List<String> words = new ArrayList<>();
		for(String w : rememo.split(" ")) {
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
						memo +="#" + word + " ";
						words.add(word);
					}
				}
			} else {
				memo += w + " ";
			}
		}
		
		Cashbook cashbook = new Cashbook();
		cashbook.setMemberId(memberId);
		cashbook.setCategory(category);
		cashbook.setCashbookDate(cashbookDate);
		cashbook.setPrice(price);
		cashbook.setMemo(memo);
		CashbookDao cashbookDao = new CashbookDao();
		int cashbookNo = cashbookDao.insertCashbook(cashbook);
		
		if(cashbookNo == 0) {
			System.out.println("입력 실패");
			response.sendRedirect(request.getContextPath() + "/addCashbook?cashbookDate="+cashbook.getCashbookDate());
			return;
		}
		
		HashtagDao hashtagDao = new HashtagDao();
		// 입력 성공 시 해시태그 존재 여부 확인
		// 해시태그 있을 경우 추출하여 데이터베이스에 입력
		for(String w : memo.split(" ")) {
			if(w.startsWith("#")) {
				String word = w.replace("#", "");
				if(word.length() > 0) {
					Hashtag hashtag = new Hashtag();
					hashtag.setCashbookNo(cashbookNo);
					hashtag.setWord(word);
					hashtagDao.insertHashtag(hashtag);
				}
			}
		}
		
		
		int targetYear = Integer.parseInt(cashbook.getCashbookDate().substring(0, 4));
		int targetMonth = Integer.parseInt(cashbook.getCashbookDate().substring(5, 7)) - 1;
		int targetDate = Integer.parseInt(cashbook.getCashbookDate().substring(8, 10));
		response.sendRedirect(request.getContextPath()+"/cashbook?targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
	}

}
