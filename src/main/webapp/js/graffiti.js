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
    
