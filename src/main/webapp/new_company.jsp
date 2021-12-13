<!DOCTYPE html>
<html>
<head>
    <title>View company</title>
    <meta charset="UTF-8">
    <jsp:include page="headers.jsp"/>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<% ua.goIt.model.Company company = (ua.goIt.model.Company) request.getAttribute("company"); %>
<div class="container">
<div class="row">
        <h2>Add company</h2>
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/companies" type="button" class="btn btn-success">Back to companies</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control"
                   value=""
                   id="name" placeholder="Company name">
        </div>
        <div class="mb-3">
            <label for="quantity " class="form-label">Age</label>
            <input type="text" class="form-control"
                   value=""
                   id="quantity" placeholder="Quantity Employee">

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
    let quantity = document.getElementById('quantity');

    function save() {
     let body = {
        name: name.value,
        quantityEmployee: quantity.value,
      }
         let url = '/company/new';
         let method = 'POST';
        fetch(url, {
            method: method,
            body: JSON.stringify(body)
        })
        .then( _ => {
            window.location.href = '/companies';
        }
        );
    }
</script>
</body>
</html>