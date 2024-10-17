document.getElementById('registerForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const user = { username, password };

    try {
        const response = await fetch('/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });

        if (response.status === 200) {
            alert("Usuario registrado correctamente");
            window.location.href = "login.html";
        } else {
            const errorData = await response.json();
            alert(`Error: ${errorData}`);
        }
    } catch (error) {
        console.error('Error al intentar registrarse:', error);
    }
});
