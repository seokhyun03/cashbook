package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.service.MemberService;
import cash.vo.*;

@WebServlet("/on/removeMember")
public class RemoveMemberController extends HttpServlet {
	private MemberService memberService;
	
	// 비밀번호 입력 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/view/removeMember.jsp").forward(request, response);
	}
	// 탈퇴
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberPw = request.getParameter("memberPw");
		
		Member member = new Member();
		member.setMemberId(((Member)session.getAttribute("loginMember")).getMemberId());
		member.setMemberPw(memberPw);
		
		int row = memberService.removeMember(member);
		
		if(row==0) {	// 회원탈퇴 실패시
			// removeMember.jsp view를 이동하는 controller를 리다이렉트
			response.sendRedirect(request.getContextPath() + "/removeMember");
		}else if(row==1) {	// 회원탈퇴 성공시
			// login.jsp view를 이동하는 controller를 리다이렉트
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/login");
		}else {
			System.out.println("remove member error!");
		}
	}

}
