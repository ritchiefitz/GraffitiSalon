/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.barbershop;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
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
import javax.servlet.http.HttpSession;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Kendall
 */
@WebServlet(name = "Appointment", urlPatterns = {"/Appointment"})
public class Appointment extends HttpServlet {

    public static final String ACCOUNT_SID = "AC3d9ec5782c8ef8c5bce0ff65a128d745";
    public static final String AUTH_TOKEN = "27d5eb0f01ac51826b4a505205d859dc";

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

            //Query to get barber id
            int barber_id = 0;
            String barber_name = (String) request.getParameter("barber");
            String sql = "SELECT * FROM barber_table WHERE name = '" + barber_name + "';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                barber_id = rs.getInt("barber_id");
            }

            String day = (String) request.getParameter("day");
            String time = (String) request.getParameter("time");
            String type = (String) request.getParameter("type");
            String user_id = (String) request.getSession().getAttribute("user_id");

            //Check to see if an appointent is already made for that barber at that time
            sql = "select * from appointment_table where barber_id ='" + barber_id + "' and start_time ='" + time + "' and date ='" + day + "';";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                request.setAttribute("appointmentError", "Scheduling conflict. Please schedule another time");
                request.getRequestDispatcher("/home.jsp").forward(request, response);

            } else {
                sql = "INSERT INTO appointment_table(barber_id, user_id, start_time, date)"
                        + "VALUES (" + barber_id + ", " + user_id + ", '" + time + "', '" + day + "')";
            }

            stmt.executeUpdate(sql);

            HttpSession session = request.getSession(false);
            String name = (String) session.getAttribute("name");
            request.setAttribute("appointmentMessage", "Appointment successfully created for " + name + " with " + barber_name + " for a " + type + " at " + time + " on " + day);

            //TEXTING API
//            String firstName = (String) request.getSession().getAttribute("name");
//           request.setAttribute("appointmentMessage", "Appointment successfully created for " + firstName + " with " + barber_name + " for a " + type + " at " + time + " on " + day);
//           
//           
//           String phonenum = "";
//           sql = "SELECT * FROM user_table WHERE firstName='" + firstName + "';";
//           rs = stmt.executeQuery(sql);
//           while (rs.next()) {
//               phonenum = rs.getString("phone");
//           }
//           
//           TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
//
//           // Build a filter for the MessageList
//           List<NameValuePair> params = new ArrayList<>();
//           params.add(new BasicNameValuePair("Body", "Thank you for choosing Graffitti! "
//                   + "Your next appointment will be on " + day + " at " + time + "."));
//           params.add(new BasicNameValuePair("To", "+1" + phonenum));
//           params.add(new BasicNameValuePair("From", "+19784345321"));
//
//           MessageFactory messageFactory = client.getAccount().getMessageFactory();
//           Message message = messageFactory.create(params);
            request.getRequestDispatcher("/home.jsp").forward(request, response);

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
