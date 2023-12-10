$('document').ready(function() {
	$('.table #btn-Edit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(compte, status) {
			$('#clientEdit').val(compte.client.id);
			$('#ribEdit').val(compte.rib);
			$('#soldeEdit').val(compte.solde);
		});
		$('#editModal').modal();
	});
});