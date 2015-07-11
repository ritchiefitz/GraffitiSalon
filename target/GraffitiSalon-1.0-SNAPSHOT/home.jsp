<%-- 
    Document   : home
    Created on : Jun 24, 2015, 8:29:09 PM
    Author     : ritchiefitzgerald
--%>

<%@page import="org.joda.time.DateTime"%>
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
            <div class="col-md-8 clear-bg center-block">
                <h3>Welcome, ${name}</h3>
                <h3>Appointments </h3>
                <div class="appointment-message">
                    <!-- TODO add message here ${appointmentMessage} -->
                    <!-- Added Default for now -->
                    ${appointmentMessage}
                </div>
                <h3>Create Appointment</h3>
                <form class="form-horizontal" action="Appointment" method="post">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="barber">Barber:</label>
                        <div class="col-md-9">
                            <!-- TODO? pull values from database, use attribute? -->
                            <select name="barber" id="barber">
                                <option value="Fat Albert">Fat Albert</option>
                                <option value="paul">Paul</option>
                                <option value="john">John</option>
                                <option value="adam">Adam</option>
                                <option value="joe">Joe</option>
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
                                <option value="8:00am">8:00 AM</option>
                                <option value="8:15am">8:15 AM</option>
                                <option value="8:30am">8:30 AM</option>
                                <option value="8:45am">8:45 AM</option>
                                <option value="9:00am">9:00 AM</option>
                                <option value="9:15am">9:15 AM</option>
                                <option value="9:30am">9:30 AM</option>
                                <option value="9:45am">9:45 AM</option>
                                <option value="10:00am">10:00 AM</option>
                                <option value="10:15am">10:15 AM</option>
                                <option value="10:30am">10:30 AM</option>
                                <option value="10:45am">10:45 AM</option>
                                <option value="11:00am">11:00 AM</option>
                                <option value="11:15am">11:15 AM</option>
                                <option value="11:30am">11:30 AM</option>
                                <option value="11:45am">11:45 AM</option>
                                <option value="12:00pm">12:00 PM</option>
                                <option value="1:00am">1:00 PM</option>
                                <option value="1:15pm">1:15 PM</option>
                                <option value="1:30pm">1:30 PM</option>
                                <option value="1:45pm">1:45 PM</option>
                                <option value="2:00pm">2:00 PM</option>
                                <option value="2:15pm">2:15 PM</option>
                                <option value="2:30pm">2:30 PM</option>
                                <option value="2:45pm">2:45 PM</option>
                                <option value="3:00pm">3:00 PM</option>
                                <option value="3:15pm">3:15 PM</option>
                                <option value="3:30pm">3:30 PM</option>
                                <option value="3:45pm">3:45 PM</option>
                                <option value="4:00pm">4:00 PM</option>
                                <option value="4:15pm">4:15 PM</option>
                                <option value="4:30pm">4:30 PM</option>
                                <option value="4:45pm">4:45 PM</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="type">Hair Cut Type:</label>
                        <div class="col-md-9">
                            <select name="type" id="type">
                                <option value="15::standard">Standard (15 min)</option>
                                <option value="45::perm">Perm (45 min)</option>
                                <option value="30::high">Highlights (30 min)</option>
                                <option value="60::dye">Dye (1 hour)</option>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" name="action" value="appointment">
                    <input type="submit" class="btn btn-primary" value="Make Appointment">
                </form>
            </div>
        </div>
    </main>
</div>

<jsp:include page="modules/footer.jsp" />
