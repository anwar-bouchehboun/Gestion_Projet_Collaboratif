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
<div class="container mt-5">
  <h2 class="mb-4">Liste des Membres</h2>
  <button class="btn btn-primary mb-3" onclick="showAddMemberModal()">Ajouter un Membre</button>

  <table class="table table-striped">
    <thead>
      <tr>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Email</th>
        <th>Rôle</th>
        <th>Équipe</th>
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
          <td>${member.equipe.nom}</td>
          <td>
            <button class="btn btn-sm btn-primary" onclick="editMember(${member.id}, '${member.nom}', '${member.prenom}', '${member.email}', ${member.equipe_id}, '${member.role}')">Modifier</button>
            <button class="btn btn-sm btn-danger" onclick="deleteMember(${member.id})">Supprimer</button>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <!-- Pagination -->
  <nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
      <c:forEach begin="1" end="${totalPages}" var="i">
        <li class="page-item ${currentPage == i ? 'active' : ''}">
          <a class="page-link" href="${pageContext.request.contextPath}/member?action=list&page=${i}">${i}</a>
        </li>
      </c:forEach>
    </ul>
  </nav>
</div>

<!-- Modal for Add/Edit Member -->
<div class="modal fade" id="memberModal" tabindex="-1" aria-labelledby="memberModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="memberModalLabel">Ajouter/Modifier un Membre</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="memberForm" action="${pageContext.request.contextPath}/member" method="post">
          <input type="hidden" id="formAction" name="action" value="add">
          <input type="hidden" id="memberId" name="id" value="">
          <div class="mb-3">
            <label for="nom" class="form-label">Nom</label>
            <input type="text" class="form-control" id="nom" name="nom" required>
          </div>
          <div class="mb-3">
            <label for="prenom" class="form-label">Prenom</label>
            <input type="text" class="form-control" id="prenom" name="prenom" required>
          </div>
          <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
          </div>
          <div class="mb-3">
            <label for="equipe" class="form-label">E�quipe</label>
            <select class="form-select" id="equipe" name="equipeId" required>
              <c:forEach items="${equipes}" var="equipe">
                <option value="${equipe.id}">${equipe.nom}</option>
              </c:forEach>
            </select>
          </div>
          <div class="mb-3">
            <label for="role" class="form-label">Role</label>
            <select class="form-select" id="role" name="role" required>
              <c:forEach items="${roles}" var="role">
                <option value="${role}">${role}</option>
              </c:forEach>
            </select>
          </div>
          <button type="submit" class="btn btn-primary">Enregistrer</button>
        </form>
      </div>
    </div>
  </div>
</div>

<script>
  function showAddMemberModal() {
    document.getElementById('memberModalLabel').textContent = 'Ajouter un Membre';
    document.getElementById('formAction').value = 'add';
    document.getElementById('memberId').value = '';
    document.getElementById('memberForm').reset();
    var modal = new bootstrap.Modal(document.getElementById('memberModal'));
    modal.show();
  }

  function editMember(id, nom, prenom, email, equipeId, role) {
    document.getElementById('memberModalLabel').textContent = 'Modifier un Membre';
    document.getElementById('formAction').value = 'edit';
    document.getElementById('memberId').value = id;
    document.getElementById('nom').value = nom;
    document.getElementById('prenom').value = prenom;
    document.getElementById('email').value = email;
    document.getElementById('equipe').value = equipeId;
    document.getElementById('role').value = role;
    var modal = new bootstrap.Modal(document.getElementById('memberModal'));
    modal.show();
  }

  function deleteMember(id) {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce membre ?')) {
      window.location.href = '${pageContext.request.contextPath}/member?action=delete&id=' + id;
    }
  }
</script>