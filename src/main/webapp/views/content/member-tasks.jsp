<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
    <h2 class="text-center mb-4">Tasks for ${member.prenom} ${member.nom}</h2>

    <div class="card">
<div class="card-header bg-primary text-white text-uppercase fw-bold custom-header">Task List</div>        <div class="card-body p-0">
            <div class="table-responsive">
           <table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Titre</th>
            <th scope="col">Description</th>
            <th scope="col">Priorite</th>
            <th scope="col">Statut</th>
            <th scope="col">Date Creation</th>
            <th scope="col">Date Fin</th>
            <th scope="col">Projet</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${not empty tasks}">
                <c:forEach var="task" items="${tasks}">
                    <tr>
                        <td>${fn:toUpperCase(task.id)}</td>
                        <td>${fn:toUpperCase(task.titre)}</td>
                        <td>${fn:toUpperCase(task.description)}</td>
                        <td>${fn:toUpperCase(task.priorite)}</td>
                        <td>${fn:toUpperCase(task.statut)}</td>
                        <td>${fn:toUpperCase(task.dateCreation)}</td>
                        <td>${fn:toUpperCase(task.dateEcheance)}</td>
                        <td>${fn:toUpperCase(task.projet.nom)}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="8" class="text-center">Aucune tâche trouvée pour ce membre</td>
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