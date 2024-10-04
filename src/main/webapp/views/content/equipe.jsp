<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Équipes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" rel="stylesheet">
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
</head>
<body>
    <div class="container">
        <h2 class="text-center mb-4">Gestion des Équipes</h2>

        <!-- Create New Team Form -->
        <div class="mb-4">
            <form action="${pageContext.request.contextPath}/equipe" method="POST">
                <input type="hidden" name="action" value="add" />
                <div class="d-flex flex-column flex-md-row mb-3">
                    <div class="col-auto me-md-2 mb-2 mb-md-0">
                        <input type="text" class="form-control" placeholder="Entrer Nom Équipe" name="teamName" required />
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary mb-3">Ajouter Équipe</button>
                    </div>
                </div>
            </form>
        </div>

        <!-- Success and Error Messages -->
        <c:if test="${not empty sessionScope.successMessage}">
            <div class="alert alert-success" role="alert">
                ${sessionScope.successMessage}
            </div>
            <c:remove var="successMessage" scope="session" />
        </c:if>

        <c:if test="${not empty sessionScope.errorMessage}">
            <div class="alert alert-danger" role="alert">
                ${sessionScope.errorMessage}
            </div>
            <c:remove var="errorMessage" scope="session" />
        </c:if>

        <!-- Equipe List -->
        <div class="card">
            <div class="card-header">Liste des Équipes</div>
            <div class="card-body p-0">
                <div class="table-responsive">
                    <table class="table table-light">
                        <thead>
                            <tr>
                                <th scope="col">Nom</th>
                                <th scope="col" class="text-center">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty equipes}">
                                    <c:forEach var="equipe" items="${equipes}">
                                        <tr>
                                            <td>${equipe.nom}</td>
                                            <td class="text-center">
                                                <button class="btn btn-icon btn-edit me-1" title="Modifier" data-bs-toggle="modal" data-bs-target="#updateModal" data-id="${equipe.id}" data-name="${equipe.nom}">
                                                    <i class="bi bi-pencil"></i>
                                                </button>
                                                <form action="${pageContext.request.contextPath}/equipe" method="POST" style="display:inline;">
                                                    <input type="hidden" name="action" value="delete" />
                                                    <input type="hidden" name="id" value="${equipe.id}" />
                                                    <button type="submit" class="btn btn-icon btn-delete" title="Supprimer">
                                                        <i class="bi bi-trash"></i>
                                                    </button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="2" class="text-center">Aucune équipe trouvée.</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        
        <!-- Pagination -->
        <c:if test="${totalPages > 1}">
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center mt-4">
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                            <a class="page-link" href="${pageContext.request.contextPath}/equipe?action=list&page=${i}">${i}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </c:if>
    </div>

    <!-- Update Modal -->
    <div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateModalLabel">Modifier Équipe</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="updateForm" action="${pageContext.request.contextPath}/equipe" method="POST">
                        <input type="hidden" name="action" value="updateTeam" />
                        <input type="hidden" name="id" id="updateId" value="" />
                        <div class="mb-3">
                            <label for="newName" class="form-label">Nouveau Nom:</label>
                            <input type="text" class="form-control" id="newName" name="newName" required />
                        </div>
                        <button type="submit" class="btn btn-primary">Modifier</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
    <script>
        $('#updateModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var id = button.data('id');
            var teamName = button.data('name');
            var modal = $(this);
            modal.find('#updateId').val(id);
            modal.find('#newName').val(teamName);
        });
    </script>
</body>
</html>