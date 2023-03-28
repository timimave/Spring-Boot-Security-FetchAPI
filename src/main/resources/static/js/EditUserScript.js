//
// $.ajax({
//   type: "PUT",
//   url: `/api/admin/${id}/editUser`,
//   data: JSON.stringify(user),
//   contentType: "application/json",
//   success: function() {
//     alert("User updated successfully!");
//   },
//   error: function(jqXHR, textStatus, errorThrown) {
//     console.log(errorThrown);
//     alert("Error updating user!");
//   }
// });

// const editUserModal = document.querySelector('#editUserModal');
// const usernameInput = editUserModal.querySelector('#usernameInput');
// console.log(usernameInput)
// const tableBody = document.querySelector('tbody');
//
// tableBody.addEventListener('click', (event) => {
//   const editButton = event.target.closest('.edit-user-button');
//
//   if (editButton) {
//     const row = editButton.closest('tr');
//     const userId = row.cells[0].textContent;
//     const request = new Request(`/api/admin/${userId}.editUser`, {
//
//       method: 'PUT',
//       headers: {
//         'Content-Type': 'application/json'
//       }
//     });
//     fetch(request)
//     .then(response => response.json())
//     .then(user => {
//       usernameInput.value = user.username;
//     });
//   }
// });

