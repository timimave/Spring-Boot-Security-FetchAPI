$(document).ready(function(){
  $.get("api/admin/currentUser", function(data, status){
    console.log(status);
    if (status === "success") {
      var userDetails = "<tr>" +
          "<td>" + data.id + "</td>" +
          "<td>" + data.username + "</td>" +
          "<td>" + data.name + "</td>" +
          "<td>" + data.lastname + "</td>" +
          "<td>" + data.personWhoStudiesJava + "</td>" +
          "<td>" + data.roles[0].name + "</td></tr>";
      console.log(userDetails);
      $("#users-table-body").append(userDetails);
    }
  });
});