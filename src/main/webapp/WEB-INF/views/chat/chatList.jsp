<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Title</title>
</head>
<form action="/chat/createRoom" method="post">
    <input type="text" name="name" placeholder="채팅방 이름">
    <button type="submit" >방 만들기</button>
</form>

<table>
    <tr>
        <c:forEach items="${roomList}" var="article">
        <a href="/chatRoom?roomId=${room.roomId}">${room.name}</a>
        </c:forEach>
    </tr>
</table>

<body>

</body>
</html>
