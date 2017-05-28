$(function() {
 $("#sign-up-form").submit(checkData); 
}); 

function checkData(event) {
 if ($("#password1").val() != $("#repeat").val()) {
	 	event.preventDefault();
 		$("#must-match").css("display", "block");
 		$('#repeat').each(function() {
 		    this.setCustomValidity("Passwords must match!")
 		}); 		
 }
}


$('.form').find('input, textarea').on('keyup blur focus', function (e) {
  
  var $this = $(this),
      label = $this.prev('label');

	  if (e.type === 'keyup') {
			if ($this.val() === '') {
          label.removeClass('active highlight');
        } else {
          label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
    	if( $this.val() === '' ) {
    		label.removeClass('active highlight'); 
			} else {
		    label.removeClass('highlight');   
			}
    } else if (e.type === 'focus') {
      if( $this.val() === '' ) {
    		label.removeClass('highlight'); 
			}
      else if( $this.val() !== '' ) {
		    label.addClass('highlight');
			}
    }
});

$('.form').find('select').on('click focusout change', function(e) {
  var $this = $(this),
      label = $this.prev('label');
      if(e.type === 'click') {
        if( $this.val() === '' )
          label.removeClass('active highlight'); 
        else if( $this.val() !== null )
          label.addClass('active highlight');
      } else if(e.type === 'focusout') {
        if( $this.val() === '' )
          label.removeClass('active highlight'); 
        else if( $this.val() !== '')
          label.removeClass('highlight');
      } else if(e.type === 'change') {
    	  if( $this.val() === '' )
              label.removeClass('active highlight'); 
            else if( $this.val() !== null )
              label.addClass('active highlight');
      }
});

$('.tab a').on('click', function (e) {
  
  e.preventDefault();
  
  $(this).parent().addClass('active');
  $(this).parent().siblings().removeClass('active');
  
  target = $(this).attr('href');

  $('.tab-content > div').not(target).hide();
  
  $(target).fadeIn(600);
  
});