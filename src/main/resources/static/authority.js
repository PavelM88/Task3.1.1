authorityUser();

function authorityUser() {
    let userBar = $('#userAuthor');

    fetch('/api/authority')
        .then(res => res.json())
        .then(principal => {
            let htmlPrincipal = `
                <span class="navbar-brand" href="#">
                ${principal.userName} with roles: 
                ${principal.roles.map(role => {
                return role.name.substring(5)
            }).join(" ")}
                </span> `;
            userBar.append(htmlPrincipal);
        });
}