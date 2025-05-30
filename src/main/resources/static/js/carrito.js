class Producto{
	constructor(id, descripcion, img, precio, cantidad = 1){
		this.id = id;
		this.descripcion = descripcion;
		this.img = img;
		this.precio = precio;
		this.cantidad = cantidad;
	}
}

function mostrarCarrito() {
  let carrito = obtenerCarrito();
  let contenedor = document.getElementById("carrito");
  contenedor.innerHTML = "";

  if (carrito.length === 0) {
    contenedor.innerHTML = "<p class='text-center fs-2'>El carrito está vacío.</p>";
    return;
  }

  let tabla = document.createElement("table");
  tabla.className = "table";
  tabla.id = "tabla";
  
  let tHead = document.createElement("thead");
  let trHead = document.createElement("tr");
  let thTitulo = document.createElement("th");
  thTitulo.colSpan = "5";
  thTitulo.innerText = "Productos:"

  trHead.appendChild(thTitulo);
  tHead.appendChild(trHead);
  
  tabla.appendChild(tHead);
  
  let tBody = document.createElement("tbody");
  
  carrito.forEach(p => {
    let trProducto = document.createElement("tr");
	
	let tdImagen = document.createElement("td");
	tdImagen.style.textAlign = "center";
	tdImagen.style.verticalAlign = "middle";
	let imagen = document.createElement("img");
	imagen.style.display = "block";
	imagen.style.margin = "0 auto";	
	imagen.style.height = "30px";
	imagen.style.objectFit = "cover";
	imagen.src = `${p.img}`;
	
	tdImagen.appendChild(imagen);
	trProducto.appendChild(tdImagen);	
	
	let tdDescripcion = document.createElement("td");
	tdDescripcion.innerText = `${p.descripcion}`;
	
	trProducto.appendChild(tdDescripcion);
	
	/* Comienzo cantidad con botones */
	
	let tdCantidad = document.createElement("td");

	let btnRestar = document.createElement("button");
	btnRestar.innerText = "−";
	btnRestar.className = "btn btn-primary";
	btnRestar.onclick = () => modificarCantidad(p.id, -1);

	let spanCantidad = document.createElement("span");
	spanCantidad.innerText = ` x ${p.cantidad} `;

	let btnSumar = document.createElement("button");
	btnSumar.innerText = "+";
	btnSumar.className = "btn btn-primary";
	btnSumar.onclick = () => modificarCantidad(p.id, 1);

	tdCantidad.appendChild(btnRestar);
	tdCantidad.appendChild(spanCantidad);
	tdCantidad.appendChild(btnSumar);
	trProducto.appendChild(tdCantidad);
	
	/* Fin cantidad */	
	
	let tdPrecioIndividual = document.createElement("td");
	tdPrecioIndividual.innerText = `$${p.precio} c/u`;
	
	trProducto.appendChild(tdPrecioIndividual);
	
	let tdSubtotal = document.createElement("td");
	tdSubtotal.innerText = ` - Subtotal: $${(p.precio * p.cantidad).toFixed(2)}`;
	
	trProducto.appendChild(tdSubtotal);
	
	tBody.appendChild(trProducto);
  });
  
  let trTotal = document.createElement("tr");

  let espacioVacioTotal = document.createElement("td");
  espacioVacioTotal.colSpan = "4";
  trTotal.appendChild(espacioVacioTotal);
  
  let tdTotal = document.createElement("td");
  
  let total = carrito.reduce((acc, p) => acc + p.precio * p.cantidad, 0);
  
  tdTotal.innerText = `Total: $${total.toFixed(2)}`;
  
  trTotal.appendChild(tdTotal);
  tBody.appendChild(trTotal);
  
  let trWhatsapp = document.createElement("tr");
  let tdWhatsapp = document.createElement("td");
  tdWhatsapp.className = "text-center";
  tdWhatsapp.colSpan = "5";
  
  let aWhatsapp = document.createElement("a");
  aWhatsapp.innerHTML = "<i class='fa-brands fa-whatsapp fa-xl'></i>  Realizar consulta";
  aWhatsapp.id = "btnWhatsapp";
  aWhatsapp.className = "whatsapp-btn";
  aWhatsapp.target = "_blank";
  aWhatsapp.onclick = mensajeWhatsapp;
  
  tdWhatsapp.appendChild(aWhatsapp);
  trWhatsapp.appendChild(tdWhatsapp);
  tBody.appendChild(trWhatsapp);
  
  tabla.appendChild(tBody);
  contenedor.appendChild(tabla);
}

function obtenerCarrito() {
  return JSON.parse(localStorage.getItem("carrito")) || [];
}

function guardarCarrito(carrito){
	localStorage.setItem("carrito", JSON.stringify(carrito));
}

function modificarCantidad(id, cambio) {
  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];

  let index = carrito.findIndex(p => p.id === id);
  if (index !== -1) {
    carrito[index].cantidad += cambio;
    if (carrito[index].cantidad < 1) {
      carrito.splice(index, 1);
    }
    localStorage.setItem("carrito", JSON.stringify(carrito));
    mostrarCarrito();
	actualizarCantidadCarrito();
  }
}

function actualizarCantidadCarrito(){
	let cantidad = obtenerCarrito().length;
	let cantidadCarrito = document.getElementById("cantidadCarrito");
	cantidadCarrito.innerText = ` (${cantidad}) `;
};

mostrarCarrito();
actualizarCantidadCarrito();

function mensajeWhatsapp () {
	let mensaje = `Quisiera hacer una consulta con los siguientes productos:\n`;
	const carrito = obtenerCarrito();

	carrito.forEach((producto) => {
		mensaje += `${producto.descripcion} - x ${producto.cantidad} - precio $${producto.precio} - subtotal $${(producto.cantidad*producto.precio).toFixed(2)}\n`;
	})
	
	let total = carrito.reduce((sum, producto) => sum + (producto.cantidad * producto.precio), 0);
	
	mensaje += `Total: $${total}.`;
	
	const numeroTelefono = "5492645711922"; //
	const enlace = `https://wa.me/${numeroTelefono}?text=${encodeURIComponent(mensaje)}`;
	
	btnWhatsapp.href = enlace;
}