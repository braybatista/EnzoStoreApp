<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:p="http://primefaces.org/ui"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
    <h:head>
        <title>Catalogo</title>
        <link rel="stylesheet" href="resource/css/estilosCatalogo.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <!-- Importar la clase ControladorCarritoBean -->
        <h:outputScript library="js" name="ControladorCarritoBean.js" target="head" />

        <script type="text/javascript">
            var carritoVisible = false;

            function toggleCarrito() {
                var carritoPopup = document.getElementById('carritoPopup');

                if (!carritoVisible) {
                    carritoPopup.style.display = 'block';
                    carritoVisible = true;
                    //mostrarProductosEnCarrito();
                } else {
                    carritoPopup.style.display = 'none';
                    carritoVisible = false;
                }
            }

            function closeCarritoPopup() {
                var carritoPopup = document.getElementById('carritoPopup');
                carritoPopup.style.display = 'none';
                carritoVisible = false;
            }
        </script>
    </h:head>
    <h:body class="fondoTienda">
        <p:graphicImage class="logoEnzo" value="/resource/images/LogoEnzo.png"/>
        <p:button icon="pi pi-user" onclick="PF('dlg1').show()" class="botonperfil"/>
        <h1 class="titulo">BIENVENIDOS A ENZO STORE</h1>

        <p:dialog header="MI PERFIL" widgetVar="dlg1" minHeight="40" width="350" showEffect="fade">
            <p class="m-0">
                <h3>NOMBRE: #{controladorUsuario.usuario.nombre}</h3>
                <h3>APELLIDO: #{controladorUsuario.usuario.apellido}</h3>
                <h3> TIPO: #{controladorUsuario.usuario.tipo_contacto}</h3>
            </p>
        </p:dialog>

        <h:form enctype="multipart/form-data">
            <h:commandButton value="Cerrar Sesion" action="#{controladorUsuario.cerrar()}"/>

            <div class="carruselCatalago">
                <div class="vistaproductos">
                    <p:carousel value="#{controladorPrenda.listaprenda}" var="prendaAMostrar" numVisible="4" circular="true">
                        <div class="producto-item">
                            <h:commandButton image="/Imagenes/Prendas/#{prendaAMostrar.nombre}.png" class="imagenPrenda"/>
                            <h:outputText value="#{prendaAMostrar.nombre}" class="producto-nombre"/>
                            <h:outputText value="$#{prendaAMostrar.precio}" class="producto-precio"/>
                            <h:outputText value="#{prendaAMostrar.estado}"/>
                        </div>

                        <!-- Llamar al método de agregar al carrito del bean -->
                        <h:commandButton value="Agregar al carrito" styleClass="boton-agregar" actionListener="#{controladorCarritoBean.agregarAlCarrito(prendaAMostrar.id, prendaAMostrar.nombre, prendaAMostrar.precio)}"/>
                        
                    </p:carousel>
                </div>
            </div>

            <h:commandButton value="mostrar pedidos" action="#{controladorVenta.mostrarPedidos()}"/>
            <div>
                <p:dataTable var="pedido" value="#{controladorVenta.listapedidos}">
                    <p:column headerText="Nombre">
                        <h:outputText value="#{pedido.nombre}" />
                    </p:column>
                    <p:column headerText="precio">
                        <h:outputText value="#{pedido.precio}" />
                    </p:column>
                    <p:column headerText="color">
                        <h:outputText value="#{pedido.color}" />
                    </p:column>
                    <p:column headerText="talla">
                        <h:outputText value="#{pedido.talla}" />
                    </p:column>
                </p:dataTable>
            </div>

            <div class="carrito-button" onclick="toggleCarrito()">&#128722;</div>

            <div class="carrito-popup" id="carritoPopup">
                <div class="popup-header">
                    <div class="popup-title">Carrito</div>
                    <div class="popup-close" onclick="closeCarritoPopup()">&#215;</div>
                </div>
                <div class="carrito">
                    <h2 class="carrito-titulo" id="carritoTotal">Total: $#{controladorCarritoBean.totalCarrito}</h2>
                    <div class="carrito-productos" id="carritoProductos">
                        <!-- Iterar sobre los productos en el carrito -->
                        <ui:repeat value="#{controladorCarritoBean.carritoProductos}" var="producto">
                            <div class="carrito-producto">
                                <span>#{producto.nombre}</span>
                                <span>$#{producto.precio}</span>
                                <!-- Llamar al método de eliminar del carrito del bean -->
                                <h:commandButton value="Eliminar" action="#{controladorCarritoBean.eliminarDelCarrito}">
                                    <f:param name="index" value="#{producto.index}" />
                                </h:commandButton>
                            </div>
                        </ui:repeat>
                    </div>
                    <h3>Total: $<span id="totalCarrito">#{controladorCarritoBean.totalCarrito}</span></h3>
                    <!-- Llamar al método de realizar compra del bean -->
                    <p:commandButton value="Realizar compra" styleClass="boton-compra" action="#{controladorCarritoBean.realizarCompra()}" />
                </div>
            </div>
        </h:form>
    </h:body>
</html>