window.onload = function() {
			var reg = /^[a-zа-яА-ЯA-Z ,.'-]+$/;
			$("#changenamebtn").click(function(e) {
				$("#nameinfo").hide();
				$("#changename").show();
				e.preventDefault();
			});
			$("#cancelchange").click(function(e) {
				$("#nameinfo").show();
				$("#changename").hide();
				e.preventDefault();
			});
			$("#changesubmit").click(function(e) {
				var text = $('#name').val();
				var matchedSubString = text.match(reg);
				var validUsername = text == matchedSubString;

				if (!validUsername) {
					$('#name').css({
						'border-color' : 'red'
					});
					$('#name').tooltip({
						'trigger' : 'hover',
						'title' : 'Введите корректное имя'
					});
					e.preventDefault();

				}
			});

		};