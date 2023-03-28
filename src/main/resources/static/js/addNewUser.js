
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
      console.log('');
    } else {
      console.error('Error adding user');

    }
  })
  .catch(error => {
    console.error('Error adding user', error);

  });
});

