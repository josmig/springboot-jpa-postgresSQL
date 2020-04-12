package com.example.demo.Controller;

import java.util.Map;

import com.example.demo.model.Entity.Cliente;
import com.example.demo.model.Service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;

@Controller
@RequestMapping(value ="/cliente")
public class ClienteController {


	//Inyección de dependecias , de nuestro Dao (Persistencia)
	@Autowired
	private ClienteServiceImpl clienteService;

	@GetMapping("/listar")
	public String listar(Map<String,Object>model) {
		model.put("titulo","Listado de clientes");
		model.put("clientes", clienteService.findAll());
		return "cliente/listar";
	}

	@GetMapping("/form")
	public String crear(Model model){
		String titulo = "Formulario Cliente";
		Cliente cliente = new Cliente();
		model.addAttribute("cl", cliente);
		model.addAttribute("title", titulo);
		return "cliente/form";
	}
	@PostMapping("/form")
	public String guardar(@Valid @ModelAttribute("cl") Cliente cliente,
                          BindingResult bindingResult, Model model , RedirectAttributes flash,
                          SessionStatus sessionStatus){

	    //si hay errores retorna al formulario
		if(bindingResult.hasErrors()){
				String titulo = "Formulario Cliente";
				model.addAttribute("title", titulo);
				return "cliente/form";
		}
		clienteService.save(cliente);
		sessionStatus.setComplete();
		String cliente_message = (cliente.getId() != null ) ? "Cliente editado con exito" : "Cliente creado con exito";
        flash.addFlashAttribute("success",cliente_message);
		return "redirect:/cliente/listar";
	}

	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String ,Object>model,RedirectAttributes flash){
		Cliente cliente = null;
		if(id > 0){
			cliente=clienteService.findOne(id);
			if(cliente == null){
                flash.addFlashAttribute("error","El ID del cliente no existe en la Base de datos");
                return "redirect:/cliente/listar";
            }
		}else{
		    flash.addFlashAttribute("error","El ID del cliente no puede ser 0");
			return "redirect:/cliente/listar";
		}
		model.put("cl",cliente); // con la misma variable que ponemos en crear lo usamos aca osea el "cl"
		model.put("title","Editar cliente");
		return "cliente/form";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable(value = "id")Long id,RedirectAttributes flash){
		if(id>0){
			clienteService.delete(id);
			flash.addFlashAttribute("success","Cliente eliminado con exito");
		}
		return "redirect:/cliente/listar";
	}


}
