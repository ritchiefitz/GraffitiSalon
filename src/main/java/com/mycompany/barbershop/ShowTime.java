/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.barbershop;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author paul
 */
@WebServlet(name = "ShowTime", urlPatterns = {"/ShowTime"})
public class ShowTime extends HttpServlet {

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
       
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = null;

        // Detect if we are on openshift or local environment.
        String db_host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        if (db_host != null) {
            String db_port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
            DB_URL = "jdbc:mysql://" + db_host + ":" + db_port + "/barbershop";
        } else {
            DB_URL = "jdbc:mysql://127.0.0.1/barbershop";
        }

        String USER = "admint7Jze9t";
        String PASS = "5kkJlvZVANR9";

        Connection conn = null;
        Statement stmt = null;

        try {
        
        //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
        
        //String barber_id = request.getParameter("barber");
        
        String barber_id = request.getParameter("id");
        
        List<Time> jsonList = new ArrayList<>();
        String sql = "SELECT * FROM appointment_table WHERE barber_id=" + barber_id;
        ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Time t = new Time();
                t.start_time = rs.getString("start_time");
                t.end_time = rs.getString("end_time");
                jsonList.add(t);
            }
            
            String json = new Gson().toJson(jsonList);
            
            response.getWriter().write(json);

    
    } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

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

