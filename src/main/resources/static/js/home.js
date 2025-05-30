class Producto{
	constructor(id, descripcion, img, precio, cantidad = 1){
		this.id = id;
		this.descripcion = descripcion;
		this.img = img;
		this.precio = precio;
		this.cantidad = cantidad;
	}
}

function agregarCarrito(id, descripcion, img, precio){
	
	let carrito = obtenerCarrito();
	
	let index = carrito.findIndex(p => p.id === id);
	
	if (index !== -1) {
	  carrito[index].cantidad += 1;
	} else {
	  let nuevoProducto = new Producto(id, descripcion, img, precio);
	  carrito.push(nuevoProducto);
	}

	guardarCarrito(carrito);
	actualizarCantidadCarrito();
	alert("Producto agregado al carrito");
}

function obtenerCarrito() {
  return JSON.parse(localStorage.getItem("carrito")) || [];
}

function guardarCarrito(carrito){
	localStorage.setItem("carrito", JSON.stringify(carrito));
}

function actualizarCantidadCarrito(){
	let cantidad = obtenerCarrito().length;
	let cantidadCarrito = document.getElementById("cantidadCarrito");
	cantidadCarrito.innerText = ` (${cantidad}) `;
};

actualizarCantidadCarrito();