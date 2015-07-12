<%-- 
    Document   : register
    Created on : Jun 24, 2015, 8:08:38 PM
    Author     : ritchiefitzgerald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="modules/header.jsp" />

<div class="row">
    <main class="col-md-12 register-page">
        <div class="row">
            <div class="col-md-8 clear-bg center-block">
                <h2>Register</h2>
                <form class="form-horizontal" action="Registration" method="POST">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="fname">First Name:</label>
                        <div class="col-md-9">
                            <input type="text" name="fname" id="fname" class="form-control" placeholder="John">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="lname">Last Name:</label>
                        <div class="col-md-9">
                            <input type="text" name="lname" id="lname" class="form-control" placeholder="Doe">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="gender">Gender:</label>
                        <div class="col-md-9">
                            <select name="gender" id="gender">
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="phone_num">Phone #:</label>
                        <div class="col-md-9">
                            <!--pattern="(\d{3}) \d{3}-\d{4}"-->
                            <input type="text" name="phone_num"
                            id="phone_num" class="form-control" placeholder="(123) 456-7890">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="email">Email:</label>
                        <div class="col-md-9">
                            <input type="email" name="email" id="email" class="form-control" placeholder="john@doe.com">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="password">Password:</label>
                        <div class="col-md-9">
                            <input type="password" name="password" id="password" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="verify_pass">Verify Password:</label>
                        <div class="col-md-9">
                            <input type="password" name="verify_pass" id="verify_pass" class="form-control">
                        </div>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Register">
                </form>
            </div>
        </div>
    </main>
</div>

<jsp:include page="modules/footer.jsp" />
