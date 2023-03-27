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


//
// fetch('/api/admin')
// .then(response => response.json())
// .then(users => {
//   const tableBody = document.querySelector('tbody');
//   tableBody.innerHTML = '';
//   users.forEach(user => {
//     const row = document.createElement('tr');
//     row.innerHTML = `
//
//         <td>${user.id}</td>
//         <td>${user.username}</td>
//         <td>${user.name}</td>
//         <td>${user.lastname}</td>
//         <td>${user.personWhoStudiesJava}</td>
//         <td>${user.roles.map(role => role.role).join(', ')}</td>
//         <td>
//
//         <button type="button" class="btn btn-primary" th:attr="data-userid=${user.id}" data-toggle="modal" data-target="#editUserModal" id="editUserBtn">Edit</button>
//
//
//         </td>
//         <td>
//   <button type="button" class="btn btn-danger" onclick="deleteUser(${user.id})">Delete</button>
//         </td>
//       `;
//     tableBody.appendChild(row);
//   });
//
//
//   $(document).ready(function() {
//     $('#editUserBtn').on('click', function() {
//       console.log("Button clicked!");
//     });
//   });
//
//   $(document).ready(function() {
//     console.log('Скрипт запущен');
//     $('#editUserModal').on('show.bs.modal', function (event) {
//       var button = $(event.relatedTarget); // Кнопка, которая вызвала модальное окно
//       var userId = button.data('userid'); // Извлекаем ID пользователя из data-атрибута кнопки
//       var modal = $(this);
//       // Отправляем GET-запрос на сервер для получения данных пользователя по ID
//       $.get('/api/admin/' + userId, function(data) {
//         console.log("API response received:", data);
//         // Заполняем соответствующие поля на модальном окне данными пользователя
//         modal.find('#userIdInput').val(data.id);
//         modal.find('#usernameInput').val(data.username);
//         modal.find('#nameInput').val(data.name);
//         modal.find('#lastnameInput').val(data.lastname);
//       });
//     });
//   });
// });

// // вывод юзеров на страницу------------------------------------------------------
// fetch('/api/admin')
// .then(response => response.json())
// .then(users => {
//   const tableBody = document.querySelector('tbody');
//   tableBody.innerHTML = '';
//   users.forEach(user => {
//     const row = document.createElement('tr');
//     row.innerHTML = `
//
//         <td>${user.id}</td>
//         <td>${user.username}</td>
//         <td>${user.name}</td>
//         <td>${user.lastname}</td>
//         <td>${user.personWhoStudiesJava}</td>
//         <td>${user.roles.map(role => role.role).join(', ')}</td>
//         <td>
//
//         <button type="button" class="btn btn-primary" th:attr="data-userid=${user.id}" data-toggle="modal" data-target="#editUserModal" id="editUserBtn">Edit</button>
//
//
//         </td>
//         <td>
//   <button type="button" class="btn btn-danger" onclick="deleteUser(${user.id})">Delete</button>
//         </td>
//       `;
//     tableBody.appendChild(row);
//   });
// });





// $(document).ready(function() {
//   console.log('Script is running');
//   $('#editUserBtn').click(function() {
//     var userId = $(this).data('userid'); // Извлекаем ID пользователя из data-атрибута кнопки
//     var modal = $('#editUserModal');
//     // Отправляем GET-запрос на сервер для получения данных пользователя по ID
//     $.get('/api/admin/' + userId, function(data) {
//       // Заполняем соответствующие поля на модальном окне данными пользователя
//       modal.find('#userIdInput').val(data.id);
//       modal.find('#usernameInput').val(data.username);
//       modal.find('#nameInput').val(data.name);
//       modal.find('#lastnameInput').val(data.lastname);
//     });
//   });
// });

// $(document).ready(function() {
//   console.log('Script is running'); // Выводим сообщение в консоль браузера при запуске скрипта
//   $('#editUserBtn').click(function() { // При нажатии на кнопку Edit выводим еще одно сообщение в консоль
//     console.log('Edit button clicked');
//   });
// });

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
//     const request = new Request(`/api/admin/${userId}`, {
//
//       method: 'GET',
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





