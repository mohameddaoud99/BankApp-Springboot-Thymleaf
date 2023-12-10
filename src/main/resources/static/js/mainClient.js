$('document').ready(function() {
	$('.table #btn-Edit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(client, status) {
			$('#idEdit').val(client.id);
			$('#nomEdit').val(client.nom);
			$('#prenomEdit').val(client.prenom);
			$('#adresseEdit').val(client.adresse);
		});
		$('#editModal').modal();
	});
});