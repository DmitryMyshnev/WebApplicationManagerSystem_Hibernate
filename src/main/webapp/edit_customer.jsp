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
        <h2>Edit customer</h2>
    </div>
    <div class="row col-6">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/customers" type="button" class="btn btn-success">Back to customer</a>
            </div>
        </div>
    </div>
    <div class="row col-6">
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <input type="text" disabled class="form-control"
                   value="<%= customer.getId() == null ? "" : customer.getId() %>"
                   id="id" placeholder="Id">
        </div>
        <div class="mb-3">
            <label for="first name" class="form-label">First Name</label>
            <input type="text" class="form-control"
                   value="<%= customer.getFirstName() == null ? "" : customer.getFirstName() %>"
                   id="firstName" placeholder="First name">
        </div>
        <div class="mb-3">
            <label for="last name" class="form-label">Last Name</label>
            <input type="text" class="form-control"
                   value="<%= customer.getLastName() == null ? "" : customer.getLastName() %>"
                   id="lastName" placeholder="Last name">
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
    let id = document.getElementById('id');
    let firstName = document.getElementById('firstName');
    let lastName = document.getElementById('lastName');

    function save() {
     let body = {
        id: id.value,
        firstName: firstName.value,
        lastName: lastName.value,
       }
         let url = '/customer/edit/<%= customer.getId() %>';
         let method = 'PUT';
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