<!DOCTYPE html>
<html>
<head>
    <title>Developer</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>

<jsp:include page="navigation.jsp"/>

<div class="container">
    <div class="row">
        <h2>Developer</h2>
    </div>

    <div class="row">

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Age</th>
                <th scope="col">Gender</th>
                <th scope="col">Salary</th>
                <th scope="col">Skills</th>
                <th scope="col">Projects</th>
            </tr>
            </thead>
            <tbody>

         <% ua.goIt.model.Developer developer = (ua.goIt.model.Developer) request.getAttribute("developer");  %>

                <tr>
                    <td><%= developer.getId() %></td>
                    <td><%= developer.getName() %></td>
                    <td><%= developer.getAge() %></td>
                    <td><%= developer.getSex() %></td>
                    <td><%= developer.getSalary() %></td>
                    <td>
                          <%
                           java.util.List<ua.goIt.model.Skill> skills = developer.getSkills();
                              for(ua.goIt.model.Skill skl : skills){%>
                                <p>
                                   <%= skl.getLanguage()%>
                                   (<%=skl.getLevel()%>);<br>
                                </p>
                            <%}%>
                    </td>
                    <td>
                          <%
                           java.util.List<ua.goIt.model.Project> projects = developer.getProjects();
                             for(ua.goIt.model.Project prj : projects){%>
                                  <a href = "/project/<%=prj.getId()%>"> <%= prj.getName()%>;<br></a>
                           <%}%>
                    </td>

                    <td>
                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                            <div class="btn-group me-2" role="group" aria-label="Second group">
                                <a href="/developer/edit/<%= developer.getId() %>" type="button" class="btn btn-warning">Edit</a>
                                <a href="/developer/remove/<%= developer.getId() %>" type="button" class="btn btn-danger">Remove</a>
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