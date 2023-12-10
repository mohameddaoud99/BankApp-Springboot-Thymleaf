package tn.iit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.iit.entity.Compte;
@Repository
public interface CompteDao extends JpaRepository<Compte, Integer>{
	List<Compte> findByClientId(int id);
}
