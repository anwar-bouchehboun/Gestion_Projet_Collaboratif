<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<div
  class="modal fade"
  id="taskModal"
  tabindex="-1"
  aria-labelledby="taskModalLabel"
  aria-hidden="true"
>
  <div class="modal-dialog modal-wide">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="taskModalLabel">
          Créer/Modifier une tâche
        </h5>
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="modal"
          aria-label="Close"
        ></button>
      </div>
      <div class="modal-body">
        <form id="taskForm">
          <div class="row">
            <div class="col-md-4 mb-3">
              <label for="taskTitle" class="form-label">Titre</label>
              <input type="text" class="form-control" id="taskTitle" required />
            </div>
            <div class="col-md-4 mb-3">
              <label for="taskTeam" class="form-label">Équipe</label>
              <select
                class="form-select"
                id="taskTeam"
                required
                onchange="updateTeamMembers()"
              >
                <option value="">Sélectionner une équipe</option>
                <!-- Team options will be dynamically added here -->
              </select>
            </div>
            <div class="col-md-4 mb-3">
              <label for="taskMember" class="form-label"
                >Membre de l'équipe</label
              >
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
              <label for="taskDueDate" class="form-label"
                >Date d'échéance</label
              >
              <input
                type="date"
                class="form-control"
                id="taskDueDate"
                required
              />
            </div>
          </div>
          <div class="mb-3">
            <label for="taskDescription" class="form-label">Description</label>
            <textarea
              class="form-control"
              id="taskDescription"
              rows="4"
              required
            ></textarea>
          </div>
          <input type="hidden" id="taskId" />
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
          Annuler
        </button>
        <button type="button" class="btn btn-primary" onclick="saveTask()">
          Enregistrer
        </button>
      </div>
    </div>
  </div>
</div>

<!-- Add this modal for delete confirmation -->
<div
  class="modal fade"
  id="deleteConfirmModal"
  tabindex="-1"
  aria-labelledby="deleteConfirmModalLabel"
  aria-hidden="true"
>
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="deleteConfirmModalLabel">
          Confirmer la suppression
        </h5>
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="modal"
          aria-label="Close"
        ></button>
      </div>
      <div class="modal-body">
        Êtes-vous sûr de vouloir supprimer cette tâche ?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
          Annuler
        </button>
        <button type="button" class="btn btn-danger" onclick="confirmDelete()">
          Supprimer
        </button>
      </div>
    </div>
  </div>
</div>

