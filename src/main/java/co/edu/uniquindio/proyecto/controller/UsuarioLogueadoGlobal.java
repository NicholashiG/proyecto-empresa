package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.model.Usuario;

public class UsuarioLogueadoGlobal {

    private static Usuario usuarioLogueado;

    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public static void setUsuarioLogueado(Usuario usuarioLogueado) {
        UsuarioLogueadoGlobal.usuarioLogueado = usuarioLogueado;
    }

}
