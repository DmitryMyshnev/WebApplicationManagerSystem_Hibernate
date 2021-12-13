<!DOCTYPE html>
<html>
<head>
    <title>View project</title>
    <meta charset="UTF-8">
    <jsp:include page="headers.jsp"/>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<% ua.goIt.model.Project project = (ua.goIt.model.Project) request.getAttribute("project"); %>
<div class="container">
<div class="row">
        <h2>New project</h2>
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/projects" type="button" class="btn btn-success">Back to users</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control"
                   value=""
                   id="name" placeholder="Project name">
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control"
                   value=""
                   id="description" placeholder="Project description">
        </div>
        <div class="mb-3">
            <label for="cost" class="form-label">Cost</label>
            <input type="text" class="form-control"
                   value=""
                   id="cost" placeholder="Project cost">
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
    let description = document.getElementById('description');
    let cost = document.getElementById('cost');

    function save() {
     let body = {
        name: name.value,
        description: description.value,
        cost: cost.value,
      }
         let url = '/project/new';
         let method = 'POST';
        fetch(url, {
            method: method,
            body: JSON.stringify(body)
        })
        .then( _ => {
            window.location.href = '/projects';
        }
        );
    }
</script>
</body>
</html>