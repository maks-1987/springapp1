<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty film.title}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty film.title}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<%--Нам нужно немного подправить страницу, чтобы она вела себя по-разному для добавления и
редактирования. Для решения этого вопроса воспользуемся условиями из все той же библиотеки JSTL core:--%>
<c:if test="${empty film.title}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty film.title}">
    <c:url value="/edit" var="var"/>
</c:if>

<%-- <form> — форма для сбора и отправки данных, с указанием кто их будет обрабатывать (/edit)--%>
<%-- <input> — элементы интерфейса для взаимодействия с пользователем (кнопки, поля ввода и т.д.)--%>
<%-- <label> — текстовая метка
Итак, при нажатии кнопки <input type="submit" value="Edit film"> данные из формы будут отправлены
на сервер (специально добавлено невидимое поле со значением id, чтобы сервер знал какую именно
запись в БД нужно обновить). В методе editFilm они будут присвоены соответствующим полям атрибута
film. Затем мы вернемся на главную страницу с обновленным списком.--%>
<form action="${var}" method="post">
    <c:if test="${!empty film.title}">
        <input type="hidden" name="id" value="${film.id}">
    </c:if>

    <label for="title">Title</label>
    <input type="text" name="title" id="title" value="${film.title}">

    <label for="year">Year</label>
    <input type="text" name="year" id="year" value="${film.year}">

    <label for="genre">Genre</label>
    <input type="text" name="genre" id="genre" value="${film.genre}">

    <label for="watched">Watched</label>
    <input type="text" name="watched" id="watched" value="${film.watched}">
<%-- Т.е. мы просто проверяем поле film.title. Если оно пустое, значит это новый фильм, мы
должны заполнить для него все данные и добавить в список. Если это поле не пустое, значит
это фильм из списка и его нужно просто изменить. Т.о. получаем два варианта нашей странички--%>
    <c:if test="${empty film.title}">
        <input type="submit" value="Add new film">
    </c:if>
    <c:if test="${!empty film.title}">
        <input type="submit" value="Edit film">
    </c:if>
</form>
</body>
</html>
