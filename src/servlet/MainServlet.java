package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{

		//ログインしているか確認する。
		//セッションスコープからユーザー情報を取得
		HttpSession session =request.getSession();
		String userId = (String) session.getAttribute("userId");

		//ユーザー情報が取得できなかった場合、index.jspにリダイレクト
		if(userId == null){
			response.sendRedirect("/login/");
			return;
		}
		

		
		
		//ログアウトの動作
		//logoutパラメータの取り出し
		request.setCharacterEncoding("UTF-8");
		String logout = request.getParameter("logout");
		//logoutがnull以外の場合
		if(logout != null){
			//セッションスコープを破棄し、index.jspにリダイレクト
			session.invalidate();
			response.sendRedirect("/login/");
			return;
		}

		//main.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);



	}
}