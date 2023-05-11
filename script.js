// Reference: https://stackoverflow.com/questions/65179019/how-to-change-page-content-or-division-content-on-button-click

let button = document.querySelectorAll('.menu button');
let content_inside = document.querySelectorAll('.content_inside');

Array.from(button).forEach(function(buttonArray, i) {
buttonArray.addEventListener('click', function() {

    Array.from(button).forEach(buttonAll => buttonAll.classList.remove('button_active'));
    
    Array.from(content_inside).forEach(content_insideAll => content_insideAll.classList.remove('content_inside_active'));
    
    button[i].classList.add('button_active'); 
    
    content_inside[i].classList.add('content_inside_active');  
  });
});