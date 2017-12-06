package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Login;



public class AccountDAO{

	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL =
			"jdbc:h2:file:/usr/lib/h2/Login";
			//検証環境のパス
			//"jdbc:h2:file:C:/data/Login";
	private final String DB_USER ="sa";
	private final String DB_PASS = "";


	//登録メソッド
	public boolean registerUser(Account Account){
		Connection conn = null;
		try{
			//JDBCドライバを読み込む
			//Class.forNameでエラーが出るため、Catchに「ClassNotFoundException」を追加
			Class.forName(DRIVER_NAME);

			//データベースに接続
			conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);

			//INSERT文を準備(IDは自動連番なので指定しなくてよい)
			String sql = "INSERT INTO ACCOUNT(USER_ID,PASS,MAIL,AGE) VALUES(?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, Account.getUserId());
			pStmt.setString(2, Account.getPass());
			pStmt.setString(3, Account.getMail());
			pStmt.setInt   (4, Account.getAge());

			//INSERT文を実行
			int result = pStmt.executeUpdate();

			if(result != 1){
				return false;
			}

		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
			return false;

		}finally{


			//データベース切断
			if(conn != null){
				try{
					conn.close();

				}catch(SQLException e){
					e.printStackTrace();
				}
			}

		}
		return true;
	}

	//USERID参照メソッド
	public List<String> UserIdList(){
		Connection conn = null;

		//USERIDを入れるArrayListを作成
		List<String> userIdList = new ArrayList<String>();

		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);

			//SELECT文の準備
			String sql = "SELECT USER_ID FROM ACCOUNT";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT文を実行
			ResultSet rs = pStmt.executeQuery();

			//SELECT文で抽出した「USER_ID」をArrayListに格納
			while(rs.next()){
				userIdList.add(rs.getString("USER_ID"));
			}


		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}finally{
			//データベース切断
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}

		return userIdList;
	}









	//ログインメソッド
	public Account findByLogin(Login login){
		Connection conn = null;
		Account account = null;
		try{
			//JDBCドライバを読み込む
			Class.forName(DRIVER_NAME);

			//データベースに接続
			conn = DriverManager.getConnection(
					JDBC_URL,DB_USER,DB_PASS);

			//SELECT文を準備
			String sql = "SELECT USER_ID, PASS, MAIL, AGE FROM ACCOUNT WHERE USER_ID =? AND PASS = ? ";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,login.getUserId());
			pStmt.setString(2,login.getPass());

			//SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			//一致したユーザーが存在した場合
			//そのユーザーを表すAccountインスタンスを生成
			if(rs.next()){
				//結果表からデータを取得
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				int age = rs.getInt("AGE");

				account = new Account(userId,pass, mail,age);

			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}

			}
		}
		//見つかったユーザーまたはnullを返す
		return account;
	}









}