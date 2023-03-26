
// вывод юзеров на страницу
fetch('/api/admin')
.then(response => response.json())
.then(users => {
  const tableBody = document.querySelector('tbody');
  tableBody.innerHTML = '';
  users.forEach(user => {
    const row = document.createElement('tr');
    row.innerHTML = `

        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.name}</td>
        <td>${user.lastname}</td>
        <td>${user.personWhoStudiesJava}</td>
        <td>${user.roles.map(role => role.role).join(', ')}</td>
        <td>
          <a type="button" class="btn btn-primary" href="/admin/${user.id}/edit">Edit</a>
        </td>
        <td>
  <button type="button" class="btn btn-danger" onclick="deleteUser(${user.id})">Delete</button>
        </td>
      `;
    tableBody.appendChild(row);
  });
});

// удаление юзера по id

function deleteUser(id) {
  fetch(`/api/admin/${id}/delete`, { method: 'DELETE' })
  .then(response => {
    if (response.ok) {
      location.reload(); // обновляем страницу после удаления
    } else {
      alert('Ошибка при удалении пользователя');
    }
  });
}

// const deleteButtons = document.querySelectorAll('.btn-danger');
//
// deleteButtons.forEach(button => {
//   button.addEventListener('click', event => {
//     const url = event.target.dataset.url;
//     const id = event.target.dataset.id;
//     fetch(url, {
//       method: 'DELETE',
//     }).then(response => {
//       if (response.ok) {
//         const row = event.target.closest('tr');
//         row.remove();
//         // show success message or redirect to another page
//       } else {
//         // show error message
//       }
//     });
//   });
// });


