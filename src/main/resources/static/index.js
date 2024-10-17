const propertyList = document.getElementById('propertyList');
const propertyForm = document.getElementById('propertyForm');
const apiUrl = '/properties';

async function checkSession() {
    const response = await fetch('/auth/checkSession', {
        method: 'GET',
        credentials: 'include'
    });
    if (response.status !== 200) {
        window.location.href = "login.html"; // Redirige si no está autenticado
    }
}

async function fetchProperties() {
    const response = await fetch(apiUrl);
    const properties = await response.json();
    displayProperties(properties);
}

function displayProperties(properties) {
    propertyList.innerHTML = '';
    properties.forEach(property => {
        const li = document.createElement('li');
        li.classList.add('list-group-item', 'd-flex', 'justify-content-between', 'align-items-center');
        li.textContent = `${property.address} - $${property.price} - ${property.size} m² - ${property.description}`;

        // Contenedor para los botones
        const buttonContainer = document.createElement('div');

        // Botón de actualización
        const updateButton = document.createElement('button');
        updateButton.classList.add('btn', 'btn-warning', 'btn-sm', 'mr-2');
        updateButton.textContent = 'Actualizar';
        updateButton.onclick = () => updateProperty(property);

        // Botón de borrado
        const deleteButton = document.createElement('button');
        deleteButton.classList.add('btn', 'btn-danger', 'btn-sm');
        deleteButton.textContent = 'Borrar';
        deleteButton.onclick = () => deleteProperty(property.id);

        // Añadir botones al contenedor
        buttonContainer.appendChild(updateButton);
        buttonContainer.appendChild(deleteButton);

        // Añadir el contenedor de botones a la lista
        li.appendChild(buttonContainer);
        propertyList.appendChild(li);
    });
}

propertyForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const newProperty = {
        address: document.getElementById('address').value,
        price: parseFloat(document.getElementById('price').value),
        size: parseInt(document.getElementById('size').value),
        description: document.getElementById('description').value
    };

    await fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newProperty)
    });

    propertyForm.reset();
    fetchProperties();
});

async function updateProperty(property) {
    const newAddress = prompt("Nueva Dirección:", property.address);
    const newPrice = prompt("Nuevo Precio:", property.price);
    const newSize = prompt("Nuevo Tamaño (m²):", property.size);
    const newDescription = prompt("Nueva Descripción:", property.description);

    if (newAddress && newPrice && newSize && newDescription) {
        const updatedProperty = {
            address: newAddress,
            price: parseFloat(newPrice),
            size: parseInt(newSize),
            description: newDescription
        };

        await fetch(`${apiUrl}/${property.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedProperty)
        });

        fetchProperties();
    }
}

async function deleteProperty(id) {
    if (confirm("¿Estás seguro de que deseas borrar esta propiedad?")) {
        await fetch(`${apiUrl}/${id}`, {
            method: 'DELETE'
        });
        fetchProperties();
    }
}

async function logoutUser() {
    await fetch('/auth/logout', {
        method: 'POST',
        credentials: 'include'
    });
    window.location.href = "login.html"; // Redirige al login
}

// Agregar un evento al botón de cerrar sesión
document.getElementById('logoutButton').addEventListener('click', logoutUser);

// Verifica la sesión al cargar
checkSession();
fetchProperties(); // Cargar propiedades al inicio
