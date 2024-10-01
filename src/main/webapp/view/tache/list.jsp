<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="fr">
      <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Gestion des Tâches - Modern Design</title>
        <!-- Bootstrap 5.3.2 CSS -->
        <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
        />
        <!-- Bootstrap Icons -->
        <link
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"
          rel="stylesheet"
        />
        <!-- Google Fonts -->
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
    
          .btn-edit:hover,
          .btn-delete:hover {
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
    
          /* Update this style for even wider modals */
          .modal-dialog.modal-wide {
            max-width: 95%;
            width: 95%;
          }
    
          /* Add this style to limit the maximum width on very large screens */
          @media (min-width: 1200px) {
            .modal-dialog.modal-wide {
              max-width: 1140px;
            }
          }
        </style>
      </head>
    
      <body>
        <div class="container">
          <h2 class="text-center mb-4">Gestion des Tâches</h2>
    
          <div class="text-end mb-3">
            <button
              class="btn btn-primary"
              data-bs-toggle="modal"
              data-bs-target="#taskModal"
            >
              <i class="bi bi-plus-circle"></i> Créer une nouvelle tâche
            </button>
          </div>
          <div class="card">
            <div class="card-header">Liste des Tâches</div>
            <div class="card-body p-0">
              <div class="table-responsive">
                <table class="table table-hover">
                  <thead>
                    <tr>
                      <th scope="col">Titre</th>
                      <th scope="col">Description</th>
                      <th scope="col">Priorité</th>
                      <th scope="col">Statut</th>
                      <th scope="col">Date de Création</th>
                      <th scope="col">Date d'Échéance</th>
                      <th scope="col">Actions</th>
                    </tr>
                  </thead>
                  <tbody id="taskTableBody">
                    <!-- Task rows will be dynamically inserted here -->
                  </tbody>
                </table>
              </div>
            </div>
          </div>
    
          <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center mt-4">
              <li class="page-item"><a class="page-link" href="#">1</a></li>
              <li class="page-item active"><a class="page-link" href="#">2</a></li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
            </ul>
          </nav>
        </div>
    
        <!-- Add this modal for creating/editing tasks -->
        <div class="modal fade" id="taskModal" tabindex="-1" aria-labelledby="taskModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-wide">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="taskModalLabel">Créer/Modifier une tâche</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <form id="taskForm">
                  <div class="row">
                    <div class="col-md-4 mb-3">
                      <label for="taskTitle" class="form-label">Titre</label>
                      <input type="text" class="form-control" id="taskTitle" required>
                    </div>
                    <div class="col-md-4 mb-3">
                      <label for="taskTeam" class="form-label">Équipe</label>
                      <select class="form-select" id="taskTeam" required onchange="updateTeamMembers()">
                        <option value="">Sélectionner une équipe</option>
                        <!-- Team options will be dynamically added here -->
                      </select>
                    </div>
                    <div class="col-md-4 mb-3">
                      <label for="taskMember" class="form-label">Membre de l'équipe</label>
                      <select class="form-select" id="taskMember" required>
                        <option value="">Sélectionner un membre</option>
                        <!-- Team member options will be dynamically added here -->
                      </select>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4 mb-3">
                      <label for="taskPriority" class="form-label">Priorité</label>
                      <select class="form-select" id="taskPriority" required>
                        <option value="Haute">Haute</option>
                        <option value="Moyenne">Moyenne</option>
                        <option value="Basse">Basse</option>
                      </select>
                    </div>
                    <div class="col-md-4 mb-3">
                      <label for="taskStatus" class="form-label">Statut</label>
                      <select class="form-select" id="taskStatus" required>
                        <option value="À faire">À faire</option>
                        <option value="En cours">En cours</option>
                        <option value="Terminée">Terminée</option>
                      </select>
                    </div>
                    <div class="col-md-4 mb-3">
                      <label for="taskDueDate" class="form-label">Date d'échéance</label>
                      <input type="date" class="form-control" id="taskDueDate" required>
                    </div>
                  </div>
                  <div class="mb-3">
                    <label for="taskDescription" class="form-label">Description</label>
                    <textarea class="form-control" id="taskDescription" rows="4" required></textarea>
                  </div>
                  <input type="hidden" id="taskId">
                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                <button type="button" class="btn btn-primary" onclick="saveTask()">Enregistrer</button>
              </div>
            </div>
          </div>
        </div>
    
        <!-- Add this modal for delete confirmation -->
        <div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="deleteConfirmModalLabel">Confirmer la suppression</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                Êtes-vous sûr de vouloir supprimer cette tâche ?
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
    
        <script>
          let currentPage = 1;
          const itemsPerPage = 10;
    
          function loadTasks() {
            $.get(`${window.location.pathname}?action=list&page=${currentPage}`, function(data) {
              const taskTableBody = $('#taskTableBody');
              taskTableBody.empty();
              data.taches.forEach((task) => {
                const row = `
                  <tr>
                    <td>${task.titre}</td>
                    <td>${task.description}</td>
                    <td>
                      <div class="dropdown">
                        <button class="btn btn-sm dropdown-toggle ${getPriorityClass(task.priorite)}" type="button" id="priorityDropdown${task.id}" data-bs-toggle="dropdown" aria-expanded="false">
                          ${task.priorite}
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="priorityDropdown${task.id}">
                          <li><a class="dropdown-item" href="#" onclick="updatePriority(${task.id}, 'HAUTE')">Haute</a></li>
                          <li><a class="dropdown-item" href="#" onclick="updatePriority(${task.id}, 'MOYENNE')">Moyenne</a></li>
                          <li><a class="dropdown-item" href="#" onclick="updatePriority(${task.id}, 'BASSE')">Basse</a></li>
                        </ul>
                      </div>
                    </td>
                    <td>
                      <div class="dropdown">
                        <button class="btn btn-sm dropdown-toggle ${getStatusClass(task.statut)}" type="button" id="statusDropdown${task.id}" data-bs-toggle="dropdown" aria-expanded="false">
                          ${task.statut}
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="statusDropdown${task.id}">
                          <li><a class="dropdown-item" href="#" onclick="updateStatus(${task.id}, 'A_FAIRE')">À faire</a></li>
                          <li><a class="dropdown-item" href="#" onclick="updateStatus(${task.id}, 'EN_COURS')">En cours</a></li>
                          <li><a class="dropdown-item" href="#" onclick="updateStatus(${task.id}, 'TERMINEE')">Terminée</a></li>
                        </ul>
                      </div>
                    </td>
                    <td>${task.dateCreation}</td>
                    <td>${task.dateEcheance}</td>
                    <td>
                      <button class="btn btn-icon btn-edit me-1" onclick="editTask(${task.id})" title="Modifier">
                        <i class="bi bi-pencil"></i>
                      </button>
                      <button class="btn btn-icon btn-delete" onclick="deleteTask(${task.id})" title="Supprimer">
                        <i class="bi bi-trash"></i>
                      </button>
                    </td>
                  </tr>
                `;
                taskTableBody.append(row);
              });
              updatePagination(data.currentPage, data.totalPages);
            });
          }
    
          let taskToDelete = null;
    
          $(document).ready(function () {
            loadTasks();
    
            $("#search, #priorityFilter, #statusFilter").on(
              "input change",
              function () {
                loadTasks();
              }
            );
    
            $('.btn-primary[data-bs-target="#taskModal"]').click(function() {
              $('#taskId').val('');
              $('#taskForm')[0].reset();
              $('#taskModalLabel').text('Créer une nouvelle tâche');
            });
          });
    
          function getPriorityClass(priority) {
            switch (priority) {
              case "HAUTE":
                return "text-danger";
              case "MOYENNE":
                return "text-warning";
              case "BASSE":
                return "text-success";
              default:
                return "text-secondary";
            }
          }
    
          function getStatusClass(status) {
            switch (status) {
              case "A_FAIRE":
                return "text-secondary";
              case "EN_COURS":
                return "text-primary";
              case "TERMINEE":
                return "text-success";
              default:
                return "text-dark";
            }
          }
    
          function updatePriority(taskId, newPriority) {
            const task = tasks.find((t) => t.id === taskId);
            if (task) {
              task.priority = newPriority;
              loadTasks();
            }
          }
    
          function updateStatus(taskId, newStatus) {
            const task = tasks.find((t) => t.id === taskId);
            if (task) {
              task.status = newStatus;
              loadTasks();
            }
          }
    
          function editTask(taskId) {
            const task = tasks.find(t => t.id === taskId);
            if (task) {
              $('#taskId').val(task.id);
              $('#taskTitle').val(task.title);
              $('#taskDescription').val(task.description);
              $('#taskPriority').val(task.priority);
              $('#taskStatus').val(task.status);
              $('#taskDueDate').val(task.dueDate);
              $('#taskModalLabel').text('Modifier une tâche');
              $('#taskModal').modal('show');
            }
          }
    
          function deleteTask(taskId) {
            taskToDelete = taskId;
            $('#deleteConfirmModal').modal('show');
          }
    
          function confirmDelete() {
            if (taskToDelete !== null) {
              tasks = tasks.filter(t => t.id !== taskToDelete);
              loadTasks();
              $('#deleteConfirmModal').modal('hide');
              taskToDelete = null;
            }
          }
    
          function saveTask() {
            const taskId = $('#taskId').val();
            const newTask = {
              id: taskId ? parseInt(taskId) : Date.now(),
              image: "https://via.placeholder.com/50",
              title: $('#taskTitle').val(),
              description: $('#taskDescription').val(),
              priority: $('#taskPriority').val(),
              status: $('#taskStatus').val(),
              creationDate: taskId ? tasks.find(t => t.id === parseInt(taskId)).creationDate : new Date().toISOString().split('T')[0],
              dueDate: $('#taskDueDate').val()
            };
    
            if (taskId) {
              const index = tasks.findIndex(t => t.id === parseInt(taskId));
              tasks[index] = newTask;
            } else {
              tasks.push(newTask);
            }
    
            loadTasks();
            $('#taskModal').modal('hide');
            $('#taskForm')[0].reset();
          }
        </script>
      </body>
    </html>