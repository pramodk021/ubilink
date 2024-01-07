<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Home page</title>
</head>
<body>
<h1>Home page</h1>
<p>
Welcome to "UbiLink".<br/>
<i>${message}</i><br/>
<a href="${pageContext.request.contextPath}/requestPlace/create.html">Create a new Place</a><br/>
<a href="${pageContext.request.contextPath}/requestPlace/list.html">View all Malls</a><br/>
<a href="${pageContext.request.contextPath}/requestOffers/createOffer.html">Create a new Offer</a><br/>
<a href="${pageContext.request.contextPath}/requestOffers/list.html">View all Offers</a><br/>
<a href="${pageContext.request.contextPath}/test/createTest.html">Create a new Test</a><br/>
<a href="${pageContext.request.contextPath}/test/listTests.html">View all Tests</a><br/>
</p>
</body>
</html>