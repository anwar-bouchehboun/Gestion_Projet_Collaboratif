<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <style>
      body {
        background-color: #f0f2f5;
        font-family: "Poppins", sans-serif;
        color: #333;
      }

      .container {
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
        display:flex;
        justify-content:space-between;
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

      .img-thumbnail {
        width: 50px;
        height: 50px;
        object-fit: cover;
        border-radius: 50%;
        border: 2px solid #fff;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      }

      .search-bar {
        margin-bottom: 20px;
      }

      .btn-icon {
        width: 36px;
        height: 36px;
        padding: 0;
        border-radius: 50%;
        display: inline-flex;
        align-items: center;
        justify-content: center;
        transition: all 0.2s;
        border: none;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      }

      .btn-icon:hover {
        transform: translateY(-2px);
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
      }

      .btn-icon i {
        font-size: 1rem;
      }

      .btn-edit {
        background-color: #4a90e2;
        color: white;
      }

      .btn-delete {
        background-color: #e74c3c;
        color: white;
      }

  

      .status-todo {
        background-color: #fff5f5;
      }
      .status-inprogress {
        background-color: #fff9db;
      }
      .status-done {
        background-color: #f1f8e9;
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
        font-weight: 500;
        transition: all 0.2s;
      }

      .page-link:hover,
      .page-item.active .page-link {
        background-color: #4a90e2;
        color: white;
        box-shadow: 0 2px 5px rgba(74, 144, 226, 0.3);
      }

      .dropdown-toggle {
        border: none;
        background: none;
        font-weight: 500;
        padding: 0.375rem 0.75rem;
        border-radius: 20px;
        transition: all 0.2s;
      }

      .dropdown-toggle:hover,
      .dropdown-toggle:focus {
        background-color: rgba(0, 0, 0, 0.05);
      }

      .dropdown-menu {
        border: none;
        box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
        border-radius: 10px;
      }

      .dropdown-item {
        padding: 0.5rem 1rem;
        font-size: 0.9rem;
        transition: all 0.2s;
      }

      .dropdown-item:hover {
        background-color: #f8f9fa;
        color: #4a90e2;
      }

      @media (max-width: 768px) {
        .table-responsive {
          font-size: 0.85rem;
        }
        .btn-icon {
          width: 32px;
          height: 32px;
        }
      }

      .modal-dialog.modal-wide {
        max-width: 95%;
        width: 95%;
      }

      @media (min-width: 1200px) {
        .modal-dialog.modal-wide {
          max-width: 1140px;
        }
      }
    </style>
  </head>

  <div class="container">
        <h2 class="text-center mb-4">Gestion des Projets</h2>

        

        <div class="text-end mb-3">
            <button class="btn btn-primary" onclick="showAddProjetModal()">
                <i class="bi bi-plus-circle"></i> Créer un nouveau Projet
            </button>
        </div>

        <div class="card">
            <div class="card-header flex "><h4>Liste des Projets</h4>
            
            <div class="mb-0">
            <form action="">
                <div class="d-flex  flex-md-row ">
                   <div class="col-auto me-md-2 mb-2 mb-md-0">
                         <input type="text" class="form-control " id="" placeholder="Entre Nom Projet">
                     </div>
                 <div class="col-auto me-md-2">
                 <button type="submit" class="btn btn-primary mb-3">chercher</button>
                    </div>
                </div>
            </form>
        </div>
            </div>
            
            
            
            
            <div class="card-body p-0">
                <div class="table-responsive">
                    <table class="table table-light">
                        <thead>
                            <tr>
                                <th scope="col">Nom</th>
                                <th scope="col">Description</th>
                                <th scope="col">Statut</th>
                                <th scope="col">Date de creation</th>
                                <th scope="col">Date d'echance</th>
                                <th scope="col">Membres</th>
                                <th scope="col">Taches</th>                                
                                <th scope="col">Actions</th>
                            </tr>
                        </thead>
<tbody>
       
      
                    <c:forEach var='projet' items="${projets}">
    <tr>
        <td>${projet.nom}</td>
        <td>${fn:substring(projet.description, 0, 50)}${fn:length(projet.description) > 50 ? '...' : ''}</td>
        <td>${projet.statut}</td>
        <td>${projet.dateDebut != null ? projet.dateDebut : '-'}</td>
        <td>${projet.dateFin != null ? projet.dateFin : '-'}</td>
        <td>${projet.equipe != null ? projet.equipe.nom : '-'}</td>
        <td>${projet.totalTaches}</td>
        <td>${projet.totalMembres}</td>
        <td>
            <button class="btn btn-sm btn-primary" onclick="showEditProjetModal('${projet.id}', '${projet.nom}', '${projet.description}', '${projet.equipe.id}', '${projet.statut}', '${projet.dateDebut}', '${projet.dateFin}')">
                <i class="bi bi-pencil"></i>
            </button>
            <button class="btn btn-sm btn-danger" onclick="confirmDelete(${projet.id})">
                <i class="bi bi-trash"></i>
            </button>
        </td>
    </tr>
</c:forEach>
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
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item active"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
            </ul>
        </nav>
    </div>

    <div class="modal fade" id="projetModal" tabindex="-1" aria-labelledby="projetModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="projetModalLabel">Créer/Modifier un Projet</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="projetForm" action="${pageContext.request.contextPath}/projet" method="post">
                        <input type="hidden" id="projetId" name="id">
                        <input type="hidden" id="action" name="action" value="add">
                        <div class="mb-3">
                            <label for="nom" class="form-label">Nom</label>
                            <input type="text" class="form-control" id="nom" name="nom" required>
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label">Description</label>
                            <textarea class="form-control" id="description" name="description" required></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="equipeId" class="form-label">Équipe</label>
                            <select class="form-select" id="equipeId" name="equipeId" required>
                                <c:forEach var="equipe" items="${equipes}">
                                    <option value="${equipe.id}">${equipe.nom}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="statut" class="form-label">Statut</label>
                            <select class="form-select" id="statut" name="statut" required>
                                <c:forEach var="statut" items="${statuts}">
                                    <option value="${statut}">${statut}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="dateDebut" class="form-label">Date de début</label>
                            <input type="date" class="form-control" id="dateDebut" name="dateDebut" required>
                        </div>
                        <div class="mb-3">
                            <label for="dateFin" class="form-label">Date de fin</label>
                            <input type="date" class="form-control" id="dateFin" name="dateFin" required>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                            <button type="submit" class="btn btn-primary">Enregistrer</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteConfirmModalLabel">Confirmer la suppression</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    tes-vous sr de vouloir supprimer ce Projet ?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                    <button type="button" class="btn btn-danger" onclick="confirmDelete()">Supprimer</button>
                </div>
            </div>
        </div>
    </div>

  

<script>
    function showAddProjetModal() {
        document.getElementById('projetForm').reset();
        document.getElementById('projetId').value = '';
        document.getElementById('action').value = 'add';
        document.getElementById('projetModalLabel').textContent = 'Créer un nouveau Projet';
        var projetModal = new bootstrap.Modal(document.getElementById('projetModal'));
        projetModal.show();
    }

    function showEditProjetModal(id, nom, description, equipeId, statut, dateDebut, dateFin) {
        document.getElementById('projetId').value = id;
        document.getElementById('nom').value = nom;
        document.getElementById('description').value = description;
        document.getElementById('equipeId').value = equipeId;
        document.getElementById('statut').value = statut;
        document.getElementById('dateDebut').value = dateDebut;
        document.getElementById('dateFin').value = dateFin;
        document.getElementById('action').value = 'update';
        document.getElementById('projetModalLabel').textContent = 'Modifier le Projet';
        var projetModal = new bootstrap.Modal(document.getElementById('projetModal'));
        projetModal.show();
    }

    function confirmDelete(id) {
        if (confirm('Êtes-vous sûr de vouloir supprimer ce projet ?')) {
            window.location.href = '${pageContext.request.contextPath}/projet?action=delete&id=' + id;
        }
    }
</script>