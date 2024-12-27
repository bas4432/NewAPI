<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <table>
        <tbody>
           <ul>
            <c:forEach var="UserKeywordNews" items="${UserKeywordNews}">
                <li>Link: ${UserKeywordNews.userId}</li>
                <li>Title: ${UserKeywordNews.keyWord}</li>
                <li>Title: ${UserKeywordNews.newsSite}</li>
                <br>
            </c:forEach>
           </ul>
        </tbody>
    </table>
</div>

<form action="/mypage/keyword/add" method="post">

    <label for="keyword">키워드 </label>
    <select name="keyword" id="keyword">
       <option value="선거">선거</option>
       <option value="코로나">코로나</option>
       <option value="퇴사">퇴사</option>
    </select>

    <label for="newsSites">뉴스 사이트</label>
    <select name="newsite" id="newsSites">
        <option value="naver">네이버</option>
        <option value="daum">다음</option>
        <option value="google">구글</option>
    </select>
    <input type="submit" value="등록">
</form>
<a href="/mypage/info">나의페이지</a>

</body>
</html>