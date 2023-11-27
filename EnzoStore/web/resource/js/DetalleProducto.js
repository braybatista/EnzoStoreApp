//var carritoVisible = false;
//var carritoProductos = [];
//var historialProductos = {};
//
//function toggleCarrito() {
//    var carritoButton = document.querySelector('.carrito-button');
//    var carritoPopup = document.querySelector('.carrito-popup');
//
//    if (!carritoVisible) {
//        carritoPopup.style.display = 'block';
//        carritoVisible = true;
//        mostrarProductosEnCarrito();
//    } else {
//        carritoPopup.style.display = 'none';
//        carritoVisible = false;
//    }
//}
//
//function closeCarritoPopup() {
//    var carritoPopup = document.querySelector('.carrito-popup');
//    carritoPopup.style.display = 'none';
//    carritoVisible = false;
//}
//
//function agregarAlCarrito(nombre, precio) {
//    // Agregar el producto al carrito
//    carritoProductos.push({nombre: nombre, precio: precio});
//    mostrarProductosEnCarrito();
//}
//
//function mostrarDetalles(nombre, precio) {
//    // Mostrar la ventana emergente de detalles
//    var detallesPopup = document.querySelector('.detalles-popup');
//    var detallesTitulo = document.getElementById('detallesTitulo');
//    var detallesContenido = document.getElementById('detallesContenido');
//
//    detallesTitulo.textContent = nombre;
//    detallesContenido.innerHTML = 'Precio: $' + precio;
//    detallesPopup.style.display = 'block';
//
//    // Guardar el producto en el historial
//    if (!historialProductos[nombre]) {
//        historialProductos[nombre] = {nombre: nombre, precio: precio};
//        mostrarHistorialProductos();
//    }
//}
//
////function closeDetallesPopup(nombre) {
////    var detallesPopup = document.getElementById('detallesPopup_' + nombre);
////    detallesPopup.style.display = 'none';
////}
//
//function mostrarProductosEnCarrito() {
//    var carritoProductosDiv = document.getElementById('carritoProductos');
//    carritoProductosDiv.innerHTML = '';
//
//    carritoProductos.forEach(function (producto) {
//        var productoDiv = document.createElement('div');
//        productoDiv.className = 'carrito-producto';
//        productoDiv.innerHTML = '<span>' + producto.nombre + '</span>' + '<span>$' + producto.precio + '</span>';
//        carritoProductosDiv.appendChild(productoDiv);
//    });
//}
//
//function mostrarHistorialProductos() {
//    var historialItemsDiv = document.getElementById('historialItems');
//    historialItemsDiv.innerHTML = '';
//
//    for (var key in historialProductos) {
//        if (historialProductos.hasOwnProperty(key)) {
//            var historialProducto = historialProductos[key];
//            var productoDiv = document.createElement('div');
//            productoDiv.className = 'historial-item';
//            productoDiv.innerHTML = '<span>' + historialProducto.nombre + '</span>' + '<span>$' + historialProducto.precio + '</span>';
//            historialItemsDiv.appendChild(productoDiv);
//        }
//    }
//    
//    
//        
//}

