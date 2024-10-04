<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container-fluid">
    <h2 class="text-center mb-4">Membres de l'équipe ${equipe.nom}</h2>
    <div class="card">
        <div class="card-header">
            <h4>Liste des Membres</h4>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Email</th>
                        <th>Rôle</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="member" items="${members}">
                        <tr>
                            <td>${member.nom}</td>
                            <td>${member.prenom}</td>
                            <td>${member.email}</td>
                            <td>${member.role}</td>
                            <td>
                                <a href="<c:url value='/equipe?action=viewMemberTasks&id=${member.id}'/>" class="btn btn-icon btn-info" title="Voir les tâches">
                                    <i class="bi bi-list-task"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>