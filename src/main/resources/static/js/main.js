
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
        modal.querySelector('#personWhoStudiesJavaInput').value = data.personWhoStudiesJava;
        modal.querySelector('#passwordInput').value = '';
        const rolesInput = modal.querySelector('#rolesInput');
        if (data.roles) {
          rolesInput.querySelectorAll('option').forEach(option => {
            option.selected = data.roles.some(role => role.role === option.text);
          });
        }
      });
    });
  });
});

// Добавляем обработчик события отправки формы
editUserForm.addEventListener('submit', event => {
  event.preventDefault(); // отменяем стандартное поведение браузера

  const userId = document.querySelector('#userIdInput').value;

  // Собираем данные из формы
  const formData = new FormData(editUserForm);
  const userData = {

    id: document.querySelector('#userIdInput').value,
    username: document.querySelector('#usernameInput').value,
    name: document.querySelector('#nameInput').value,
    lastname: document.querySelector('#lastnameInput').value,
    personWhoStudiesJava: document.querySelector('#personWhoStudiesJavaInput').value,
    password: document.querySelector('#passwordInput').value,
    roles: Array.from(document.querySelectorAll('#rolesInput option:checked')).map(option => ({id: option.value}))

  };

  console.log('roles:', userData.roles);

  // Отправляем PUT-запрос на сервер для сохранения изменений
  fetch(`/api/admin/${userId}/editUser`, {
    method: 'PUT',
    body: JSON.stringify(userData),
    headers: {
      'Content-Type': 'application/json'
    }
  })
  .then(response => {
    if (response.ok) {
      // Если запрос успешно выполнен, перезагружаем страницу
      location.reload();
    } else {
      // Если сервер вернул ошибку, выводим сообщение об ошибке
      alert('Failed to save changes');
    }
  })
  .catch(error => {
    // Если произошла ошибка при выполнении запроса, выводим сообщение об ошибке
    console.error(error);
    alert('Failed to save changes');
  });
});



// получение CSRF-токена ------
// const csrfToken = document.querySelector('input[name="_csrf"]').value;

// удаление юзера по id --------------------------------------------------------
  function deleteUser(id) {
    fetch(`/api/admin/${id}/delete`,
        {
          method: 'DELETE',
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






// window.onload = () => {
//   // Получаем форму и кнопку "Save changes"
//   const editUserForm = document.querySelector('#editUserForm');
//   const saveChangesBtn = document.querySelector('#editUserModal .modal-footer button[type="submit"]');
//
// // Добавляем обработчик на клик по кнопке "Save changes"
//   saveChangesBtn.addEventListener('click', (event) => {
//     event.preventDefault(); // Отменяем стандартное поведение кнопки (отправку формы)
//
//     // Получаем данные из формы
//     const formData = new FormData(editUserForm);
//
//     // Отправляем данные на сервер
//     fetch(`/api/admin/${formData.get('id')}/editUser`, {
//       method: 'PUT',
//       headers: {
//         'Content-Type': 'application/json'
//       },
//       body: JSON.stringify({
//         username: formData.get('username'),
//         name: formData.get('name'),
//         lastname: formData.get('lastname'),
//         personWhoStudiesJava: formData.get('personWhoStudiesJava'),
//         password: formData.get('password'),
//         role: formData.getAll('roles[]')
//       })
//     })
//     .then(response => {
//       if (!response.ok) {
//         throw new Error('Failed to update user');
//       }
//       $('#editUserModal').modal('hide'); // Скрываем модальное окно
//       location.reload(); // Перезагружаем страницу, чтобы отобразить изменения
//     })
//     .catch(error => {
//       console.error(error);
//       alert('Failed to update user');
//     });
//   });
// };







































