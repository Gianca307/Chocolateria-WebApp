function agregarInsumo(id, nombre, costo) {
    let tbody = document.getElementById("lista-insumos-comprados");
    let fila = document.createElement("tr");
	let index = tbody.children.length; // Obtener el índice correcto basado en la cantidad de filas
    
    fila.setAttribute("data-index", tbody.children.length); // Guardar el índice en un atributo
    
    fila.innerHTML = `
        <td>${nombre}</td>
        <td><input type="number" name="insumosComprados[${index}].cantidad" value="1" min="1" onchange="actualizarTotalCompra()" required></td>
        <td><input type="number" step="0.01" name="insumosComprados[${index}].precio" value="${costo}" min="1" onchange="actualizarTotalCompra()" required></td>
        <td>
            <input type="hidden" name="insumosComprados[${index}].insumo.id" value="${id}">
            <button type="button" class="btn btn-secondary" onclick="eliminarInsumo(this)">Eliminar</button>
        </td>
    `;
   tbody.appendChild(fila);
   actualizarNombres();
   actualizarTotalCompra();
}

function eliminarInsumo(boton) {
    let fila = boton.parentElement.parentElement;
    fila.remove();
    actualizarNombres();
	actualizarTotalCompra();
}

function actualizarNombres() {
    let filas = document.querySelectorAll("#lista-insumos-comprados tr");
    filas.forEach((fila, index) => {
        fila.setAttribute("data-index", index);
        fila.querySelectorAll("input").forEach(input => {
            let name = input.name.replace(/\[\d+\]/, `[${index}]`); // Reemplazar el índice
            input.name = name;
        });
    });
}

// 🔹 Función para actualizar el precio total de la compra
function actualizarTotalCompra() {
	let total = 0;
	let filas = document.querySelectorAll("#lista-insumos-comprados tr");

	filas.forEach(fila => {
		let cantidadInput = fila.querySelector(`input[name*=".cantidad"]`); // Selección corregida
	    let precioInput = fila.querySelector(`input[name*=".precio"]`); // Selección corregida

		let cantidad = cantidadInput ? parseFloat(cantidadInput.value) || 0 : 0;
        let precio = precioInput ? parseFloat(precioInput.value) || 0 : 0;

        total += cantidad * precio;
    });

    document.getElementById("precioCompra").value = total.toFixed(2); // Actualizar el input de precioCompra
}

actualizarNombres();