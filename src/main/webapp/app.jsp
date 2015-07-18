<%-- 
    Document   : app
    Created on : Jul 16, 2015, 2:46:01 PM
    Author     : ritchiefitzgerald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="modules/header.jsp" />
<div class="row">
    <main class="col-md-12 app-page">
        <div class="row">
            <div class="col-md-12 clear-bg center-block">
                <div class="slideshow-wrapper">
                    <div class="slideshow">
                        <div class="slides"><iframe width="740" height="421" src="https://www.youtube.com/embed/yDUIT84X1I8?rel=0" frameborder="0" allowfullscreen></iframe></div>
                        <div class="slides"><img src="images/home.jpg" alt=""></div>
                        <div class="slides"><img src="images/register.jpg" alt=""></div>
                        <div class="slides"><img src="images/login.jpg" alt=""></div>
                        <div class="slides"><img src="images/appointment.jpg" alt=""></div>
                    </div>
                    <div class="slidenav">
                        <a id="prev" href="#">Prev</a>
                        <a id="next" href="#">Next</a>
                    </div>
                </div>
                <h2>Barbershop</h2>
                <div class="app-description">
                    <h4>Description:</h4>
                    <p>
                        Clients like things to be easy and organized. Before the app 
                        barbershop, clients needed to call in and make an appointment,
                        write that appointment down, and if everything works out you will
                        see them at the time you agreed on.
                    </p>
                    <p>What does barbershop do for you and your clients? Making appointments and reminding them.</p>
                    <p>Here are the steps:</p>
                    <ul>
                        <li>Client logs in.</li>
                        <li>Client makes an appointment</li>
                    </ul>
                    <p>That is it!!</p>
                    <p>
                        Once a client makes an appointment they will receive a text message
                        for their appointment. Checkout the video to see our app in action.
                    </p>
                    <p>
                        <strong>Disclaimer:</strong> Our trial version of the api only sends texts to numbers saved on our api account.
                    </p>
                    <p>
                        Now you and your client no longer have to manually keep track. Just
                        let our app do it for you!
                    </p>
                </div>
                <div class="app-reviews">
                    <h4>Ratings</h4>
                    <div class="review">
                        <div class="total-rating">4.0</div>
                        <div class="star-rating"></div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
<jsp:include page="modules/footer.jsp" />
