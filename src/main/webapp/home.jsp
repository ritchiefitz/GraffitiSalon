<%-- 
    Document   : home
    Created on : Jun 24, 2015, 8:29:09 PM
    Author     : ritchiefitzgerald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
//allow access only if session exists
if(session.getAttribute("name") == null){
    response.sendRedirect("login.jsp");
}
%>

<jsp:include page="modules/header.jsp" />

<div class="row">
    <main class="col-md-12 user-home-page">
        <div class="row">
            <div class="col-md-7 clear-bg">
                <h3>Welcome, ${name}</h3>
                <h3>Appointments </h3>
                <div class="appointment-message">
                    ${yourAppointments}
                    <!-- TODO add message here ${appointmentMessage} -->
                    <!-- Added Default for now -->
                    ${appointmentMessage} ${appointmentError}
                </div>
                <h3>Create Appointment</h3>
                <form class="form-horizontal" action="Appointment" method="post">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="barber">Barber:</label>
                        <div class="col-md-9">
                            <!-- TODO? pull values from database, use attribute? -->
                            <select name="barber" id="barber">
                                <option value="Babara Goatee">Babara Goatee</option>
                                <option value="Fat Albert">Fat Albert</option>
                                <option value="Ruben Studdard">Ruben Studdard</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="day">Day:</label>
                        <div class="col-md-9">
                            <!-- TODO? pull only the current and following days  -->
                            <select name="day" id="day">
                                <option value="Monday">Monday</option>
                                <option value="Tuesday">Tuesday</option>
                                <option value="Wednesday">Wednesday</option>
                                <option value="Thursday">Thursday</option>
                                <option value="Friday">Friday</option>
                                <option value="Saturday">Saturday</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="time">Time:</label>
                        <div class="col-md-9">
                            <select name="time" id="time">
                                <option value="8:00 AM - 8:30 AM">8:00 AM - 8:30 AM</option>
                                <option value="8:30 AM - 9:00 AM">8:30 AM - 9:00 AM</option>
                                <option value="9:00 AM - 9:30 AM">9:00 AM - 9:30 AM</option>
                                <option value="9:30 AM - 10:00 AM">9:30 AM - 10:00 AM</option>
                                <option value="10:00 AM - 10:30 AM">10:00 AM - 10:30 AM</option>
                                <option value="10:30 AM - 11:00 AM">10:30 AM - 11:00 AM</option>
                                <option value="11:00 AM - 11:30 AM">11:00 AM - 11:30 AM</option>
                                <option value="11:30 AM - 12:00 PM">11:30 AM - 12:00 PM</option>
                                <option value="1:00 PM - 1:30 PM">1:00 PM - 1:30 PM</option>
                                <option value="1:30 PM - 2:00 PM">1:30 PM - 2:00 PM</option>
                                <option value="2:00 PM - 2:30 PM">2:00 PM - 2:30 PM</option>
                                <option value="2:30 PM - 3:00 PM">2:30 PM - 3:00 PM</option>
                                <option value="3:00 PM - 3:30 PM">3:00 PM - 3:30 PM</option>
                                <option value="3:30 PM - 4:00 PM">3:30 PM - 4:00 PM</option>
                                <option value="4:00 PM - 4:30 PM">4:00 PM - 4:30 PM</option>
                                <option value="4:30 PM - 5:00 PM">4:30 PM - 5:00 PM</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="type">Hair Cut Type:</label>
                        <div class="col-md-9">
                            <select name="type" id="type">
                                <option value="standard">Standard</option>
                                <option value="perm">Perm</option>
                                <option value="high">Highlights</option>
                                <option value="dye">Dye</option>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" name="action" value="appointment">
                    <input type="submit" class="btn btn-primary" value="Make Appointment">
                </form>
            </div>
            <div class="col-md-4 col-md-offset-1 clear-bg">
                <div class="barber-name"></div>
                <div class="schedule-day"></div>
                <hr>
                <div class="available-times"></div>
            </div>
        </div>
    </main>
</div>

<jsp:include page="modules/footer.jsp" />
