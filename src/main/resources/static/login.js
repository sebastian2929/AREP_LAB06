async function checkSession() {
    const response = await fetch('/auth/checkSession', {
        method: 'GET',
        credentials: 'include'
    });
    if (response.status === 200) {
        window.location.href = "index.html"; // Redirige si ya está autenticado
    }
}

// Llamar a la función al cargar
checkSession();

document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const credentials = { username, password };

    try {
        const response = await fetch('/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials),
            credentials: 'include' // Incluir cookies para manejar la sesión
        });

        if (response.status === 200) {
            // Login exitoso, redirigir al index
            window.location.href = "index.html";
        } else {
            // Mostrar mensaje de error
            alert("Usuario o contraseña incorrectos");
        }
    } catch (error) {
        console.error('Error al intentar iniciar sesión:', error);
    }
});
