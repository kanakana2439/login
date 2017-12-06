<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<form action="/login/LoginServlet" method="post">
ユーザーID：<input type="text" name="USERID"  value=${userId} ><br>
パスワード：<input type="password" name="PASS"><br>


<input type="submit" value="ログイン">
<button type="button" onclick="history.back()">戻る</button>
</form>

<c:if test="${not empty errorMsg}">
<p><font color="red">${errorMsg }</font></p>
</c:if>
<a href="/login/">トップへ戻る</a>
</body>
</html>