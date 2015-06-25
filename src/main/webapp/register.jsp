<%-- 
    Document   : register
    Created on : Jun 24, 2015, 8:08:38 PM
    Author     : ritchiefitzgerald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="modules/header.jsp" />

<div class="row">
    <main class="col-md-12">
        <form class="form-horizontal" action="GraffitiController" method="post">
            <div class="form-group">
                <label class="col-sm-2 control-label" for="fname">First Name:</label>
                <div class="col-md-10">
                    <input type="text" name="fname" id="fname" class="form-control" placeholder="John">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="lname">Last Name:</label>
                <div class="col-md-10">
                    <input type="text" name="lname" id="lname" class="form-control" placeholder="john@doe.com">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="gender">Gender:</label>
                <div class="col-md-10">
                    <select name="gender" id="gender">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="phone_num">Phone #:</label>
                <div class="col-md-10">
                    <input type="text" name="phone_num" pattern="(\d{3}) \d{3}-\d{4}"
                    id="phone_num" class="form-control" placeholder="(123) 456-7890">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="email">Email:</label>
                <div class="col-md-10">
                    <input type="email" name="email" id="email" class="form-control" placeholder="john@doe.com">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="password">Password:</label>
                <div class="col-md-10">
                    <input type="email" name="password" id="password" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="verify_pass">Verify Password:</label>
                <div class="col-md-10">
                    <input type="email" name="verify_pass" id="verify_pass" class="form-control">
                </div>
            </div>
            <input type="hidden" name="action" value="register">
            <input type="submit" class="btn btn-primary" value="Register">
        </form>
    </main>
</div>

<jsp:include page="modules/footer.jsp" />
