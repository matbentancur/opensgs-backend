<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="opensgs.datatypes.DtMensaje"%>
<%@page import="opensgs.logica.servicios.ServicioMensaje"%>

<% DtMensaje dtMensaje = (DtMensaje) request.getAttribute("dtMensaje"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>OpenSGS</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>OpenSGS - Aplicación</h1>
        <% if (dtMensaje != null){ %>
            <% List<String> mensajes = (List<String>) dtMensaje.getMensajes();%>
            <% for (String mensaje : mensajes) { %>
                <h2><%= mensaje %></h2>
            <% } %>
        <% } else { %>
        <h2><%= ServicioMensaje.getInstance().getMensaje("OpenSGS.initialize.error") %></h2>
        <% } %>
    </body>
</html>
