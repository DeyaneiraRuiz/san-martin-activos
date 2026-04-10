package com.sgsi.catalogos.repository;

import com.sgsi.catalogos.entity.CatTipoTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatTipoTicketRepository extends JpaRepository<CatTipoTicket, Integer> {
}
