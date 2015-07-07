<%-- 
    Document   : login.jsp
    Created on : Jun 24, 2015, 7:53:21 PM
    Author     : ritchiefitzgerald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="modules/header.jsp" />

<div class="row">
    <main class="col-md-12 login-page">
        <div class="row">
            <div class="col-md-10 clear-bg">
                <h2>Login</h2>
                <div class="row">
                    <div class="col-md-8 text-left">
                        <p class="special-message">Already have an account?</p>
                        <form action="GraffitiController" method="post">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" name="email" id="email" class="form-control" placeholder="john@doe.com">
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="text" name="password" id="password" class="form-control">
                            </div>
                            <input type="hidden" name="action" value="login">
                            <input type="submit" class="btn btn-primary" value="Login">
                        </form>
                    </div>
                    <div class="col-md-4 text-center">
                        <p class="special-message">Need an account?</p>
                        <a href="/register.jsp" class="logo-font">Register</a>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>

<jsp:include page="modules/footer.jsp" />