// const editUserModal = document.querySelector('#editUserModal');
// const usernameInput = editUserModal.querySelector('#usernameInput');
//
// const tableBody = document.querySelector('tbody');
//
// tableBody.addEventListener('click', (event) => {
//   const editButton = event.target.closest('.edit-user-button');
//
//   if (editButton) {
//     const row = editButton.closest('tr');
//     const userId = row.cells[0].textContent;
//     const xhr = new XMLHttpRequest();
//     xhr.open('GET', `/api/admin/${userId}`, true);
//     xhr.responseType = 'json';
//     xhr.onload = function() {
//       if (xhr.status === 200) {
//         const user = xhr.response;
//         usernameInput.value = user.username;
//       }
//     };
//     xhr.send();
//   }
// });

// const editUserModal = document.querySelector('#editUserModal');
// const usernameInput = editUserModal.querySelector('#usernameInput');
//
// const tableBody = document.querySelector('tbody');
//
// tableBody.addEventListener('click', (event) => {
//   const editButton = event.target.closest('.edit-user-button');
//
//   if (editButton) {
//     const row = editButton.closest('tr');
//     const userId = row.cells[0].textContent;
//     const request = new Request(`/api/admin/${userId}`, {
//       method: 'GET',
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


















// // Функция для заполнения полей формы данными пользователя
// function populateEditUserForm(user) {
//   // Находим поля формы
//   const userIdInput = document.getElementById('userIdInput');
//   const usernameInput = document.getElementById('usernameInput');
//   const nameInput = document.getElementById('nameInput');
//   const lastnameInput = document.getElementById('lastnameInput');
//   const personWhoStudiesJavaInput = document.getElementById('personWhoStudiesJavaInput');
//   const passwordInput = document.getElementById('passwordInput');
//   const rolesInput = document.getElementById('rolesInput');
//
//   // Заполняем поля формы данными пользователя
//   userIdInput.value = user.id;
//   usernameInput.value = user.username;
//   nameInput.value = user.name;
//   lastnameInput.value = user.lastname;
//   personWhoStudiesJavaInput.value = user.personWhoStudiesJava;
//   passwordInput.value = user.password;
//
//   // Заполняем список ролей
//   rolesInput.innerHTML = '';
//   for (let i = 0; i < user.roles.length; i++) {
//     const role = user.roles[i];
//     const option = document.createElement('option');
//     option.value = role.id;
//     option.textContent = role.name;
//     rolesInput.appendChild(option);
//   }
// }
//
// // Функция для получения данных пользователя с сервера и открытия модального окна редактирования
// function editUser(userId) {
//   // Отправляем GET-запрос на сервер для получения данных пользователя по его id
//   fetch(`/api/admin/${userId}/editUser`)
//   .then(response => response.json())
//   .then(data => {
//     // Заполняем поля формы данными пользователя
//     populateEditUserForm(data);
//
//     // Открываем модальное окно редактирования
//     $('#editUserModal').modal('show');
//   })
//   .catch(error => {
//     console.error('Error:', error);
//     alert('Ошибка при получении данных пользователя');
//   });
// }
// document.getElementById('editButton').addEventListener('click', () => {
//   editUser(id);
// });








//
// function editUser(id) {
//   fetch(`/api/admin/${id}/editUser`)
//   .then(response => response.json())
//   .then(user => {
//
//     console.log(user); // Выводим объект пользователя в консоль
//     // Заполняем поля модального окна данными пользователя
//
//     // Заполняем поля формы значениями из полученного объекта пользователя
//     document.getElementById('userIdInput').value = user.id;
//     document.getElementById('usernameInput').value = user.username;
//     document.getElementById('nameInput').value = user.name;
//     document.getElementById('lastnameInput').value = user.lastname;
//     //document.getElementById('personWhoStudiesJavaInput').value = user.personWhoStudiesJava;
//     document.getElementById('rolesInput').value = user.roles.map(role => role.id);
//
//     // Заполняем поле "Roles" данными о ролях пользователя
//     let rolesInput = document.getElementById('rolesInput');
//     rolesInput.value = user.roles.map(role => role.id); // Преобразуем массив объектов ролей в массив их идентификаторов
//
//     // Открываем модальное окно
//     $('#editUserModal').modal('show');
//   });
// }



