package tn.iit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.iit.dao.CompteDao;
import tn.iit.entity.Compte;

@Service
public class CompteService {
	private final CompteDao compteDao;

	@Autowired
	public CompteService(CompteDao compteDao) {
		super();
		this.compteDao = compteDao;
	}
	
	public void save(Compte compte) {
		compteDao.saveAndFlush(compte);
	}
	
	public void update(Compte compte){
		compteDao.saveAndFlush(compte);
	}
	
	public void delete(int rib) {
		compteDao.deleteById(rib);
	}
	
	public List<Compte> getAll() {
		return compteDao.findAll();
	}
	
	public Compte findById(int rib) {
		Optional<Compte> optionalCompte = compteDao.findById(rib);
		if(optionalCompte.isPresent()) {
			Compte compte = optionalCompte.get();
			return compte;
		}
		return null;
	}	
	
	public List<Compte> findComptes(int id){
		return compteDao.findByClientId(id);
	}
}
