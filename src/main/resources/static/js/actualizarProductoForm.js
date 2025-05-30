function agregarInsumo(id, nombre, cantidad, costoInsumo) {
    let tbody = document.getElementById("lista-insumos-productos");
    let fila = document.createElement("tr");
	let index = tbody.children.length; // Obtener el índice correcto basado en la cantidad de filas
	let precioUnitario = (costoInsumo / cantidad).toFixed(2);
    
    fila.setAttribute("data-index", tbody.children.length); // Guardar el índice en un atributo
    
    fila.innerHTML = `
        <td>${nombre}</td>
        <td><input type="number" name="insumosProductos[${index}].cantidad" value="1" min="1" oninput="actualizarCostoPrecio()" required></td>
        <td><input type="number" step="0.01" value="${precioUnitario}" oninput="actualizarCostoPrecio()" required readonly></td>
        <td>
            <input type="hidden" name="insumosProductos[${index}].insumo.id" value="${id}">
            <button type="button" class="btn btn-secondary" onclick="eliminarInsumo(this)">Eliminar</button>
        </td>
    `;
   tbody.appendChild(fila);
   actualizarNombres();
   actualizarCostoPrecio();
}

function eliminarInsumo(boton) {
    let fila = boton.parentElement.parentElement;
    fila.remove();
    actualizarNombres();
    actualizarCostoPrecio();
}

function actualizarNombres() {
    let filas = document.querySelectorAll("#lista-insumos-productos tr");
    filas.forEach((fila, index) => {
        fila.setAttribute("data-index", index);
        fila.querySelectorAll("input").forEach(input => {
            let name = input.name.replace(/\[\d+\]/, `[${index}]`); // Reemplazar el índice
            input.name = name;
        });
    });
}

// 🔹 Función para actualizar el precio total de la compra
function actualizarCostoPrecio() {
	let total = 0;
	let filas = document.querySelectorAll("#lista-insumos-productos tr");

	filas.forEach(fila => {
		let cantidadInput = fila.querySelector(`input[name*=".cantidad"]`); // Selección corregida
		let precioInput = fila.querySelector(`td:nth-child(3) input`); // Seleccionamos el input del precio sin name

		let cantidad = cantidadInput ? parseFloat(cantidadInput.value) || 0 : 0;
        let precio = precioInput ? parseFloat(precioInput.value) || 0 : 0;

        total += cantidad * precio;
    });

    document.getElementById("costoBase").value = total.toFixed(2); // Actualizar el input de precioCompra
	
	let valorAgregado = parseFloat(document.getElementById("costoAdic").innerText);

    let sugerido = total + ((total * valorAgregado) / 100);
    document.getElementById("precioSugerido").innerText = "$ " + sugerido.toFixed(2);
}
	
actualizarNombres();