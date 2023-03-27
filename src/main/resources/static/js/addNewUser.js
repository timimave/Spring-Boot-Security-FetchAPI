
// document.getElementById("newUserForm").addEventListener("submit", function(event) {
//   event.preventDefault(); // остановить отправку формы по умолчанию
//   const form = event.target;
//   const formData = new FormData(form);
//   fetch('/api/admin/add', {
//     method: 'POST',
//     body: formData
//   })
//   .then(response => {
//     if (response.ok) {
//       console.log('User added!');
//       // выполнить какие-либо действия после успешного добавления пользователя
//     } else {
//       console.error('Error adding user');
//       // выполнить какие-либо действия в случае ошибки при добавлении пользователя
//     }
//   })
//   .catch(error => {
//     console.error('Error adding user', error);
//     // выполнить какие-либо действия в случае ошибки при добавлении пользователя
//   });
// });


document.getElementById("newUserForm").addEventListener("submit", function(event) {
  event.preventDefault(); // остановить отправку формы по умолчанию
  const form = event.target;
  const formData = new FormData(form);

  // преобразование объекта FormData в объект JSON
  const json = {};
  for (const [key, value] of formData.entries()) {
    json[key] = value;
  }

  fetch('/api/admin/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(json) // отправка данных в формате JSON
  })
  .then(response => {
    if (response.ok) {
      console.log('User added!');
      // выполнить какие-либо действия после успешного добавления пользователя
    } else {
      console.error('Error adding user');
      // выполнить какие-либо действия в случае ошибки при добавлении пользователя
    }
  })
  .catch(error => {
    console.error('Error adding user', error);
    // выполнить какие-либо действия в случае ошибки при добавлении пользователя
  });
});

