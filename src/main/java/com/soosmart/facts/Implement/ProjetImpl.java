package com.soosmart.facts.Implement;

import com.soosmart.facts.dto.project.ProjetDTO;
import com.soosmart.facts.dto.project.SaveProjetDTO;
import com.soosmart.facts.dto.project.UpdateProjet;
import com.soosmart.facts.entity.Client;
import com.soosmart.facts.entity.Projet;
import com.soosmart.facts.mapper.ResponseMapper;
import com.soosmart.facts.repository.ClientDAO;
import com.soosmart.facts.repository.ProjetDAO;
import com.soosmart.facts.service.ProjetService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProjetImpl implements ProjetService {

    private final ProjetDAO projetRepository;
    private final ClientDAO clientRepository;
    private final ResponseMapper responseMapper;

    @Override
    public List<ProjetDTO> list() {
        return this.projetRepository.findAll().stream().map(
                this.responseMapper::responseProjetDTO
        ).toList();
    }

    @Override
    public ProjetDTO save(SaveProjetDTO saveProjetDTO) {
        Optional<Client> client = this.clientRepository.findById(UUID.fromString(saveProjetDTO.client_id()));

        if (client.isPresent()) {
            return this.responseMapper.responseProjetDTO(this.projetRepository.save(Projet.builder()
                    .projet_type(saveProjetDTO.projet_type())
                    .description(saveProjetDTO.description())
                    .offre(saveProjetDTO.offre())
                    .client(client.get())
                    .build())
            );
        }else {
            throw new EntityExistsException("Client not found");
        }

    }

    @Override
    public ProjetDTO update(UUID id, UpdateProjet updateProjet) {
        Optional<Projet> projetOld = this.projetRepository.findById(id).stream().findFirst();
        if (projetOld.isPresent()) {
            Projet projetNew = projetOld.get();
            projetNew.setProjet_type(updateProjet.projet_type());
            projetNew.setDescription(updateProjet.description());
            projetNew.setOffre(updateProjet.offre());
            return this.responseMapper.responseProjetDTO(this.projetRepository.save(projetNew));
        }
        else {
            throw new EntityExistsException("Projet not found");
        }
    }

    @Override
    public Boolean changeOffre(UUID id) {

        Optional<Projet> projet = this.projetRepository.findById(id).stream().findFirst();
        if (projet.isPresent()) {
            Projet projetNew = projet.get();
            projetNew.setOffre(!projetNew.getOffre());
            this.projetRepository.save(projetNew);
            return projetNew.getOffre();
        }
        else {
            throw new EntityExistsException("Projet not found");
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            this.projetRepository.delete(this.projetRepository.getReferenceById(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}