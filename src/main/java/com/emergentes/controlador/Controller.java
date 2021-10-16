package com.emergentes.controlador; 
import com.emergentes.modelo.gestor_tareas; 
import com.emergentes.modelo.tareas; 
import java.io.IOException; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 
@WebServlet(name = "Controller", urlPatterns = {"/Controller"}) 
public class Controller extends HttpServlet { 
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)             
            throws ServletException, IOException {         
        tareas objTarea = new tareas();         
        int id;         
        int pos; 
        String opcion = request.getParameter("op"); 
        String op = (opcion!=null)? request.getParameter("op"):"view";         
        if (op.equals("nuevo")) { 
            HttpSession sesion = request.getSession(); 
            gestor_tareas agenda = (gestor_tareas) sesion.getAttribute("agenda");             
            objTarea.setId(agenda.obtieneId());             
            request.setAttribute("op", op);             
            request.setAttribute("miTarea", objTarea); 
            request.getRequestDispatcher("Editar.jsp").forward(request, response); 
        } 
        if (op.equals("modificar")) { 
            id=Integer.parseInt(request.getParameter("id")); 
            HttpSession sesion = request.getSession(); 
            gestor_tareas agenda =(gestor_tareas) sesion.getAttribute("agenda");             
            pos=agenda.ubicarTarea(id);             
            objTarea=agenda.getLista().get(pos);             
            request.setAttribute("op", op);             
            request.setAttribute("miTarea", objTarea); 
            request.getRequestDispatcher("Editar.jsp").forward(request, response); 
        } 
        if (op.equals("eliminar")) { 
            id=Integer.parseInt(request.getParameter("id")); 
            HttpSession sesion = request.getSession(); 
            gestor_tareas agenda =(gestor_tareas) sesion.getAttribute("agenda");             
            pos=agenda.ubicarTarea(id);             
            agenda.eliminarTarea(pos);             
            sesion.setAttribute("agenda", agenda);             
            response.sendRedirect("index.jsp"); 
        } 
        if (op.equals("view")) { 
            response.sendRedirect("index.jsp"); 
        } 
    } 
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)             
            throws ServletException, IOException {         
        tareas objTarea = new  tareas();         
        int pos; 
        String op = request.getParameter("op"); 
        if (op.equals("grabar")) { 
            objTarea.setId(Integer.parseInt(request.getParameter("id")));             
            objTarea.setTarea(request.getParameter("tarea"));             
            objTarea.setPrioridad(request.getParameter("prioridad")); 
            HttpSession sesion = request.getSession(); 
            gestor_tareas agenda = (gestor_tareas)sesion.getAttribute("agenda");             
            String opg = request.getParameter("opg"); 
            if (opg.equals("nuevo")) { 
                agenda.Insertar_Tarea(objTarea); 
            }else{ 
                pos=agenda.ubicarTarea(objTarea.getId());                 
                agenda.modificarTarea(pos, objTarea); 
            } 
            sesion.setAttribute("agenda", agenda);             
            response.sendRedirect("index.jsp"); 
        }     
    } 
}
