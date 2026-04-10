package com.sgsi.catalogos.service;

import org.springframework.lang.NonNull;

import com.sgsi.catalogos.entity.CatTipoTicket;
import com.sgsi.catalogos.repository.CatTipoTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatTipoTicketService {

    private final CatTipoTicketRepository repository;

    public List<CatTipoTicket> findAll() { return repository.findAll(); }
    public Optional<CatTipoTicket> findById(@NonNull Integer id) { return repository.findById(id); }
    public CatTipoTicket save(@NonNull CatTipoTicket entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
