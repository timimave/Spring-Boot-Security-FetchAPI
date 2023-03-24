<!-- Скрипт для заполнения таблицы данными с сервера -->

function fillUsersTable(users) {
  const tbody = document.querySelector('tbody');

  users.forEach(user => {
    const tr = document.createElement('tr');
    const idTd = document.createElement('td');
    const loginTd = document.createElement('td');
    const nameTd = document.createElement('td');
    const lastNameTd = document.createElement('td');
    const javaLearningTd = document.createElement('td');
    const roleTd = document.createElement('td');

    // Установка атрибутов для ячеек таблицы
    idTd.setAttribute('data-label', 'ID');
    loginTd.setAttribute('data-label', 'LogIn');
    nameTd.setAttribute('data-label', 'Name');
    lastNameTd.setAttribute('data-label', 'LastName');
    javaLearningTd.setAttribute('data-label', 'Is learning Java?');
    roleTd.setAttribute('data-label', 'Role');

    // Заполнение ячеек данными из объекта пользователя
    idTd.textContent = user.id;
    loginTd.textContent = user.username;
    nameTd.textContent = user.name;
    lastNameTd.textContent = user.lastname;
    javaLearningTd.textContent = user.isPersonStudyingJava === 'y' ? 'Yes' : 'No';
    roleTd.textContent = user.roles.map(role => role.role).join(', ');

    // Добавление кнопок Edit и Delete в соответствующие ячейки
    const editBtn = document.createElement('button');
    editBtn.textContent = 'Edit';
    editBtn.classList.add('btn', 'btn-primary');
    editBtn.setAttribute('data-id', user.id);

    const deleteBtn = document.createElement('button');
    deleteBtn.textContent = 'Delete';
    deleteBtn.classList.add('btn', 'btn-danger', 'ml-2');
    deleteBtn.setAttribute('data-id', user.id);

    const editTd = document.createElement('td');
    editTd.appendChild(editBtn);

    const deleteTd = document.createElement('td');
    deleteTd.appendChild(deleteBtn);


    // Добавление ячеек в строку таблицы
    tr.appendChild(idTd);
    tr.appendChild(loginTd);
    tr.appendChild(nameTd);
    tr.appendChild(lastNameTd);
    tr.appendChild(javaLearningTd);
    tr.appendChild(roleTd);
    // ---
    tr.appendChild(editTd);
    tr.appendChild(deleteTd);

    // Добавление строки в таблицу
    tbody.appendChild(tr);
  });
}
// Получение данных с сервера и заполнение таблицы
fetch('/api/admin')
.then(response => response.json())
.then(users => fillUsersTable(users))
.catch(error => console.error('Ошибка при получении данных с сервера', error));

