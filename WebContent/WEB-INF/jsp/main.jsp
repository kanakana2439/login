<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">

<title>【楽天】JAVA参考書のラインナップ</title>

</head>
<style>
img{
	max-width: 100%;
}
#gallery{
	overflow: hidden;
	box-sizing: border-box;
	padding-left: 1px;

}
.img_block{
	display: inline-block;
	box-sizing: border-box;
	padding: 2px;
	width: 33.333333%
}
.author_text{
	margin: 0 0 1em 0;
	padding: 0;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	font-size: 65%;
	color: #FA5858;
}
.title_text{
	margin: 0 0 1em 0;
	padding: 0;
	overflow: hidden;
	white-space: normal;
	width: 200px;
	height: 50px;
	overflow: visible;
	font-size: 100%;
	color: #000000;
}
.price_text{
	margin: 0 0 1em 0;
	padding: 0;
	overflow: hidden;
	white-space: normal;

	overflow: visible;
	font-size: 80%;
	color: #000000;
#pagination{
	margin: 40px 0 40px 0;


	text-align: center;
	
}
</style>
<body>
<header>
<h1>【楽天】JAVA参考書のラインナップ</h1>
<h2>次の30件を表示する場合、一番下の「次の30件を表示」をクリックしてください</h2>
</header>

<p>ようこそ<c:out value="${userId}" />さん</p>

<a href="/login/MainServlet?logout=y">ログアウト</a>
<section>
	<div id="gallery"></div>
	<div id="pagination"></div>
</section>

<footer>Test page</footer>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(document).ready(function(){
	var dataURL = 'https://app.rakuten.co.jp/services/api/BooksTotal/Search/20170404';
	var BookData;
	var pageData = 1;
	var CountData;


	
	var getData = function(url,pageData){
		$.ajax({
			url:url,
			dataType: 'jsonp',

			data:{
			 applicationId:"1077836788996273413",
			 keyword:"Java",
			 booksGenreId:"000",
			 hits:"30",
			 page:pageData
			}
		})
		.done(function(data){
			BookData = data;
			console.dir(BookData);
			CountData = data.count / 30;
			
			$(BookData.Items).each(function(){
			
				$('#gallery').append(
					$('<div class="img_block"></div>')
					.append(
						$('<a></a>')
							.attr('href',this.Item.itemUrl)
							.attr('target','_blank')
							.append(
								$('<img>').attr('src',this.Item.largeImageUrl)
							)
					)
					.append(
						$('<p class="title_text"></p>').text(this.Item.title)
					)
					.append(
						$('<p class="author_text"></p>').text(this.Item.author)
					) 
					.append(
						$('<p class="price_text"></p>').text(this.Item.itemPrice  + '円(税込)' )
					)
				)
			});
		})
		.fail(function(){
			$('#gallery').text(textStatus);
		})
		
	
	}
	getData(dataURL,pageData);
	
			
	

	if($('#pagination').children().length === 0){
		$('#pagination').append(
			$('<a class="next"></a>').attr('href','#').text('次の30件を表示').on('click',function(e){
				e.preventDefault();
				pageData += 1;
				getData(dataURL,pageData);
				
				if(pageData >  CountData){
					$('.next').remove();
				}
			})

		
		);	
		console.dir(pageData);
	}
	if(pageData <  CountData){
		$('.next').remove();
	}
	
});
</script>
</body>
</html>
