/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.barbershop;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kendall
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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

        //SETTING UP CONNECTION TO DATABASE
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

//        String USER = "root";
//        String PASS = "";
        String USER = "admint7Jze9t";
        String PASS = "5kkJlvZVANR9";

        Connection conn = null;
        Statement stmt = null;
        System.out.println("0");
        try {
            //CONNECTING TO DB
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            //CHECKING DB FOR USER BY EMAIL
            String email = request.getParameter("email");
            String sql = null;
            boolean doIt = true;
 
            sql = "select * from user_table inner join appointment_table on user_table.user_id = appointment_table.user_id inner join barber_table ON appointment_table.barber_id = barber_table.barber_id where email ='" + email + "';";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            if (!rs.next()){
                System.out.println("New user");
                doIt = false;
                sql = "select * from user_table where email ='" + email + "';";  
                rs = stmt.executeQuery(sql);
            }else {
             System.out.println("Old user");   
             rs = stmt.executeQuery(sql);
            }
            
            while (rs.next()) {
                String passwordToHash = request.getParameter("password");
                System.out.println("IN HERE"); 
                String generatedPassword = null;
                try {
                    System.out.println("3");
                    // Create MessageDigest instance for MD5
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    System.out.println("4");
                    //Add password bytes to digest
                    md.update(passwordToHash.getBytes());
                    //Get the hash's bytes
                    byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
                    //Convert it to hexadecimal format
                    StringBuilder sb = new StringBuilder();
                    System.out.println("5");
                    for (int i = 0; i < bytes.length; i++) {
                        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    System.out.println("6");
                    //Get complete hashed password in hex format
                    generatedPassword = sb.toString();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                if (generatedPassword.equals(rs.getString("password"))) {
                    //Login them in and set user_id as session variable
                    String id = rs.getString("user_id");
                    String firstName = rs.getString("firstName");

                    HttpSession session = request.getSession();
                    session.setAttribute("name", firstName);
                    session.setAttribute("user_id", id);
                    //setting session to expiry in 30 mins
                    session.setMaxInactiveInterval(30 * 60);
                    
                    if(doIt){
                    request.setAttribute("yourAppointments", rs.getString("date") + " at " + rs.getString("start_time") + " with " + rs.getString("name"));              
                    }
                    
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                } else {
                    //Redirect them to the Login page with error messsage
                    System.out.println("Wrong password! Redirect them!");
                    request.setAttribute("loginError", "Incorrect email or password");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }

            }
            System.out.println("Wrong password! Redirect them!");
                    request.setAttribute("loginError", "Incorrect email or password");
                    request.getRequestDispatcher("login.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
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