// // Получаем форму и назначаем обработчик отправки
// const editUserForm = document.querySelector('#editUserForm');
// editUserForm.addEventListener('submit', function (event) {
//   event.preventDefault(); // Отменяем действие по умолчанию (отправку формы)
//
//   const userId = document.querySelector('#userIdInput').value;
//   const username = document.querySelector('#usernameInput').value;
//   const name = document.querySelector('#nameInput').value;
//   const lastname = document.querySelector('#lastnameInput').value;
//   const personWhoStudiesJava = document.querySelector('#personWhoStudiesJavaInput').value;
//   const password = document.querySelector('#passwordInput').value;
//   const roles = [...document.querySelector('#rolesInput').options].filter(option => option.selected).map(option => option.value);
//
//   const user = {
//     username,
//     name,
//     lastname,
//     personWhoStudiesJava,
//     password,
//     roles
//   };
//
//   fetch(`/api/admin/${userId}/editUser`, {
//     method: 'PUT',
//     headers: {
//       'Content-Type': 'application/json'
//     },
//     body: JSON.stringify(user)
//   })
//   .then(response => {
//     if (response.ok) {
//       location.reload(); // обновляем страницу после успешного редактирования
//     } else {
//       alert('Ошибка при редактировании пользователя');
//     }
//   });
// });



// // отображение данных в полях юзера
// fetch(`/api/admin/${userId}`)
// .then(response => response.json())
// .then(user => {
//   // Получаем доступ к элементам формы редактирования
//   const usernameInput = document.querySelector('#usernameInput');
//
//   // Устанавливаем значение поля username равным имени текущего пользователя
//   usernameInput.value = user.username;
// });
//
//
//
//
// // обработчик события нажатия на кнопку "Edit" --------------------------------
// document.addEventListener('click', function(event) {
//   if (event.target.classList.contains('edit-user-btn')) {
//     const userId = event.target.dataset.userId; // получаем id пользователя из data-атрибута кнопки
//     const editUserModal = document.querySelector('#editUserModal');
//     const editUserForm = editUserModal.querySelector('#editUserForm');
//
//     // заполняем форму данными пользователя
//     fetch(`/api/admin/${userId}/editUser`)
//     .then(response => response.json())
//     .then(user => {
//       editUserForm.elements['id'].value = user.id;
//       editUserForm.elements['username'].value = user.username;
//       editUserForm.elements['name'].value = user.name;
//       editUserForm.elements['lastname'].value = user.lastname;
//       editUserForm.elements['password'].value = '';
//       editUserForm.elements['roles'].value = user.roles.map(role => role.id);
//       editUserForm.elements['personWhoStudiesJava'].value = user.personWhoStudiesJava;
//     });
//
//     // обработчик события отправки формы
//     editUserForm.addEventListener('submit', function(event) {
//       event.preventDefault();
//
//       const formData = new FormData(event.target);
//       const data = {
//         id: formData.get('id'),
//         username: formData.get('username'),
//         name: formData.get('name'),
//         lastname: formData.get('lastname'),
//         password: formData.get('password'),
//         roles: formData.getAll('roles'),
//         personWhoStudiesJava: formData.get('personWhoStudiesJava')
//       };
//
//       // отправляем данные на сервер
//       fetch(`/api/admin/${userId}/editUser`, {
//         method: 'PUT',
//         headers: { 'Content-Type': 'application/json' },
//         body: JSON.stringify(data)
//       })
//       .then(response => {
//         if (response.ok) {
//           editUserModal.modal('hide'); // закрываем модальное окно после успешного сохранения
//           location.reload(); // обновляем страницу после изменения
//         } else {
//           alert('Ошибка при редактировании пользователя');
//         }
//       });
//     });
//   }
// });

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


