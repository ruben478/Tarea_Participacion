<%@page import="com.emergentes.modelo.gestor_tareas"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%if (session.getAttribute("agenda") == null) {
        gestor_tareas objeto1 = new gestor_tareas();
        session.setAttribute("agenda", objeto1);
    }
%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LISTA DE TAREAS</h1> 
        <a href="Controller?op=nuevo">NUEVO</a> 
        <table border="1"> 
            <tr> 
                <th>ID</th> 
                <th>TAREA</th> 
                <th>PRIORIDAD</th> 
                <th></th> 
                <th></th> 
            </tr> 
            <c:forEach var="item" items="${sessionScope.agenda.getLista()}"> 
                <tr> 
                    <td>${item.id}</td> 
                    <td>${item.tarea}</td> 
                    <td>${item.prioridad}</td> 
                    <td><a href="Controller?op=modificar&id=${item.id}">Modificar</a></td> 
                    <td><a href="Controller?op=eliminar&id=${item.id}">Eliminar</a></td> 
                </tr> 
            </c:forEach> 
        </table> 
    </body>
</html>
