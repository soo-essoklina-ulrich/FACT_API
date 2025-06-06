package com.soosmart.facts.dto.dossier.proforma;

import com.soosmart.facts.dto.articleQuantite.ArticleQuantiteDTO;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ProformaDTO(
        UUID id,
        String reference,
        String numero,
        List<ArticleQuantiteDTO> articleQuantiteslist,
        Float total_ht,
        Float total_ttc,
        Float total_tva,
        String client,
        Instant date,
        String signby,
        Boolean adopted
        ) {
}
