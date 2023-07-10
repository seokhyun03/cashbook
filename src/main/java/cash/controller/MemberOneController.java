package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.dao.*;
import cash.service.MemberService;
import cash.vo.*;

@WebServlet("/on/memberOne")
public class MemberOneController extends HttpServlet {
	private MemberService memberService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		// 모델 값 구하기(dao 메서드 호출)

		Member member = memberService.getMemberOne(loginMember.getMemberId());
		// member 출력하는(포워딩 대상) memberOne.jsp에도 공유되어야한다
		// request가 공유되니까 request안에 넣어서 공유
		request.setAttribute("member", member);
					
		// memberOne.jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/view/memberOne.jsp").forward(request, response);
	}
}
