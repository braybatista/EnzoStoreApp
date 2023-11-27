package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.conexion.Conexion;
import com.controlador.FaceUtil;
import com.modelo.DetallePedido;
import com.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;

public class DAODetallePedido {

    PreparedStatement ps;
    ResultSet rs;

    public List<DetallePedido> mostrarListaPedidosBD() {
        Connection con = null;
        ArrayList<DetallePedido> listapedidos = new ArrayList<>();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Usuario usuario = (Usuario) facesContext.getExternalContext().getSessionMap().get("user");
        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("select * from obtener_detalles_pedido where idusuario = " + usuario.getId() + ";");
            rs = ps.executeQuery();
            while (rs.next()) {
                listapedidos.add(DetallePedido.loadDetallePedido(rs));
            }
            con.close();

        } catch (SQLException s) {
            FaceUtil.addErrorMessage("error en la base de datos al iniciar sesion " + s);

        }

        return listapedidos;
    }
}
