
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
          <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteUserModal" data-id="${user.id}">Delete</button>
        </td>
      `;
    tableBody.appendChild(row);
  });
});



