<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン完了</title>
</head>
<body>
<p>ようこそ<c:out value="${userId}" />さん</p>

3秒後、メインページに移動します。

<a href="/login/MainServlet">メイン画面へ</a>


<script>
var ToLink =function(){
	window.location.href = '/login/MainServlet';
}
setTimeout(ToLink,3000);
</script>
</body>
</html>