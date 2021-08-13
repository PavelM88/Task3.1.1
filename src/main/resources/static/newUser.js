function newUser() {
    let role = selectRole(Array.from(document.getElementById("newRoles").selectedOptions).map(r => r.value));

    fetch('/api/add', {
        method: 'POST',
        body: JSON.stringify({
            name: window.formNewUser.newName.value,
            lastName: window.formNewUser.newLastName.value,
            age: window.formNewUser.newAge.value,
            userName: window.formNewUser.newUsername.value,
            password: window.formNewUser.newPassword.value,
            roles: role
        }),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
        .then(response => response.json())
        .then(user => {
            $('#tBody tr:last').after(`<tr id="${user.id}"> 
                <td> ${user.id} </td>
                <td>  ${window.formNewUser.newName.value} </td>
                <td>  ${window.formNewUser.newLastName.value} </td>
                <td>  ${window.formNewUser.newAge.value} </td>
                <td>  ${window.formNewUser.newUsername.value} </td>
                <td>  ${window.formNewUser.newRoles.value} </td>
                <td> <button type="button" onclick="modalEdit(${user.id})" class="btn btn-primary">Edit</button> </td>
                <td> <button type="button" onclick="modalDelete(${user.id})" class="btn btn-danger">Delete</button> </td>
                </tr>`);

            window.formNewUser.newName.value = "";
            window.formNewUser.newLastName.value = "";
            window.formNewUser.newAge.value = "";
            window.formNewUser.newUsername.value = "";
            window.formNewUser.newPassword.value = "";
            window.formNewUser.newRoles.value = "";

            $('#NewUserCreated').modal();
        });
}