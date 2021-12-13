<!DOCTYPE html>
<html>
<head>
    <title>Company page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>

<jsp:include page="navigation.jsp"/>

<div class="container">
    <div class="row">
        <h2>Company page</h2>
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/company/new" type="button" class="btn btn-primary">New</a>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Quantity Employee</th>
                <th scope="col">Projects</th>
            </tr>
            </thead>
            <tbody>
            <%
            Object[] companies = (Object[]) request.getAttribute("companies");
                for(Object comp : companies) {
                ua.goIt.model.Company company = (ua.goIt.model.Company) comp;
                %>
                <tr>
                    <td><%= company.getId() %></td>
                    <td><%= company.getName() %></td>
                    <td><%= company.getQuantityEmployee() %></td>

                    <td><%
                          java.util.List<ua.goIt.model.Project> projects = company.getProjects();
                            for(ua.goIt.model.Project prj : projects){%>
                                <a href = "/project/<%=prj.getId()%>"> <%= prj.getName()%>;<br></a>
                           <% }%>
                    </td>
                    <td>
                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                            <div class="btn-group me-2" role="group" aria-label="Second group">
                                <a href="/company/edit/<%= company.getId() %>" type="button" class="btn btn-warning">Edit</a>
                                <a href="/company/remove/<%= company.getId() %>" type="button" class="btn btn-danger">Remove</a>
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