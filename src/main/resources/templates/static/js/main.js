
// Выполняем GET запрос на сервер и получаем список пользователей

fetch('/api/users')
  .then(response => response.json())
  .then(users => {

  // Получаем tbody таблицы
  const tbody = document.querySelector('tbody');

  // Заполняем таблицу данными
  users.forEach(user => {
    const tr = document.createElement('tr');
    const idTd = document.createElement('td');
    const loginTd = document.createElement('td');
    const nameTd = document.createElement('td');
    const lastNameTd = document.createElement('td');
    const javaLearningTd = document.createElement('td');
    const roleTd = document.createElement('td');

    idTd.textContent = user.id;
    loginTd.textContent = user.username;
    nameTd.textContent = user.name;
    lastNameTd.textContent = user.lastname;
    javaLearningTd.textContent = user.isPersonStudyingJava === 'y' ? 'Yes'
        : 'No';
    roleTd.textContent = user.roles.map(role => role.role).join(', ');

    tr.appendChild(idTd);
    tr.appendChild(loginTd);
    tr.appendChild(nameTd);
    tr.appendChild(lastNameTd);
    tr.appendChild(javaLearningTd);
    tr.appendChild(roleTd);

    tbody.appendChild(tr);

  })
  .catch(error => console.error('Ошибка при получении данных с сервера', error));

  });