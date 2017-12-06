package model;

import java.util.List;

import dao.AccountDAO;

public class UserIdLogic {
	public String find_List(String userId){

		//戻り値用の変数を宣言
		String FindUserId = "nothing";

		//データベースから「USER_ID」のリストを取り出す
		AccountDAO dao = new AccountDAO();
		List<String> UserIdList = dao.UserIdList();
		

		//リストの中身を確認して引数と同一のUserIdが無いか調べる
		for(int i = 0; i < UserIdList.size(); i++){
		
			if(userId.equals(UserIdList.get(i))){
				FindUserId = "そのユーザーIDはすでに使われています。";
		
			}
			
		}

		return FindUserId;

	}
}
