userInfoPage();

function userInfoPage() {
    let tableUser = $('#userInfo');

    fetch('/api/authority')
        .then(res => res.json())
        .then(user => {
            let htmlUserInfo = `$(
            <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.userName}</td>
                    <td>${user.roles.map(role => {
                return role.name.substring(5);
                    })}</td>
            </tr>)`;
            tableUser.append(htmlUserInfo);
        })
}