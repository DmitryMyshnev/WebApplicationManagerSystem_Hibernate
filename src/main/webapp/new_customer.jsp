<!DOCTYPE html>
<html>
<head>
    <title>View customer</title>
    <meta charset="UTF-8">
    <jsp:include page="headers.jsp"/>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<% ua.goIt.model.Customer customer = (ua.goIt.model.Customer) request.getAttribute("customer"); %>
<div class="container">
<div class="row">
        <h2>Add customer</h2>
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/customers" type="button" class="btn btn-success">Back to customers</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="first name" class="form-label">Name</label>
            <input type="text" class="form-control"
                   value=""
                   id="firstName" placeholder="First name">
        </div>
        <div class="mb-3">
            <label for="last name " class="form-label">Age</label>
            <input type="text" class="form-control"
                   value=""
                   id="lastName" placeholder="Last name">

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
    let firstName = document.getElementById('firstName');
    let lastName = document.getElementById('lastName');

    function save() {
     let body = {
        firstName: firstName.value,
        lastName: lastName.value,
      }
         let url = '/customer/new';
         let method = 'POST';
        fetch(url, {
            method: method,
            body: JSON.stringify(body)
        })
        .then( _ => {
            window.location.href = '/customers';
        }
        );
    }
</script>
</body>
</html>