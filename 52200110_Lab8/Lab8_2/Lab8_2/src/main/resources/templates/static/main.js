$(document).ready(function(){
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();

	// Select/Deselect checkboxes
	var checkbox = $('table tbody input[type="checkbox"]');
	$("#selectAll").click(function(){
		if(this.checked){
			checkbox.each(function(){
				this.checked = true;
			});
		} else{
			checkbox.each(function(){
				this.checked = false;
			});
		}
	});
	checkbox.click(function(){
		if(!this.checked){
			$("#selectAll").prop("checked", false);
		}
	});


	// Attach a click event listener to all delete buttons
    $('.delete').on('click', function(){
            // Get the employee ID from the data-id attribute
            var employeeId = $(this).data('id');
            // Set the employee ID in the hidden input field
            $('#employeeId').val(employeeId);

            // Optionally, you can set the form action to include the employee ID
            $('#deleteForm').attr('action', '/employees/delete/' + employeeId);
        });

        // Handle the form submission
    $('#deleteForm').on('submit', function(e){
            e.preventDefault();
            var form = $(this);
            var employeeId = $('#employeeId').val();

            // Perform the AJAX request to delete the employee
            $.ajax({
                type: 'POST',
                url: 'employees/delete/'+employeeId,
                data: form.serialize(),
                success: function(response){
                    window.location.reload();
                },
                error: function(response){
                }
            });
        });
});