<script>
  let tasks = [
    {
      id: 1,
      title: "Développement Frontend",
      description: "Construire le frontend avec Bootstrap",
      priority: "Haute",
      status: "En cours",
      creationDate: "2024-09-10",
      dueDate: "2024-10-01",
    },
    {
      id: 2,
      title: "Implémentation des API",
      description: "Créer et intégrer les API REST",
      priority: "Moyenne",
      status: "À faire",
      creationDate: "2024-09-12",
      dueDate: "2024-10-05",
    },
    {
      id: 3,
      title: "Test Unitaire",
      description: "Tester les fonctionnalités avec JUnit",
      priority: "Basse",
      status: "Terminée",
      creationDate: "2024-09-08",
      dueDate: "2024-09-30",
    },
  ];

  let taskToDelete = null;

  $(document).ready(function () {
    loadTasks();

    $("#search, #priorityFilter, #statusFilter").on(
      "input change",
      function () {
        loadTasks();
      }
    );

    $('.btn-primary[data-bs-target="#taskModal"]').click(function () {
      $("#taskId").val("");
      $("#taskForm")[0].reset();
      $("#taskModalLabel").text("Créer une nouvelle tâche");
    });
  });

  function loadTasks() {
    const taskTableBody = document.getElementById("taskTableBody");
    taskTableBody.innerHTML = "";
    tasks.forEach((task) => {
      const row = `
                <tr>
                  <td>${task.title}</td>
                  <td>${task.description}</td>
                  <td>
                    <div class="dropdown">
                      <button class="btn btn-sm dropdown-toggle ${getPriorityClass(
                        task.priority
                      )}" type="button" id="priorityDropdown${
        task.id
      }" data-bs-toggle="dropdown" aria-expanded="false">
                        ${task.priority}
                      </button>
                      <ul class="dropdown-menu" aria-labelledby="priorityDropdown${
                        task.id
                      }">
                        <li><a class="dropdown-item" href="#" onclick="updatePriority(${
                          task.id
                        }, 'Haute')">Haute</a></li>
                        <li><a class="dropdown-item" href="#" onclick="updatePriority(${
                          task.id
                        }, 'Moyenne')">Moyenne</a></li>
                        <li><a class="dropdown-item" href="#" onclick="updatePriority(${
                          task.id
                        }, 'Basse')">Basse</a></li>
                      </ul>
                    </div>
                  </td>
                  <td>
                    <div class="dropdown">
                      <button class="btn btn-sm dropdown-toggle ${getStatusClass(
                        task.status
                      )}" type="button" id="statusDropdown${
        task.id
      }" data-bs-toggle="dropdown" aria-expanded="false">
                        ${task.status}
                      </button>
                      <ul class="dropdown-menu" aria-labelledby="statusDropdown${
                        task.id
                      }">
                        <li><a class="dropdown-item" href="#" onclick="updateStatus(${
                          task.id
                        }, 'À faire')">À faire</a></li>
                        <li><a class="dropdown-item" href="#" onclick="updateStatus(${
                          task.id
                        }, 'En cours')">En cours</a></li>
                        <li><a class="dropdown-item" href="#" onclick="updateStatus(${
                          task.id
                        }, 'Terminée')">Terminée</a></li>
                      </ul>
                    </div>
                  </td>
                  <td>${task.creationDate}</td>
                  <td>${task.dueDate}</td>
                  <td>
                    <button class="btn btn-icon btn-edit me-1" onclick="editTask(${
                      task.id
                    })" title="Modifier">
                      <i class="bi bi-pencil"></i>
                    </button>
                    <button class="btn btn-icon btn-delete" onclick="deleteTask(${
                      task.id
                    })" title="Supprimer">
                      <i class="bi bi-trash"></i>
                    </button>
                  </td>
                </tr>
              `;
      taskTableBody.innerHTML += row;
    });
  }

  function getPriorityClass(priority) {
    switch (priority) {
      case "Haute":
        return "text-danger";
      case "Moyenne":
        return "text-warning";
      case "Basse":
        return "text-success";
      default:
        return "text-secondary";
    }
  }

  function getStatusClass(status) {
    switch (status) {
      case "À faire":
        return "text-secondary";
      case "En cours":
        return "text-primary";
      case "Terminée":
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
    const task = tasks.find((t) => t.id === taskId);
    if (task) {
      $("#taskId").val(task.id);
      $("#taskTitle").val(task.title);
      $("#taskDescription").val(task.description);
      $("#taskPriority").val(task.priority);
      $("#taskStatus").val(task.status);
      $("#taskDueDate").val(task.dueDate);
      $("#taskModalLabel").text("Modifier une tâche");
      $("#taskModal").modal("show");
    }
  }

  function deleteTask(taskId) {
    taskToDelete = taskId;
    $("#deleteConfirmModal").modal("show");
  }

  function confirmDelete() {
    if (taskToDelete !== null) {
      tasks = tasks.filter((t) => t.id !== taskToDelete);
      loadTasks();
      $("#deleteConfirmModal").modal("hide");
      taskToDelete = null;
    }
  }

  function saveTask() {
    const taskId = $("#taskId").val();
    const newTask = {
      id: taskId ? parseInt(taskId) : Date.now(),
      image: "https://via.placeholder.com/50",
      title: $("#taskTitle").val(),
      description: $("#taskDescription").val(),
      priority: $("#taskPriority").val(),
      status: $("#taskStatus").val(),
      creationDate: taskId
        ? tasks.find((t) => t.id === parseInt(taskId)).creationDate
        : new Date().toISOString().split("T")[0],
      dueDate: $("#taskDueDate").val(),
    };

    if (taskId) {
      const index = tasks.findIndex((t) => t.id === parseInt(taskId));
      tasks[index] = newTask;
    } else {
      tasks.push(newTask);
    }

    loadTasks();
    $("#taskModal").modal("hide");
    $("#taskForm")[0].reset();
  }
</script>
