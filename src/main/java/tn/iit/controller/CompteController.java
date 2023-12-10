package tn.iit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tn.iit.entity.Client;
import tn.iit.entity.Compte;
import tn.iit.service.ClientService;
import tn.iit.service.CompteService;

@Controller
@RequestMapping("/compte")
public class CompteController {
	private final CompteService compteService;
	private final ClientService clientService;

	@Autowired
	public CompteController(CompteService compteService, ClientService clientService) {
		super();
		this.compteService = compteService;
		this.clientService = clientService;
	}

	@GetMapping
	public ModelAndView getAll() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("comptes", compteService.getAll());
		modelAndView.setViewName("nn");
		return modelAndView;
	}

	@GetMapping("/newCompte")
	public ModelAndView addNew() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("newCompte");
		return modelAndView;
	}

	@PostMapping("/addCompte")
	public String saveCompte(@RequestParam(value = "solde", required = true) float solde,
			@RequestParam(value = "client", required = true) Client id_client) {
		Compte compte = new Compte(solde, id_client);
		compteService.save(compte);
		return "redirect:/client";
	}

	@GetMapping("/deleteCompte/{id}")
	public String deleteCompte(@PathVariable int id) {
		compteService.delete(id);
		return "redirect:/client";
	}
	
	@PostMapping(value="/update" , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String update(Compte compte) {
		compteService.update(compte);
		return "redirect:/compte/getComptes/"+compte.getClient().getId();
	}
	
	@GetMapping("/getComptes/{id}")
	public ModelAndView getAllComptes(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("comptes", compteService.findComptes(id));
		modelAndView.addObject("client", clientService.findById(id));		
		modelAndView.setViewName("comptes");
		return modelAndView;
	}
	
	@GetMapping("/getOne/{id}")
	@ResponseBody
	public Compte getOne(@PathVariable int id) {
		Compte compte = compteService.findById(id);
		return compte;
	}
}
