<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.LiQijun.model.Dog" %>
<%@ page import="com.LiQijun.model.Person" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/6/4
  Time: 8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>assignment1</title>
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>
    c:forEach loop to print 1 to 10:
    <u1>
        <c:forEach var="i" begin="1" end="10">
            <li>${i}</li>
        </c:forEach>
    </u1>

</h1>
<h1>
    c:forEach loop to print 2 4 6 8 10:
    <u1>
        <c:forEach var="i" begin="2" end="10" step="2">
            <li>${i}</li>
        </c:forEach>
    </u1>
</h1>
<%
    String[] words={"one","two","three","four","five","six","seven","eight","nine","ten"};
    request.setAttribute("word",words);
%>

<h1>
    c:forEach loop to print all elements of array:
    <u1>
        <c:forEach items="${word}" var="word" >
            <li><c:out value="${word}"/></li>
        </c:forEach>
    </u1>
</h1>
<h1>
    c:forEach loop to print one three five seven nine of array:
    <u1>
        <c:forEach items="${word}" var="word" step="2">
            <li><c:out value="${word}"/></li>
        </c:forEach>
    </u1>
</h1>

<h1>
    c:forEach loop to print person name:and person's dog name:
    <u1><%
        List<Person> personList=new ArrayList<>();
        personList.add(new Person("Tom",new Dog("Tommy")));
        personList.add(new Person("Sam",new Dog("Sammy")));
        request.setAttribute("AllPerson",personList);
    %>
        <c:forEach items="${AllPerson}" var="p" >
            <li><c:out value="${p.name}:${p.dog.name}"/></li>
        </c:forEach>
    </u1>
</h1>
<%
    String str="one:two:three-four-five";
%>
<h1>
    c:forTaken loop to print all of words:
    <u1>
        <c:forTokens items="<%=str%>"  var="w" delims=": -">
        <li>${w}</li>
        </c:forTokens>
    </u1>
</h1>

</body>
</html>
