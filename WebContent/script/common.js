$(function() {
	$("#logout").click(function(){
		$.ajax({
				url: '/j2eehazi/rest/users/logout',
		    	type: "PUT",
		    	xhrFields: {
      					withCredentials: true
   				},
		    	success: function(json){
					window.location.replace('/j2eehazi');
		    	},
			});
	});

});