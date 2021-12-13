<!DOCTYPE html>
<html>
<head>
    <title>View skill</title>
    <meta charset="UTF-8">
    <jsp:include page="headers.jsp"/>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<% ua.goIt.model.Skill skill = (ua.goIt.model.Skill) request.getAttribute("skill"); %>
<div class="container">
<div class="row">
        <h2>Edit skill</h2>
    </div>
    <div class="row col-6">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/skills" type="button" class="btn btn-success">Back to skills</a>
            </div>
        </div>
    </div>
    <div class="row col-6">
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <input type="text" disabled class="form-control"
                   value="<%= skill.getId() == null ? "" : skill.getId() %>"
                   id="id" placeholder="Id">
        </div>
        <div class="mb-3">
            <label for="language" class="form-label">Language</label>
            <input type="text" class="form-control"
                   value="<%= skill.getLanguage() == null ? "" : skill.getLanguage() %>"
                   id="language" placeholder="Language">
        </div>
        <div class="mb-3">
            <label for="level" class="form-label">Level</label>
            <input type="text" class="form-control"
                   value="<%= skill.getLevel() == null ? "" : skill.getLevel() %>"
                   id="level" placeholder="Level">
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
    let language = document.getElementById('language');
    let level = document.getElementById('level');

    function save() {
     let body = {
        id: id.value,
        language: language.value,
        level: level.value,
       }
         let url = '/skill/edit/<%= skill.getId() %>';
         let method = 'PUT';
        fetch(url, {
            method: method,
            body: JSON.stringify(body)
        })
         .then( _ => {
            window.location.href = '/skills';
         }
        );
    }
</script>
</body>
</html>