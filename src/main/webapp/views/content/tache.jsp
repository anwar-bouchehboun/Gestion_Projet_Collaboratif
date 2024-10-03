<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
    body {
        background-color: #f0f2f5;
        font-family: "Poppins", sans-serif;
        color: #333;
    }
    .container-fluid {
        max-width: 1200px;
        margin-top: 40px;
    }
    .card {
        border: none;
        box-shadow: 0 0.5rem 1.5rem rgba(0, 0, 0, 0.05);
        transition: all 0.3s ease;
        border-radius: 15px;
        overflow: hidden;
    }
    .card:hover {
        box-shadow: 0 0.5rem 2rem rgba(0, 0, 0, 0.1);
    }
    .card-header {
        background-color: #4a90e2;
        color: white;
        font-weight: 600;
        text-transform: uppercase;
        letter-spacing: 1px;
        padding: 1.25rem;
        border-bottom: none;
    }
    .table {
        margin-bottom: 0;
    }
    th {
        background-color: #f8f9fa;
        color: #495057;
        font-weight: 500;
        text-transform: uppercase;
        font-size: 0.85rem;
        letter-spacing: 0.5px;
    }
    td {
        vertical-align: middle;
        font-size: 0.95rem;
    }
    .btn-icon {
        padding: 0.25rem 0.5rem;
        font-size: 0.875rem;
        line-height: 1.5;
        border-radius: 0.2rem;
    }
    .btn-edit {
        color: #fff;
        background-color: #007bff;
        border-color: #007bff;
    }
    .btn-delete {
        color: #fff;
        background-color: #dc3545;
        border-color: #dc3545;
    }
    .pagination {
        justify-content: center;
        margin-top: 20px;
    }
    .page-link {
        color: #4a90e2;
        border: none;
        border-radius: 50%;
        margin: 0 5px;
        width: 36px;
        height: 36px;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .page-item.active .page-link {
        background-color: #4a90e2;
        color: #fff;
    }
</style>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center mb-4">Gestion des Tches</h2>
            <div class="text-end mb-3">
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#taskModal">
                    <i class="bi bi-plus-circle"></i> Crer une nouvelle Tche
                </button>
            </div>
            <div class="card">
                <div class="card-header">
                    <h4>Liste des Tches</h4>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Titre</th>
                                    <th>Description</th>
                                    <th>Priorit</th>
                                    <th>Statut</th>
                                    <th>Date d'chance</th>
                                    <th>Projet</th>
                                    <th>Membre assign</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="tache" items="${taches}">
                                    <tr>
                                        <td>${tache.titre}</td>
                                        <td>${tache.description}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${tache.priorite == 'HAUTE'}">
                                                    <span class="badge bg-danger">${tache.priorite}</span>
                                                </c:when>
                                                <c:when test="${tache.priorite == 'MOYENNE'}">
                                                    <span class="badge bg-warning">${tache.priorite}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge bg-success">${tache.priorite}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${tache.statut}</td>
                                        <td>${tache.dateEcheance}</td>
                                        <td>${tache.projet.nom}</td>
                                        <td>${tache.membre.nom} ${tache.membre.prenom}</td>
                                        <td>
                                            <button onclick="editTask(${tache.id})" class="btn btn-sm btn-edit me-1">
                                                <i class="bi bi-pencil"></i>
                                            </button>
                                            <a href="<c:url value='/tache?action=delete&id=${tache.id}'/>" class="btn btn-sm btn-delete" onclick="return confirm('tes-vous sr de vouloir supprimer cette tche ?');">
                                                <i class="bi bi-trash"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            
            <!-- Pagination -->
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center mt-4">
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                            <a class="page-link" href="<c:url value='/tache?action=list&page=${i}'/>">${i}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </div>
    </div>
</div>

<!-- Create Task Modal -->
<div class="modal fade" id="taskModal" tabindex="-1" aria-labelledby="taskModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="taskModalLabel">Crer une nouvelle tche</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<c:url value='/tache?action=add'/>" method="post">
                    <div class="mb-3">
                        <label for="titre" class="form-label">Titre</label>
                        <input type="text" class="form-control" id="titre" name="titre" required>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <textarea class="form-control" id="description" name="description" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="priorite" class="form-label">Priorit</label>
                        <select class="form-select" id="priorite" name="priorite" required>
                            <option value="HAUTE">Haute</option>
                            <option value="MOYENNE">Moyenne</option>
                            <option value="BASSE">Basse</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="statut" class="form-label">Statut</label>
                        <select class="form-select" id="statut" name="statut" required>
                            <option value="ENCOURS">En cours</option>
                            <option value="TERMINEE">Termine</option>
                            <option value="ANNULEE">Annule</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="dateCreation" class="form-label">Date de cration</label>
                        <input type="date" class="form-control" id="dateCreation" name="dateCreation" required>
                    </div>
                    <div class="mb-3">
                        <label for="dateEcheance" class="form-label">Date d'chance</label>
                        <input type="date" class="form-control" id="dateEcheance" name="dateEcheance" required>
                    </div>
                    <div class="mb-3">
                        <label for="projetId" class="form-label">Projet</label>
                        <select class="form-select" id="projetId" name="projetId" required>
                            <c:forEach var="projet" items="${projets}">
                                <option value="${projet.id}">${projet.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="membreId" class="form-label">Membre assign</label>
                        <select class="form-select" id="membreId" name="membreId" required>
                            <c:forEach var="membre" items="${members}">
                                <option value="${membre.id}">${membre.nom} ${membre.prenom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Crer</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Edit Task Modal -->
<div class="modal fade" id="editTaskModal" tabindex="-1" aria-labelledby="editTaskModalLabel" aria-hidden="true">
  <div class="modal-dialog">
      <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="editTaskModalLabel">Modifier la tche</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form action="<c:url value='/tache?action=edit'/>" method="post">
                  <input type="hidden" id="editId" name="id">
                  <div class="mb-3">
                      <label for="editTitre" class="form-label">Titre</label>
                      <input type="text" class="form-control" id="editTitre" name="titre" required>
                  </div>
                  <div class="mb-3">
                      <label for="editDescription" class="form-label">Description</label>
                      <textarea class="form-control" id="editDescription" name="description" required></textarea>
                  </div>
                  <div class="mb-3">
                      <label for="editPriorite" class="form-label">Priorit</label>
                      <select class="form-select" id="editPriorite" name="priorite" required>
                          <option value="HAUTE">Haute</option>
                          <option value="MOYENNE">Moyenne</option>
                          <option value="BASSE">Basse</option>
                      </select>
                  </div>
                  <div class="mb-3">
                      <label for="editStatut" class="form-label">Statut</label>
                      <select class="form-select" id="editStatut" name="statut" required>
                          <option value="ENCOURS">En cours</option>
                          <option value="TERMINEE">Termine</option>
                          <option value="ANNULEE">Annule</option>
                      </select>
                  </div>
                  <div class="mb-3">
                      <label for="editDateCreation" class="form-label">Date de cration</label>
                      <input type="date" class="form-control" id="editDateCreation" name="dateCreation" required>
                  </div>
                  <div class="mb-3">
                      <label for="editDateEcheance" class="form-label">Date d'chance</label>
                      <input type="date" class="form-control" id="editDateEcheance" name="dateEcheance" required>
                  </div>
                  <div class="mb-3">
                      <label for="editProjetId" class="form-label">Projet</label>
                      <select class="form-select" id="editProjetId" name="projetId" required>
                          <c:forEach var="projet" items="${projets}">
                              <option value="${projet.id}">${projet.nom}</option>
                          </c:forEach>
                      </select>
                  </div>
                  <div class="mb-3">
                      <label for="editMembreId" class="form-label">Membre assign</label>
                      <select class="form-select" id="editMembreId" name="membreId" required>
                          <c:forEach var="membre" items="${members}">
                              <option value="${membre.id}">${membre.nom} ${membre.prenom}</option>
                          </c:forEach>
                      </select>
                  </div>
                  <button type="submit" class="btn btn-primary">Modifier</button>
              </form>
          </div>
      </div>
  </div>
</div>
<!-- Error message display -->
<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger" role="alert">
        ${errorMessage}
    </div>
</c:if>

<script>
    function editTask(taskId) {
        fetch(`${pageContext.request.contextPath}/tache?action=get&id=${taskId}`)
            .then(response => response.json())
            .then(task => {
                document.getElementById('editId').value = task.id;
                document.getElementById('editTitre').value = task.titre;
                document.getElementById('editDescription').value = task.description;
                document.getElementById('editPriorite').value = task.priorite;
                document.getElementById('editStatut').value = task.statut;
                document.getElementById('editDateCreation').value = task.dateCreation;
                document.getElementById('editDateEcheance').value = task.dateEcheance;
                document.getElementById('editProjetId').value = task.projet.id;
                document.getElementById('editMembreId').value = task.membre.id;
    
                var editModal = new bootstrap.Modal(document.getElementById('editTaskModal'));
                editModal.show();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Une erreur s\'est produite lors de la r�cup�ration des donn�es de la tache.');
            });
    }
    </script>