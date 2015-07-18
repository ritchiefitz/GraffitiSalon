/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    console.log("I worked!!");
    // if slideshow is there than add settings.
    if ($('.slideshow').length > 0) {
        slideshowSettings();
    }
    
    if ($('.user-home-page').length > 0) {
        $('.login-link').text('Logout');
        $('.login-link').attr('href', 'Logout');

        appointmentFormSettings();
        getAvailableTimes();
    }
});

function slideshowSettings() {
    $('.slideshow').cycle({
        fx: 'scrollHorz',
        prev: '#prev',
        next: '#next',
        easing: 'easeInOutBack',
        timeout: 0
    });
}

function appointmentFormSettings() {
	$('#barber').change(function() {
		getAvailableTimes();
	});

	$('#day').change(function() {
		getAvailableTimes();
	});
}

// TODO make sure that we add day of the week!
function getAvailableTimes() {
	var barbers = [];
    barbers['Babara Goatee'] = 1;
    barbers['Fat Albert'] = 2;
    barbers['Ruben Studdard'] = 3;

    var barberId = barbers[$('#barber').val()];
    var day = $('#day').val();
    console.log(barberId);

    // Add the day of the week to the data field!!
    $.ajax({
    	url: 'ShowTime',
    	method: 'POST',
    	data: {id: barberId, date: day}
    }).done(function (result) {
    	console.log("Information Retrived:\n" + result);
    	updatePage(result);
    });
}

function updatePage(result) {
	$('.barber-name').text($('#barber').val());
	$('.schedule-day').text($('#day').val() + '\'s Schedule');
	var scheduledTimes = JSON.parse(result);
	var availableTimes = [];
	console.log(scheduledTimes);

	var allTimes = [];
	allTimes['8:00 AM - 8:30 AM'] = '8:00 AM - 8:30 AM';
	allTimes['8:30 AM - 9:00 AM'] = '8:30 AM - 9:00 AM';
	allTimes['9:00 AM - 9:30 AM'] = '9:00 AM - 9:30 AM';
	allTimes['9:30 AM - 10:00 AM'] = '9:30 AM - 10:00 AM';
	allTimes['10:00 AM - 10:30 AM'] = '10:00 AM - 10:30 AM';
	allTimes['10:30 AM - 11:00 AM'] = '10:30 AM - 11:00 AM';
	allTimes['11:00 AM - 11:30 AM'] = '11:00 AM - 11:30 AM';
	allTimes['11:30 AM - 12:00 PM'] = '11:30 AM - 12:00 PM';
	allTimes['1:00 PM - 1:30 PM'] = '1:00 PM - 1:30 PM';
	allTimes['1:30 PM - 2:00 PM'] = '1:30 PM - 2:00 PM';
	allTimes['2:00 PM - 2:30 PM'] = '2:00 PM - 2:30 PM';
	allTimes['2:30 PM - 3:00 PM'] = '2:30 PM - 3:00 PM';
	allTimes['3:00 PM - 3:30 PM'] = '3:00 PM - 3:30 PM';
	allTimes['3:30 PM - 4:00 PM'] = '3:30 PM - 4:00 PM';
	allTimes['4:00 PM - 4:30 PM'] = '4:00 PM - 4:30 PM';
	allTimes['4:30 PM - 5:00 PM'] = '4:30 PM - 5:00 PM';

	var availableTimes = allTimes;

	var i;
	var time = '';
	for (i in scheduledTimes) {
		console.log(scheduledTimes[i]['start_time']);
		time = scheduledTimes[i]['start_time'];
		if (allTimes[time]) {
			delete availableTimes[time];
		}
	}

	var timesHtml = "";
	var addTime = "";
	for (addTime in availableTimes) {
		timesHtml += '<div class="available-time">' + addTime + '</div>';
	}

	$('.available-times').html(timesHtml);
}

    
