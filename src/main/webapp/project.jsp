<!DOCTYPE html>
<html>
<head>
    <title>Project</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>

<jsp:include page="navigation.jsp"/>

<div class="container">
    <div class="row">
        <h2>Project</h2>
    </div>

    <div class="row">

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Cost</th>
                <th scope="col">Date</th>
                <th scope="col">Developers</th>
                <th scope="col">Companies</th>
                <th scope="col">Customers</th>
            </tr>
            </thead>
            <tbody>

          <% ua.goIt.model.Project project = (ua.goIt.model.Project) request.getAttribute("project");  %>

                <tr>
                    <td><%= project.getId() %></td>
                    <td><%= project.getName() %></td>
                    <td><%= project.getDescription() == null? "":project.getDescription() %></td>
                    <td><%= project.getCost() %></td>
                    <td><%= project.getDate() %></td>
                    <td>
                     <%
                         java.util.List<ua.goIt.model.Developer> developers = project.getDevelopers();
                             for(ua.goIt.model.Developer dev : developers){%>
                                 <a href = "/developer/<%= dev.getId()%>">
                                   <%= dev.getName()%>
                                  ;<br></a>
                        <%}%>
                    </td>
                    <td>
                     <%
                        java.util.List<ua.goIt.model.Company> companies = project.getCompanies();
                          for(ua.goIt.model.Company cmp : companies){%>
                              <a href = "/company/<%= cmp.getId()%>">
                                 <%= cmp.getName()%>
                                 ;<br></a>
                        <%}%>
                    </td>
                    <td>
                       <%
                          java.util.List<ua.goIt.model.Customer> customers = project.getCustomers();
                             for(ua.goIt.model.Customer custom : customers){%>
                                 <a href = "/customer/<%= custom.getId()%>">
                                 <%= custom.getFirstName()%>
                                 <%= custom.getLastName()%>
                                 ;<br></a>
                          <%}%>
                    </td>

                    <td>
                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                            <div class="btn-group me-2" role="group" aria-label="Second group">
                                <a href="/project/edit/<%= project.getId() %>" type="button" class="btn btn-warning">Edit</a>
                                <a href="/project/remove/<%= project.getId() %>" type="button" class="btn btn-danger">Remove</a>
                            </div>
                        </div>
                    </td>
                </tr>

            </tbody>
        </table>
    </div>
</div>

</body>
</html>