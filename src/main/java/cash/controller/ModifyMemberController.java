package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.service.MemberService;
import cash.vo.Member;

@WebServlet("/on/modifyMember")
public class ModifyMemberController extends HttpServlet {
	private MemberService memberService;
	
	// 비밀번호 변경 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		request.setAttribute("member", member);
		request.getRequestDispatcher("WEB-INF/view/modifyMember.jsp").forward(request, response);
	}
	// 변경 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberPw = request.getParameter("memberPw");
		String memberNewPw = request.getParameter("memberNewPw");
		String memberNewPwCk = request.getParameter("memberNewPwCk");
		
		Member member = new Member();
		member.setMemberId(((Member)session.getAttribute("loginMember")).getMemberId());
		member.setMemberPw(memberPw);
		
		int row = 0;
		if(memberNewPw.equals(memberNewPwCk)) {
			row = memberService.modifyMember(member, memberNewPw);
		}
		
		if(row==0) {	// 비밀번호 변경 실패시
			// modifyMember.jsp view를 이동하는 controller를 리다이렉트
			response.sendRedirect(request.getContextPath() + "/on/modifyMember");
		}else if(row==1) {	// 비밀번호 변경 성공시
			// login.jsp view를 이동하는 controller를 리다이렉트
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/off/login");
		}else {
			System.out.println("modify member error!");
		}
	}

}
