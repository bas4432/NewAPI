<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>로그인 완료</title>
</head>
<body>
<form action="/user/loginProc" method="post">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" id="userType" name="userType" value="USER" />

    <span>email :</span><input type="text" name="username" required/>
    <span>pw :</span><input type="password" name="password" required/>


    <input type="submit" value="로그인"/>
</form>

</body>
</html>