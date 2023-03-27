
const editUserModal = document.querySelector('#editUserModal');
const usernameInput = editUserModal.querySelector('#usernameInput');
console.log(usernameInput)
const tableBody = document.querySelector('tbody');

tableBody.addEventListener('click', (event) => {
  const editButton = event.target.closest('.edit-user-button');

  if (editButton) {
    const row = editButton.closest('tr');
    const userId = row.cells[0].textContent;
    const request = new Request(`/api/admin/${userId}`, {

      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    });
    fetch(request)
    .then(response => response.json())
    .then(user => {
      usernameInput.value = user.username;
    });
  }
});