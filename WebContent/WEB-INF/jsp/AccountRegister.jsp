<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール登録</title>
</head>
<body>
<form action="/login/RegisterServlet" method="post">
ユーザID　：<input type="text"     name="USERID"  value=${Account.userId}><br>
パスワード：<input type="password" name="PASS"><br>
メール　　：<input type="text"     name="MAIL"    value=${Account.mail  } ><br>
年齢　　　：<input type="number"   name="AGE"     value=${Account.age   } ><br>
<input type="submit" value="登録">
<button type="button" onclick="history.back()">戻る</button>
</form>


<c:if test="${not empty errorMsg}">
<p><font color="red">${errorMsg }</font></p>
</c:if>
<a href="/login/">トップへ戻る</a>
</body>
</html>