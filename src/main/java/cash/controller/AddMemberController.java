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

@WebServlet("/addMember")
public class AddMemberController extends HttpServlet {
	private MemberService memberService;
	
	@Override // 회원가입 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 유효검사(null일 때)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		// addMember.jsp로 포워드
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
		
	}
	
	@Override // 회원가입 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 유효검사(null일 때)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		// request.getParameter()
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));

		int row = memberService.addMember(member);
		if(row==0) {	// 회원가입 실패시
			// addMember.jsp view를 이동하는 controller를 리다이렉트
			response.sendRedirect(request.getContextPath() + "/addMember");
		}else if(row==1) {	// 회원가입 성공시
			// login.jsp view를 이동하는 controller를 리다이렉트
			response.sendRedirect(request.getContextPath() + "/login");
		}else {
			System.out.println("add member error!");
		}
	}

}
