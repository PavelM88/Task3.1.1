function modalDelete(id) {

    fetch('/api/user/' + id)
        .then(response => response.json())
        .then(user => {
            let modal = document.getElementById('modalWindow');

            modal.innerHTML = `
                <div id="modalDelete" class="modal fade" tabindex="-1" role="dialog"
                    aria-labelledby="TitleModalLabel" aria-hidden="true" 
                     data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog modal-dialog-scrollable">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="TitleModalLabel">Delete user</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body bg-white">
                                <form id="formEditUser" style="width: 200px;" 
                                       class="form-signin mx-auto font-weight-bold text-center">
                                    <p>
                                        <label>ID</label>
                                        <input class="form-control form-control-sm" type="text"
                                               name="id" value="${user.id}" readonly>
                                    </p>
                                    <p>
                                        <label>First name</label>
                                        <input class="form-control form-control-sm" type="text"
                                               value="${user.name}" readonly>
                                    </p>
                                    <p>
                                        <label>Last name</label>
                                        <input class="form-control form-control-sm" type="text"
                                               value="${user.lastName}" readonly>
                                    </p>
                                    <p>
                                        <label>Age</label>
                                        <input class="form-control form-control-sm" type="number"
                                               value="${user.age}" readonly>
                                    </p>
                                    <p>
                                        <label>Username</label>
                                        <input class="form-control form-control-sm" type="text"
                                               value="${user.userName}" readonly>
                                    </p>
                                    <p>
                                        <label>Role</label>
                                        <select class="form-control form-control-sm" multiple size="2" readonly>
                                            <option value="ADMIN">ADMIN</option>
                                            <option value="USER">USER</option>
                                        </select>
                                    </p>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Close</button>
                                <button class="btn btn-danger" data-dismiss="modal"
                                        onclick="deleteUser(${user.id})">Delete</button>
                            </div> 
                        </div> 
                    </div> 
                </div>`;

            $("#modalDelete").modal();

        });
}