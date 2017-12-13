package model;

public class AccountContentLogic {
	public String check(Account Account){

		String userId = Account.getUserId();
		String pass   = Account.getPass();
		String mail   = Account.getMail();
		int    age    = Account.getAge();

		//戻り値用を変数を作成
		//HACK:戻り値を正常な時「nothing」エラーがあった場合、「エラーメッセージ」を代入しているが、
		//　　プログラムがキレイとは言えない、改善が必要
		String ReturnMassage = "nothing";


		//各エラー項目のチェックを行う

		if(userId == null || userId.length() == 0 /*UserID項目が入力されていない場合*/)
		{ReturnMassage = "ユーザーIDが入力されていません。";}

		else if(userId.length() > 11 /*ユーザーIDの文字数が11文字より大きい場合*/)
		{ReturnMassage = "ユーザーIDの文字数は11文字までです。";}

		else if(pass == null || pass.length() == 0 /*Pass項目が入力されていない場合*/)
		{ReturnMassage = "パスワードが入力されていません。";}

		else if(pass.length() > 11 /*パスワードの文字数が11文字より大きい場合*/)
		{ReturnMassage = "パスワードの文字数は11文字までです。";}

		else if(mail == null || mail.length() == 0 /*Mail項目が入力されていない場合*/)
		{ReturnMassage = "メールが入力されていません";}

		else if(mail.length() > 100 /*メールの文字数が100文字より大きい場合*/)
		{ReturnMassage = "メールの文字数が多すぎます。";}

		else if(mail.indexOf("@") == -1 /*メールが「@」を含んでいなかった場合*/)
		{ReturnMassage = "メールの構文が正しくありません";}

		else if(age == 0 /*Age項目が入力されていない場合*/)
		{ReturnMassage = "年齢が入力されていません。";}




		return ReturnMassage;
	}
}
