/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.*;
import modeloDAO.*;

/**
 *
 * @author MAMG.JR
 */
public class Controlador extends HttpServlet {

    Empleado emp = new Empleado();
    EmpleadoDAO empDAO = new EmpleadoDAO();
    Factura fac = new Factura();
    FacturaDAO facDAO = new FacturaDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Principal")) {

            request.getRequestDispatcher("Principal.jsp").forward(request, response);

        } else if (menu.equals("Empleado")) {

            switch (accion) {

                case "Listar":

                    List listaEmpleados = empDAO.listar();
                    request.setAttribute("empleados", listaEmpleados);
                    break;

                case "Agregar":

                    int DPIEmpleado = Integer.parseInt(request.getParameter("txtDPIEmpleado"));
                    String nombresEmpleado = request.getParameter("txtNombresEmpleado");
                    String telefonoEmpleado = request.getParameter("txtTelefonoEmpleado");
                    String emailEmpleado = request.getParameter("txtEmailEmpleado");
                    String contrasenaLogin = request.getParameter("txtContrasenaLogin");

                    emp.setDPIEmpleado(DPIEmpleado);
                    emp.setNombresEmpleado(nombresEmpleado);
                    emp.setTelefonoEmpleado(telefonoEmpleado);
                    emp.setEmailEmpleado(emailEmpleado);
                    emp.setContrasenaEmpleado(nombresEmpleado);

                    empDAO.agregar(emp);

                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);

                    break;
            }

            request.getRequestDispatcher("Empleado.jsp").forward(request, response);

        } else if (menu.equals("Home")) {

            request.getRequestDispatcher("PrincipalCarousel.jsp").forward(request, response);

        } else if (menu.equals("Factura")) {
            switch (accion) {
                case "Listar":
                    List listaFacturas = facDAO.listar();
                    request.setAttribute("facturas", listaFacturas);
                    break;
                case "Agregar":
                    try {
                    String fechaFac = request.getParameter("dtFechaFactura");
                    int nit = Integer.parseInt(request.getParameter("txtNIT"));
                    int DPIEmpleado = Integer.parseInt(request.getParameter("txtDPIEmpleado"));
                    int codigoTipoPago = Integer.parseInt(request.getParameter("txtCodigoTipoPago"));
                    int codigoSucursal = Integer.parseInt(request.getParameter("txtCodigoSucursal"));
                    fac.setFechaFactura(new SimpleDateFormat("yyyy-MM-dd").parse(fechaFac));
                    fac.setNIT(nit);
                    fac.setDPIEmpleado(DPIEmpleado);
                    fac.setCodigoTipoPago(codigoTipoPago);
                    fac.setCodigoSucursal(codigoSucursal);
                    facDAO.agregar(fac);
                    request.getRequestDispatcher("Controlador?menu=Factura&accion=Listar").forward(request, response);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }

            request.getRequestDispatcher("Factura.jsp").forward(request, response);
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
