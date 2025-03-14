package com.soosmart.facts.service.dossier;

import com.soosmart.facts.dto.dossier.borderau.BorderauDto;
import com.soosmart.facts.entity.dossier.Bordereau;

import java.util.List;
import java.util.UUID;

public interface BordereauService {
    BorderauDto saveBordereau(UUID id_proforma);

    List<BorderauDto> getBordereauAll();

    List<BorderauDto> getBordereauAllNotAdopted();

    BorderauDto getBordereau(UUID id);

    void deleteBordereau(UUID id);

    BorderauDto getBordereauByNumero(String numero);

    Bordereau getBordereauEntity(String numero);
}
