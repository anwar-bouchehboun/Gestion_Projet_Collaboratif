<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Gestion des Projets</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap"
      rel="stylesheet"
    />
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

  <body>
  <div class="container">
        <h2 class="text-center mb-4">Gestion des Projets</h2>

        

        <div class="text-end mb-3">
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#taskModal">
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
                                <th scope="col">Date de création</th>
                                <th scope="col">Date d'échance</th>
                                <th scope="col">Membres</th>
                                <th scope="col">Taches</th>                                
                                <th scope="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody id="taskTableBody">
                            <tr>
                                <td>Projet</td>
                                <td>projet</td>
                                <td>En cours</td>
                                <td>20/10/2020</td>
                                <td>20/10/2020</td>                         
                                <td>20</td>
                                <td>20</td>
                                <td class="d-flex flex-row gap-1">
                                             <button class="btn btn-icon btn-edit me-1"  title="Modifier">
                                                      <i class="bi bi-pencil"></i>
                                             </button>
                                                      <button class="btn btn-icon btn-delete"  title="Supprimer">
                                                       <i class="bi bi-trash"></i>
                                           </button>
                                  
                                </td>
                            </tr>
                            <tr>
                                <td>test</td>
                                <td>test</td>
                                <td>Terminé</td>
                                <td>20/10/2020</td>
                                <td>20/10/2020</td>                         
                                <td>20</td>
                                <td>20</td>
                                 <td class="d-flex flex-row gap-1">
                                      <button class="btn btn-icon btn-edit me-1" onclick="editTask(${task.id})" title="Modifier">
                                                      <i class="bi bi-pencil"></i>
                                             </button>
                                                      <button class="btn btn-icon btn-delete" onclick="deleteTask(${task.id})" title="Supprimer">
                                                       <i class="bi bi-trash"></i>
                                           </button>
                                  
                                </td>
                            </tr>
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

    <div class="modal fade" id="taskModal" tabindex="-1" aria-labelledby="taskModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="taskModalLabel">Créer/Modifier un Projet</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="Projetform">
                            <div class="mb-4">
                                <label for="Nom" class="form-label">Nom</label>
                                <input type="text" class="form-control" name="nom" id="Nom" required />
                            </div>
                    <div class="mb-4">
                            <label for="description" class="form-label">Description</label>
                            <textarea class="form-control" name="description" id="description" required></textarea>
                    </div>
                            <div class="mb-4">
                                <label for="equipe" class="form-label">équipe</label>
                                <select class="form-select" id="Role" name="role" required>
                                    <option value="">Sélectionner une équipe</option>
                                    <option value="">fvfvf</option>
                                    <option value="">fdfd</option>
                                    <option value="">aaaaa</option>
                                </select>
                            </div>
                            <div class="mb-4">
                                <label for="statut" class="form-label">Statut</label>
                                <select class="form-select" id="statut" name="statut" required>
                                    <option value="">Sélectionner un statut</option>
                                    <option value="">En cours</option>
                                    <option value="">Terminée</option>
                                    <option value="">Annulé</option>
                                </select>
                            </div>
                            
                            <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="Nom" class="form-label">Date de création</label>
                                <input type="date" class="form-control" name="nom" id="Nom" required />
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="Prenom" class="form-label">Date D'échance</label>
                                <input type="date" class="form-control" name="prenom" id="Prenom" required />
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" onclick="saveTask()">Enregistrer</button>
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
                    Êtes-vous sûr de vouloir supprimer ce Projet ?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                    <button type="button" class="btn btn-danger" onclick="confirmDelete()">Supprimer</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


</body>
</html>
