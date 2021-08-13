function listRoles(user) {
    let rolesSet = document.createElement('ul');

    for (let i = 0; i < user.roles.length; i++) {
        let role = document.createElement('li');
        role.textContent = user.roles[i].name.substring(5) + " ";
        rolesSet.appendChild(role);
    }

    return rolesSet;
}
function selectRole(role) {
    let roles = [];
    if (role.indexOf("USER") >= 0) {
        roles.push({id: 2});
    }
    if (role.indexOf("ADMIN") >= 0) {
        roles.push({id: 1});
    }
    return roles;
}