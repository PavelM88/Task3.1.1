function editUser() {

    let editRole = selectRole(Array.from(document.getElementById("editRoles").selectedOptions).map(r => r.value));
    let id = window.formEditUser.editID.value;

    fetch('/api/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: window.formEditUser.editID.value,
            name: window.formEditUser.editName.value,
            lastName: window.formEditUser.editLastName.value,
            age: window.formEditUser.editAge.value,
            userName: window.formEditUser.editUsername.value,
            password: window.formEditUser.editPassword.value,
            roles: editRole
        }),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
        .then(response => {
            $('#' + id).replaceWith(`<tr id="${id}">
                <td> ${id} </td>
                <td> ${window.formEditUser.editName.value} </td>
                <td> ${window.formEditUser.editLastName.value} </td>
                <td> ${window.formEditUser.editAge.value} </td>
                <td> ${window.formEditUser.editUsername.value} </td>
                <td> ${roleName(editRole)} </td>
                <td> <button type="button" onclick="modalEdit(${id})" class="btn btn-primary">Edit</button> </td>
                <td> <button type="button" onclick="modalDelete(${id})" class="btn btn-danger">Delete</button> </td>
                </tr>`);
            console.log(roleName(editRole));
        });
}
function roleName(editRole) {
    let roleName = "";
    for (let i = 0; i < editRole.length; i++) {
       let role = editRole[i].name + " ";
       roleName += role;
    }
    return roleName;
}