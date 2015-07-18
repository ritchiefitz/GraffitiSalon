/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.barbershop;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 */
@WebServlet(name = "Registration", urlPatterns = {"/Registration"})
public class Registration extends HttpServlet {

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
        //STEP 1: Create a connection
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

            //Getting information from form
            String firstName = (String) request.getParameter("fname");
            String lastName = (String) request.getParameter("lname");
            String gender = (String) request.getParameter("gender");
            //Setting boolean value for database (mysql equivilent is a tinyint
            int isMale = 0;
            if (gender.equals("male")) {
                isMale = 1;
            } else {
                isMale = 0;
            }
            String phone_num = (String) request.getParameter("phone_num");
            //TODO Check if email already exists
            String email = (String) request.getParameter("email");
            //TODO Hash the password
            String passwordToHash = (String) request.getParameter("password");

            String generatedPassword = null;
            try {
                // Create MessageDigest instance for MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                //Add password bytes to digest
                md.update(passwordToHash.getBytes());
                //Get the hash's bytes
                byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
                //Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                //Get complete hashed password in hex format
                generatedPassword = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            String sql = "Insert into user_table(firstName, lastName, isMale, phone, email, password) values ('" + firstName + "', '"
                    + lastName + "', " + isMale + ", '" + phone_num + "', '" + email
                    + "', '" + generatedPassword + "');";

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            request.setAttribute("user", firstName + " " + lastName);
            request.getRequestDispatcher("home.jsp").forward(request, response);

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
