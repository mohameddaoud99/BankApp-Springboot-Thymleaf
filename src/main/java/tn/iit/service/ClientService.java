package tn.iit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.iit.dao.ClientDao;
import tn.iit.entity.Client;

@Service
public class ClientService {
	private final ClientDao clientDao;

	@Autowired
	public ClientService(ClientDao clientDao) {
		super();
		this.clientDao = clientDao;
	}
	
	public void save(Client client) {
		clientDao.saveAndFlush(client);
	}
	
	public void update(Client client){
		clientDao.saveAndFlush(client);
	}
	
	public void delete(int id) {
		clientDao.deleteById(id);
	}
	
	public List<Client> getAll() {
		return clientDao.findAll();
	}
	
	public Client findById(int id) {
		Optional<Client> optionalClient = clientDao.findById(id);
		if(optionalClient.isPresent()) {
			Client client = optionalClient.get();
			return client;
		}
		return null;
	}	
}
