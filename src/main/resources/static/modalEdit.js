function modalEdit(id) {

    fetch('/api/user/' + id)
        .then(response => response.json())
        .then(user => {

            let adminSelect = "";
            let userSelect = "";

            for (let i = 0; i < user.roles.length; i++) {
                if (user.roles[i].name == "ADMIN") {
                    adminSelect = "selected";
                }
                if (user.roles[i].name == "USER") {
                    userSelect = "selected";
                }
            }

            let modal = document.getElementById('modalWindow');

            modal.innerHTML =`
                <div id="modalEdit"
                     class="modal fade" tabindex="-1" role="dialog"
                     aria-labelledby="TitleModalLabel" aria-hidden="true"
                     data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog modal-dialog-scrollable">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="TitleModalLabel">Edit user</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body bg-white">
                                <form id="formEditUser" style="width: 200px;"
                                       class="form-signin mx-auto font-weight-bold text-center">
                                    <p>
                                        <label>ID</label>
                                        <input class="form-control form-control" type="text"
                                               id="editID" name="id" value="${user.id}" readonly>
                                    </p>
                                    <p>
                                        <label>First name</label>
                                        <input class="form-control form-control" type="text"
                                               id="editName" value="${user.name}"
                                               placeholder="First name" required>
                                    </p>
                                    <p>
                                        <label>Last name</label>
                                        <input class="form-control form-control" type="text"
                                               id="editLastName" value="${user.lastName}"
                                               placeholder="Last name" required>
                                    </p>
                                    <p>
                                        <label>Age</label>
                                        <input class="form-control form-control" type="text"
                                               id="editAge" value="${user.age}" 
                                               placeholder="Age" required>
                                    </p>
                                    <p>
                                        <label>Username</label>
                                        <input class="form-control form-control" type="text"
                                               id="editUsername" value="${user.userName}"
                                               placeholder="Username" required>
                                    </p>
                                    <p>
                                        <label>Password</label>
                                        <input class="form-control form-control" type="password"
                                               id="editPassword" placeholder="Password">
                                    </p>
                                    <p>
                                        <label>Role</label>
                                        <select id="editRoles" name="editRoles" multiple size="2" required
                                               class="form-control form-control">
                                            <option value="ADMIN">ADMIN</option>
                                            <option value="USER">USER</option>
                                        </select>
                                    </p>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Close</button>
                                <button class="btn btn-primary" data-dismiss="modal"
                                        onclick="editUser()">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>`;

            $("#modalEdit").modal();

        });
}

