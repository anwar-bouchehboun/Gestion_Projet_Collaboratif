<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container-fluid">
    <h2 class="text-center mb-4">Tâches de ${member.prenom} ${member.nom}</h2>
    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <div class="card-header bg-info text-white">
                    <h5>À faire</h5>
                </div>
                <div class="card-body">
                    <c:forEach var="task" items="${tasks}">
                        <c:if test="${task.statut == 'A_FAIRE'}">
                            <div class="card mb-2">
                                <div class="card-body">
                                    <h6 class="card-title">${task.titre}</h6>
                                    <p class="card-text">${task.description}</p>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-header bg-warning text-white">
                    <h5>En cours</h5>
                </div>
                <div class="card-body">
                    <c:forEach var="task" items="${tasks}">
                        <c:if test="${task.statut == 'EN_COURS'}">
                            <div class="card mb-2">
                                <div class="card-body">
                                    <h6 class="card-title">${task.titre}</h6>
                                    <p class="card-text">${task.description}</p>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-header bg-success text-white">
                    <h5>Terminé</h5>
                </div>
                <div class="card-body">
                    <c:forEach var="task" items="${tasks}">
                        <c:if test="${task.statut == 'TERMINE'}">
                            <div class="card mb-2">
                                <div class="card-body">
                                    <h6 class="card-title">${task.titre}</h6>
                                    <p class="card-text">${task.description}</p>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>