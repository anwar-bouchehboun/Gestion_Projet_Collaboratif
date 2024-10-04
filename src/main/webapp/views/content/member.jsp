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
        <h2 class="text-center mb-4">Gestion des Membres</h2>

        <div class="text-end mb-3">
            <button class="btn btn-primary" onclick="showAddMemberModal()">
                <i class="bi bi-plus-circle"></i> Ajouter un nouveau membre
            </button>
        </div>

        <div class="card">
            <div class="card-header">Liste des Membres</div>
            <div class="card-body p-0">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Nom</th>
                                <th scope="col">Prenom</th>
                                <th scope="col">Email</th>
                                <th scope="col">Role</th>
                                <th scope="col">Equipe</th>
                                <th scope="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty members}">
                                    <c:forEach var="member" items="${members}">
                                        <tr>
                                            <td>${member.nom}</td>
                                            <td>${member.prenom}</td>
                                            <td>${member.email}</td>
                                            <td>${member.role}</td>
                                            <td>${member.equipe.nom}</td>
                                            <td>
                                                <button class="btn btn-icon btn-edit me-1" onclick="editMember(${member.id}, '${member.nom}', '${member.prenom}', '${member.email}', '${member.equipe.id}', '${member.role}')" title="Modifier">
                                                    <i class="bi bi-pencil"></i>
                                                </button>
                                                <button class="btn btn-icon btn-delete" onclick="deleteMember(${member.id})" title="Supprimer">
                                                    <i class="bi bi-trash"></i>
                                                </button>
                                                <button class="btn btn-icon btn-info" onclick="viewMemberTasks(${member.id})" title="View Tasks">
                                                    <i class="bi bi-list-task"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="6" class="text-center">Aucun membre trouvé</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center mt-4">
                <c:if test="${totalPages > 1}">
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                            <a class="page-link" href="${pageContext.request.contextPath}/member?action=list&page=${i}">${i}</a>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </nav>
    </div>

    <!-- Member Modal -->
    <div class="modal fade" id="memberModal" tabindex="-1" aria-labelledby="memberModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-wide">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="memberModalLabel">Ajouter/Modifier un Membre</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="memberForm" action="${pageContext.request.contextPath}/member" method="post">
                        <input type="hidden" id="action" name="action" value="add">
                        <input type="hidden" id="memberId" name="id">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="nom" class="form-label">Nom</label>
                                <input type="text" class="form-control" id="nom" name="nom" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="prenom" class="form-label">Prenom</label>
                                <input type="text" class="form-control" id="prenom" name="prenom" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="role" class="form-label">Role</label>
                                <select class="form-select" id="role" name="role" required>
                                    <c:forEach items="${roles}" var="role">
                                        <option value="${role}">${role}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="equipeId" class="form-label">equipe</label>
                            <select class="form-select" id="equipeId" name="equipeId" required>
                                <c:forEach items="${equipes}" var="equipe">
                                    <option value="${equipe.id}">${equipe.nom}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                    <button type="button" class="btn btn-primary" onclick="saveMember()">Enregistrer</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Delete Confirmation Modal -->
    <div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteConfirmModalLabel">Confirmer la suppression</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Êtes-vous sûr de vouloir supprimer ce membre ?
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
        let memberToDelete = null;

        function showAddMemberModal() {
            document.getElementById('memberModalLabel').textContent = 'Ajouter un Membre';
            document.getElementById('action').value = 'add';
            document.getElementById('memberId').value = '';
            document.getElementById('memberForm').reset();
            new bootstrap.Modal(document.getElementById('memberModal')).show();
        }

        function editMember(id, nom, prenom, email, equipeId, role) {
            document.getElementById('memberModalLabel').textContent = 'Modifier un Membre';
            document.getElementById('action').value = 'edit';
            document.getElementById('memberId').value = id;
            document.getElementById('nom').value = nom;
            document.getElementById('prenom').value = prenom;
            document.getElementById('email').value = email;
            document.getElementById('equipeId').value = equipeId;
            document.getElementById('role').value = role;
            new bootstrap.Modal(document.getElementById('memberModal')).show();
        }

        function deleteMember(id) {
            memberToDelete = id;
            new bootstrap.Modal(document.getElementById('deleteConfirmModal')).show();
        }

        function confirmDelete() {
            if (memberToDelete !== null) {
                window.location.href = '${pageContext.request.contextPath}/member?action=delete&id=' + memberToDelete;
            }
        }

        function saveMember() {
            document.getElementById('memberForm').submit();
        }
        function viewMemberTasks(memberId) {
            window.location.href = '${pageContext.request.contextPath}/member-tasks?memberId=' + memberId;
        }
    </script>
    </script>
</body>
</html>