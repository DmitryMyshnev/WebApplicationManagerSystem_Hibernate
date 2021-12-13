<!DOCTYPE html>
<html>
<head>
    <title>View user</title>
    <meta charset="UTF-8">
    <jsp:include page="headers.jsp"/>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<% ua.goIt.model.Developer developer = (ua.goIt.model.Developer) request.getAttribute("developer"); %>
<div class="container">
<div class="row">
        <h2>Edit developer</h2>
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/developers" type="button" class="btn btn-success">Back to users</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control"
                   value=""
                   id="name" placeholder="Developer name">
        </div>
        <div class="mb-3">
            <label for="age" class="form-label">Age</label>
            <input type="text" class="form-control"
                   value=""
                   id="age" placeholder="Developer age">
        </div>
        <div class="mb-3">
            <label for="gender" class="form-label">Gender</label>
            <input type="text" class="form-control"
                   value=""
                   id="gender" placeholder="Developer gender">
        </div>
         <div class="mb-3">
                    <label for="salary" class="form-label">Salary</label>
                    <input type="text" class="form-control"
                           value=""
                           id="salary" placeholder="Developer salary">
                </div>
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <button onclick="save()" type="button" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>
<script>
    let name = document.getElementById('name');
    let age = document.getElementById('age');
    let gender = document.getElementById('gender');
    let salary = document.getElementById('salary');

    function save() {
     let body = {
        name: name.value,
        age: age.value,
        sex: gender.value,
        salary: salary.value
      }
         let url = '/developer/new';
         let method = 'POST';
        fetch(url, {
            method: method,
            body: JSON.stringify(body)
        })
        .then( _ => {
            window.location.href = '/developers';
        }
        );
    }
</script>
</body>
</html>