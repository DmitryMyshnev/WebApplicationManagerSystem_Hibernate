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
        <h2>Add skill</h2>
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/skills" type="button" class="btn btn-success">Back to skills</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="language" class="form-label">Language</label>
            <input type="text" class="form-control"
                   value=""
                   id="language" placeholder="Language">
        </div>
        <div class="mb-3">
            <label for="level " class="form-label">Level</label>
            <input type="text" class="form-control"
                   value=""
                   id="level" placeholder="Level">

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
    let language = document.getElementById('language');
    let level = document.getElementById('level');

    function save() {
     let body = {
        language: language.value,
        level: level.value,
      }
         let url = '/skill/new';
         let method = 'POST';
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