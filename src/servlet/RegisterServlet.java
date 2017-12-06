package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.AccountContentLogic;
import model.RegisterLogic;
import model.UserIdLogic;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID =1L;

	//アカウント登録画面にフォワード
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
		throws ServletException, IOException{

		//フォワード
		RequestDispatcher dispatcher =request.
				getRequestDispatcher("/WEB-INF/jsp/AccountRegister.jsp");
		dispatcher.forward(request, response);
	}




	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("USERID");
		String pass = request.getParameter("PASS");
		String mail = request.getParameter("MAIL");
		String ageStr  = request.getParameter("AGE");

		//ageStrがnullでなかった場合、int型に変換し、ageに格納
		int age = 0;
		if (ageStr != null) {
			try {
			age = Integer.parseInt(ageStr);
			} catch (NumberFormatException e) {
			// ageStrの中身がnullの場合、代入しない
			}
		}


		//Accountインスタンスを作成
		Account Account = new Account(userId,pass,mail,age);




		//AccountContentLogicを使ってパラメータに値に問題がないかチェックする
		//問題ない場合、戻り値="nothing"
		AccountContentLogic ACL = new AccountContentLogic();
		String errorMsg = ACL.check(Account);

		//UserIdLogicを使って、同じUserIdがすでに使われていないかチェック
		//AccountContentLogicでエラーが見つかった場合、実行しない
		if(errorMsg == "nothing"){
			UserIdLogic UIL = new UserIdLogic();
			errorMsg = UIL.find_List(userId);
		}

		java.lang.System.out.println(errorMsg);

		if(errorMsg == "nothing" /*Accountの各要素に問題がない(nothing)場合*/){



			//RegisterLogicを使ってデータベースへ登録
			RegisterLogic RegisterLogic = new RegisterLogic();
			RegisterLogic.execute(Account);

			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher(
					"/WEB-INF/jsp/registerOK.jsp");
			dispatcher.forward(request, response);


		}else{/*入力エラーが見つかった場合*/

			//errorMsgをリクエストスコープに保存
			//Accountのパラメータをリクエストスコープに保存
			//AccountRegisterへフォワード
			request.setAttribute("errorMsg" ,errorMsg);
			request.setAttribute("Account",Account );

			RequestDispatcher dispatcher = request.getRequestDispatcher(
					"/WEB-INF/jsp/AccountRegister.jsp");
			dispatcher.forward(request, response);
		}




	}
}