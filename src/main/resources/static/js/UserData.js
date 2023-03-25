
// Отправляем GET-запрос на сервер, чтобы получить данные о пользователе
fetch('/userRest')
.then(response => response.json())
.then(user => {
  const tableBody = document.querySelector('tbody');
  const row = tableBody.insertRow();
  const roles = [];
  for (const role of user.roles) {
    roles.push(role.role);
  }
  row.innerHTML = `
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.name}</td>
            <td>${user.lastname}</td>
            <td>${user.personWhoStudiesJava}</td>
            <td>${roles.join(', ')}</td>
        `;
  tableBody.appendChild(row);
});