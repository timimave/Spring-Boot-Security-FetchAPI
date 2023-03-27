// вывод юзеров на страницу------------------------------------------------------
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
        <button type="button" class="btn btn-primary editUserBtn" data-userid="${user.id}" data-toggle="modal" data-target="#editUserModal">Edit</button>
      </td>
      <td>
        <button type="button" class="btn btn-danger" onclick="deleteUser(${user.id})">Delete</button>
      </td>
    `;
    tableBody.appendChild(row);
  });

  // Событие клика на кнопке редактирования пользователя
  document.querySelectorAll('.editUserBtn').forEach(button => {
    button.addEventListener('click', () => {
      console.log('Button clicked!');
      const userId = button.dataset.userid;
      const modal = document.querySelector('#editUserModal');
      // Отправляем GET-запрос на сервер для получения данных пользователя по ID
      fetch(`/api/admin/${userId}`)
      .then(response => response.json())
      .then(data => {
        // Заполняем соответствующие поля на модальном окне данными пользователя
        modal.querySelector('#userIdInput').value = data.id;
        modal.querySelector('#usernameInput').value = data.username;
        modal.querySelector('#nameInput').value = data.name;
        modal.querySelector('#lastnameInput').value = data.lastname;
      });
    });
  });
});



// получение CSRF-токена ------
// const csrfToken = document.querySelector('input[name="_csrf"]').value;

// удаление юзера по id --------------------------------------------------------
function deleteUser(id) {
  fetch(`/api/admin/${id}/delete`,
      { method: 'DELETE',
        headers: {
          'X-CSRF-TOKEN': csrfToken
        }
  }
  )
  .then(response => {
    if (response.ok) {
      location.reload(); // обновляем страницу после удаления
    } else {
      alert('Ошибка при удалении пользователя');
    }
  });
}









































