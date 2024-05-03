<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="text" placeholder="보낼 메세지를 입력하세요." class="content">
<button type="button" value="전송" class="sendBtn" onclick="sendMsg()">전송</button>
<button type="button" value="방나가기" class="quit" onclick="quit()">방 나가기 </button>
<div>
    <span>메세지</span>
    <div class="msgArea"></div>
</div>

</body>
<script>
    let socket = new WebSocket("ws://localhost:8082/ws/chat");

    // WebSocket 이벤트 핸들러 설정
    socket.onopen = function (e) {
        console.log('서버에 연결되었습니다.');
        enterRoom(socket);
    };

    socket.onclose = function (e) {
        console.log('서버와 연결이 종료되었습니다.');
    };

    socket.onerror = function (e) {
        console.error('오류 발생: ', e);
    };

    socket.onmessage = function (e) {
        console.log('수신한 메시지: ', e.data);
        let msgArea = document.querySelector('.msgArea');
        let newMsg = document.createElement('div');
        newMsg.innerText = e.data;
        msgArea.appendChild(newMsg);
    };

    // 서버로 입장 메시지 전송
    function enterRoom(socket) {
        var enterMsg = {
            "type": "ENTER",
            "roomId":  ${room.roomId}, // JSP 표현식을 사용하여 방 번호를 가져옴
            "sender": "chee", // 수정 필요
            "msg": ""
        };
        socket.send(JSON.stringify(enterMsg));
    }

    // 메시지 전송
    function sendMsg() {
        let content = document.querySelector('.content').value;
        var talkMsg = {
            "type": "TALK",
            "roomId": ${room.roomId}, // JSP 표현식을 사용하여 방 번호를 가져옴
            "sender": "chee", // 수정 필요
            "msg": content
        };
        socket.send(JSON.stringify(talkMsg));
    }

    // 퇴장 메시지 전송 및 연결 종료
    function quit() {
        var quitMsg = {
            "type": "QUIT",
            "roomId": ${room.getRoomId()}, // JSP 표현식을 사용하여 방 번호를 가져옴
            "sender": "chee", // 수정 필요
            "msg": ""
        };
        socket.send(JSON.stringify(quitMsg));
        socket.close();
        location.href = "/chat/chatList";
    }

</script>
</html>
