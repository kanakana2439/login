package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Login;
import model.LoginLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		RequestDispatcher dispatcher =request.getRequestDispatcher(
				"/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("USERID");
		String pass = request.getParameter("PASS");
		
		boolean result = true;
		
		//userIdとpassが25文字を超えていないか確認
		if(userId.length() > 26  ||pass.length() > 26 )
		{result = false;}
		
		//入力文字が25文字以下の場合、ログイン処理の実行
		if(result){
			Login login =new Login(userId,pass);
			LoginLogic bo =new LoginLogic();
			result = bo.execute(login);
		}

		//ログイン処理の成否によって処理の分岐
		if(result){//ログイン成功時

			//セッションスコープにユーザーIDを保存
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);

			//フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/loginOK.jsp");
			dispatcher.forward(request, response);

		}else{//ログイン失敗時
			//ユーザーIDとエラーメッセージをリクエストスコープに保存
			request.setAttribute("userId", userId);
			request.setAttribute("errorMsg","ユーザーIDまたはパスワードが違います。");
			//フォワード
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("//WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			
		}


	}

}