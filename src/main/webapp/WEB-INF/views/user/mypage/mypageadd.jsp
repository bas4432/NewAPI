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
            <c:forEach var="keyword" items="${keywords}">
                <li>Title: ${keyword.keyword}</li>
                <li>Link: ${keyword.userId}</li>
            </c:forEach>
           </ul>
        </tbody>
    </table>

</div>


<form action="/mypage/add" method="post">

    <label for="keyword">키워드 </label>
    <input type="text" id="keyword" name="keyword"><br>
    <input type="submit" value="등록">
</form>
<a href="/mypage/info">나의페이지</a>

</body>
</html>