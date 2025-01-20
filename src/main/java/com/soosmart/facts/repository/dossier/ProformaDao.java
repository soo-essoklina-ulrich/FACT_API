package com.soosmart.facts.repository.dossier;

import com.soosmart.facts.entity.dossier.Proforma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProformaDao extends JpaRepository<Proforma, UUID> {

    @Query("SELECT COUNT (p) FROM Proforma p WHERE p.create_at >= :startOfDay and p.create_at < :endOfDay")
    Long countProformasCreateToday(@Param("startOfDay") Instant startOfDay, @Param("endOfDay")Instant endOfDay);

    Optional<Proforma> findByNumero(String numero);

    List<Proforma> findAllByDeletedIsFalse();

    List<Proforma> findAllByDeletedIsFalseAndAdoptedIsFalse();

    List<Proforma> findAllByReference(String reference);

    Optional<Proforma> findByReference(String reference);
}
