<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>F i l m s</title>
</head>
<body>
<h2>Films</h2>
<table> <%-- <tr> — строка таблицы, <th> — заголовок столбца, <td> — ячейка таблицы --%>
    <tr>
        <th>id</th>
        <th>title</th>
        <th>year</th>
        <th>genre</th>
        <th>watched</th>
        <th>action</th>
    </tr>
    <c:forEach var="film" items="${filmList}">
        <tr> <%-- запись вроде film.id нужно понимать как film.getId(),т.е. не напрямую к полю обращение, а именно вызывается геттер --%>
            <td>${film.id}</td>
            <td>${film.title}</td>
            <td>${film.year}</td>
            <td>${film.genre}</td>
            <td>${film.watched}</td>
            <td>
                <a href="/edit/${film.id}">edit</a>
                <a href="/delete/${film.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/add" var="/add"/>
<a href="${add}">Add new film</a>

<%-- @RequestMapping(value = "/edit", method = RequestMethod.GET) значение value в аннотации должно совпадать с href в сервлете --%>
<%-- <h1><a href="/edit">edit page</a></h1>--%>
</body>
</html>
