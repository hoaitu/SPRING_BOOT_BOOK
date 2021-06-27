//auto complete search
$(document).ready(
		function() {
			$("#autoSearch").keyup(
					function() {
						var value = $(this).val();
						var result = "";
						console.log(value);
						$.ajax({
							type : "GET",
							url : "ajaxSearch",
							data : {
								'tenSach' : value
							},
							dataType : "json",
							success : function(msg) {
								for (var i = 0; i < msg.length; i++) {
									result += msg[i] + "/n";
								}
								console.log(result);
								/* $("#search").html(result); */

								$("#search").html(
										'<li class="list-group-item link-class">'
												+ result + '</li><br/>');
							}
						});
					});
		});