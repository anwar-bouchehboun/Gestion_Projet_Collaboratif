  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  
  <div class="container">
        <h2 class="text-center mb-4">Gestion des Projets</h2>

        

        <div class="text-end mb-3">
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#taskModal">
                <i class="bi bi-plus-circle"></i> Cr�er un nouveau Projet
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
                                <th scope="col">Date de cr�ation</th>
                                <th scope="col">Date d'�chance</th>
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
                                <td>Termin�</td>
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
                    <h5 class="modal-title" id="taskModalLabel">Cr�er/Modifier un Projet</h5>
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
                                <label for="Role" class="form-label">Statut</label>
                                <select class="form-select" id="Role" name="role" required>
                                    <option value="">S�lectionner un statut</option>
                                    <option value="Admin">En cours</option>
                                    <option value="User">Termin�e</option>
                                    <option value="Editor">Annul�</option>
                                </select>
                            </div>
                            <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="Nom" class="form-label">Date de cr�ation</label>
                                <input type="date" class="form-control" name="nom" id="Nom" required />
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="Prenom" class="form-label">Date D'�chance</label>
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
                    �tes-vous s�r de vouloir supprimer ce Projet ?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                    <button type="button" class="btn btn-danger" onclick="confirmDelete()">Supprimer</button>
                </div>
            </div>
        </div>
    </div>
