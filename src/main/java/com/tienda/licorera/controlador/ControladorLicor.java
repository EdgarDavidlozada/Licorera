package com.tienda.licorera.controlador;

import java.util.List;

import com.tienda.licorera.modelo.Categoria;
import com.tienda.licorera.modelo.Licor;
import com.tienda.licorera.modelo.Usuario;
import com.tienda.licorera.sevicio.ICategoriaServicio;
import com.tienda.licorera.sevicio.ILicorServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorLicor {

    @Autowired
    private ILicorServicio licorServicio;

    @Autowired
    private ICategoriaServicio categoriaServicio;


    //METODO PARA LISTAR LOS LICORES EN EL HOME
    @GetMapping({"/licorera","/","/home","/index"})
    public String listarLicores(Model model,Usuario usuario){
        model.addAttribute("usuario", usuario);
        model.addAttribute("cabecera","Licores Disponibles | MaxLicor's");
        model.addAttribute("titulo","LICORES DISPONIBLES");
        List<Categoria>listadoCategorias=categoriaServicio.listarTodas();
        List<Licor>listadoLicores=licorServicio.listarTodos();
        model.addAttribute("categorias",listadoCategorias);
        model.addAttribute("licores",listadoLicores);
        return "index";
    }


    //INICIO GESTION DE LICORES
    @GetMapping("/administrador/gestion/licores")
    public String gestionLicores(Model modelo, Licor licor) {
        List<Licor> listadoLicor = licorServicio.listarTodos();
        List<Categoria> listadoCategoria = categoriaServicio.listarTodas();
        modelo.addAttribute("navbar", "Admin MaxLicor's");
        modelo.addAttribute("cabecera", "Gestión Licores | Admin MaxLicor's");
        modelo.addAttribute("titulo", "GESTIÓN DE LICORES");
        modelo.addAttribute("pagina", "licores");
        modelo.addAttribute("categorias", listadoCategoria);
        modelo.addAttribute("licores", listadoLicor);
        modelo.addAttribute("licor", licor);

        return "administrador/indexAdmin";
    }

    @PostMapping("/administrador/gestion/licores/guardar")
    public String guardarLicor(@ModelAttribute Licor licor) {
        licorServicio.guardarLicor(licor);
        return "redirect:/administrador/gestion/licores";
    }

    @GetMapping("/administrador/gestion/licores/eliminar/{cod_licor}")
    public String eliminarLicor(@PathVariable("cod_licor") int cod_licor) {
        licorServicio.eliminarLicor(cod_licor);
        return "redirect:/administrador/gestion/licores";
    }
    //FINAL GESTION DE LICORES



}
