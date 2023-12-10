package tn.iit.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tn.iit.entity.Client;
import tn.iit.service.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {
	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}
	
	@GetMapping
	public ModelAndView getAll() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("clients", clientService.getAll());
		modelAndView.setViewName("banque");
		return modelAndView;
	}
	
	@GetMapping("/getAllComptes")
	@ModelAttribute
	public Model getAllComptes(Model model) {
		List<Client> clients = clientService.getAll();
		model.addAttribute("clients", clients);
		return model;
	}
	
	@PostMapping("/addClient")
	public String addClient(Client client) {
		clientService.save(client);
		return "redirect:/client";
	}
	
	@PostMapping(value="/updateClient" , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String updateClient(Client client) {
		clientService.update(client);	
		return "redirect:/client";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteClient(@PathVariable int id) {
		clientService.delete(id);
		return "redirect:/client";
	}
	
	@GetMapping("/getOne/{id}")
	@ResponseBody
	public Client getOne(@PathVariable int id) {
		Client client = clientService.findById(id);
		return client;
	}
}
