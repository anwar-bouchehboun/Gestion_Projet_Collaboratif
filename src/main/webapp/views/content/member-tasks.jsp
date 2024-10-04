<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <h2 class="text-center mb-4">Tasks for ${member.prenom} ${member.nom}</h2>

    <div class="card">
        <div class="card-header">Task List</div>
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Title</th>
                            <th scope="col">Description</th>
                            <th scope="col">Priority</th>
                            <th scope="col">Status</th>
                            <th scope="col">Due Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty tasks}">
                                <c:forEach var="task" items="${tasks}">
                                    <tr>
                                        <td>${task.titre}</td>
                                        <td>${task.description}</td>
                                        <td>${task.priorite}</td>
                                        <td>${task.statut}</td>
                                        <td>${task.dateEcheance}</td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="5" class="text-center">No tasks found for this member</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="mt-3">
        <a href="${pageContext.request.contextPath}/member?action=list" class="btn btn-primary">Back to Members List</a>
    </div>
</div>