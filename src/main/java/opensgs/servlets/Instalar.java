package opensgs.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.MensajeTipo;
import opensgs.logica.instalar.InstalarSistemaProduccion;
import opensgs.logica.instalar.InstalarSistemaDesarrollo;
import opensgs.logica.instalar.InstalarSistemaPruebas;

@WebServlet("/Instalar")
public class Instalar extends HttpServlet {
	       
    public Instalar() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
        
        String tipo = request.getParameter("tipo");
        
        if (tipo.equals("produccion")) {
            InstalarSistemaProduccion instalarSistemaProduccion = new InstalarSistemaProduccion();
            try {
                DtMensaje dtMensaje = instalarSistemaProduccion.inicializarDatos();
                request.setAttribute("dtMensaje", dtMensaje);
            } catch (Exception ex) {
                Logger.getLogger(Instalar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (tipo.equals("pruebas")) {
            InstalarSistemaPruebas instalarSistemaPruebas = new InstalarSistemaPruebas();
            try {
                DtMensaje dtMensaje = instalarSistemaPruebas.inicializarDatos();
                request.setAttribute("dtMensaje", dtMensaje);
            } catch (Exception ex) {
                Logger.getLogger(Instalar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (tipo.equals("desarrollo")) {
            InstalarSistemaDesarrollo instalarSistemaDesarrollo = new InstalarSistemaDesarrollo();
            try {
                DtMensaje dtMensaje = instalarSistemaDesarrollo.inicializarDatos();
                request.setAttribute("dtMensaje", dtMensaje);
            } catch (Exception ex) {
                Logger.getLogger(Instalar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                DtMensaje dtMensaje = new DtMensaje(false, "Error tipo Instalacion", MensajeTipo.ERROR);
                request.setAttribute("dtMensaje", dtMensaje);
            } catch (Exception ex) {
                Logger.getLogger(Instalar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/instalar.jsp");
        rd.forward(request, response);		
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
