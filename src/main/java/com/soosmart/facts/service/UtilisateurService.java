package com.soosmart.facts.service;

import com.soosmart.facts.dto.user.ResponseUtilisateur;
import com.soosmart.facts.dto.user.SaveUtilisateurDTO;
import com.soosmart.facts.dto.user.UpdateUtilisateurDTO;
import com.soosmart.facts.entity.user.Utilisateur;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UtilisateurService extends UserDetailsService {

    void createSuprerAdmin(String email, String username, String password);
    List<ResponseUtilisateur> findAll();
    ResponseUtilisateur findByEmail(String email);
    ResponseUtilisateur findByUsername(String username);
    ResponseUtilisateur save(SaveUtilisateurDTO utilisateur);
    ResponseUtilisateur update(UUID id, UpdateUtilisateurDTO utilisateur);
    void delete(UUID id);
    Boolean activateUser(UUID id);

    UserDetails loadUserByUsername(String username);
}
