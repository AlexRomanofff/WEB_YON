
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>YON</title>
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <style>
        <%@include file='indexStyleYons.css' %>
    </style>
</head>
<body>
<jsp:include page="_menu.jsp" />
<script type="text/javascript">
    var roomNumber;
    var currentState;

    function callDialog(obj) {

        document.getElementById("simNumber").value = obj.id;

        if (obj.name=="STANDBY") {
            roomNumber = prompt("Введите номер комнаты");
            document.getElementById("roomNumber").value = roomNumber;
            if (roomNumber=="") {
                alert("Вы не ввели номер комнаты")
            }
        }
        else if(obj.name=="ACTIVATED") {
            if(confirm("Вы уверены?")) {
                document.getElementById("roomNumber").value = "0";
            }
        }
    }
</script>

<form id = "newForm" action ="user", method = "post">

    <table>

        <c:forEach items="${yonslist}" var="current">

            <tr>
                <td title="${current.state}" href="#" class="${current.state}"><input id="${current.simNumber}" type="submit" name="${current.state}"  value="${current.simNumber}" onclick="callDialog(this);"/><td>
                <td><input type = "hidden" id="roomNumber" name = "roomNumber" value=""><td>
                <td><input type = "hidden" id="simNumber" name = "simNumber" value=""><td>
            </tr>

        </c:forEach>
    </table>
</form>

</body>
</html>
