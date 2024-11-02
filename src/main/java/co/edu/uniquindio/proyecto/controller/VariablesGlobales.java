package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.model.Usuario;

public class VariablesGlobales {

    private static Usuario usuarioLogueado;

    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public static void setUsuarioLogueado(Usuario usuarioLogueado) {
        VariablesGlobales.usuarioLogueado = usuarioLogueado;
    }

}
