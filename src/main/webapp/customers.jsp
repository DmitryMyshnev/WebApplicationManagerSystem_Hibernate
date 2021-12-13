<!DOCTYPE html>
<html>
<head>
    <title>Customer page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>

<jsp:include page="navigation.jsp"/>

<div class="container">
    <div class="row">
        <h2>Customer page</h2>
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/customer/new" type="button" class="btn btn-primary">New</a>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Projects</th>
            </tr>
            </thead>
            <tbody>
            <%
            Object[] customers = (Object[]) request.getAttribute("customers");
                for(Object cst : customers) {
                ua.goIt.model.Customer customer = (ua.goIt.model.Customer) cst;
                %>
                <tr>
                    <td><%= customer.getId() %></td>
                    <td><%= customer.getFirstName() %></td>
                    <td><%= customer.getLastName() %></td>
                    <td>
                          <%
                            java.util.List<ua.goIt.model.Project> projects = customer.getProjects();
                               for(ua.goIt.model.Project prj : projects){%>
                                  <a href = "/project/<%=prj.getId()%>"> <%= prj.getName()%>;<br></a>
                          <%}%>
                    </td>
                    <td>
                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                            <div class="btn-group me-2" role="group" aria-label="Second group">
                                <a href="/customer/edit/<%= customer.getId() %>" type="button" class="btn btn-warning">Edit</a>
                                <a href="/customer/remove/<%= customer.getId() %>" type="button" class="btn btn-danger">Remove</a>
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