package model;

import dao.AccountDAO;
//データベース登録に戻り値不要と考え、voidを選択
public class RegisterLogic {
	public void execute(Account Account){

		AccountDAO dao = new AccountDAO();
		dao.registerUser(Account);
	}
}
