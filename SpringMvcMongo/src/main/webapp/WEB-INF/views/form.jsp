<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <title>Form inserimento libro</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
	    <div class="container" style="margin-top:5%;">
	        <h3 id="form_header" class="text-warning" align="center">Inserisci libro</h3>
	        <div>&nbsp;</div>
	
			<!-- User input form to add a new user or update the existing user-->
	        <c:url var="saveUrl" value="/book/save" />
	        <form:form id="user_form" modelAttribute="userAttr" method="POST" action="${saveUrl}">
	        	<form:hidden path="id" />
	            <label for="user_name">Titolo: </label>
	            <form:input id="user_name" cssClass="form-control" path="title" required="true"/>
	            <label for="author">Autore: </label>
	            <form:input id="author" cssClass="form-control" path="author" required="true"/>
	             <label for="editor">Editore: </label>
	            <form:input id="editor" cssClass="form-control" path="editor" required="true"/>
	             <label for="isbn">ISBN: </label>
	            <form:input id="isbn" cssClass="form-control" path="isbn" required="true"/>
	            <div>&nbsp;</div>

	            <button id="saveBtn" type="submit" class="btn btn-primary">Salva</button>
	        </form:form>
	    </div>
	</body>
</html>