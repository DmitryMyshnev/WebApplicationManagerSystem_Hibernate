<!DOCTYPE html>
<html>
<head>
    <title>Skills page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>

<jsp:include page="navigation.jsp"/>

<div class="container">
    <div class="row">
        <h2>Skills page</h2>
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/skill/new" type="button" class="btn btn-primary">New</a>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Language</th>
                <th scope="col">Level</th>
                <th scope="col">Developers</th>
            </tr>
            </thead>
            <tbody>
            <%
            Object[] skills = (Object[]) request.getAttribute("skills");
                for(Object scl : skills) {
                ua.goIt.model.Skill skill = (ua.goIt.model.Skill) scl;
                %>
                <tr>
                    <td><%= skill.getId() %></td>
                    <td><%= skill.getLanguage() %></td>
                    <td><%= skill.getLevel() %></td>
                    <td>
                     <%
                        java.util.List<ua.goIt.model.Developer> developers = skill.getDevelopers();
                              for(ua.goIt.model.Developer dev : developers){%>
                                <a href = "/developer/<%=dev.getId()%>">
                                <%= dev.getName()%>
                                ;<br></a>
                       <%}%>
                    </td>
                    <td>
                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" >
                            <div class="btn-group me-2" role="group" aria-label="Second group">
                                <a href="/skill/edit/<%= skill.getId() %>" type="button" class="btn btn-warning">Edit</a>
                                <a href="/skill/remove/<%= skill.getId() %>" type="button" class="btn btn-danger">Remove</a>
                            </div>
                        </div>
                    </td>
                </tr>
               <% } %>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>