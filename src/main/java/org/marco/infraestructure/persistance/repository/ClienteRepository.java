package org.marco.infraestructure.persistance.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import org.marco.application.dto.PagedResult;
import org.marco.infraestructure.persistance.entities.ClienteEntity;

@ApplicationScoped
public class ClienteRepository implements  PanacheRepository<ClienteEntity> {

  @Inject
  EntityManager em;

  public Optional<PagedResult<ClienteEntity>> listarPaginado(int page, int size) {

    List<ClienteEntity> data = em
        .createNativeQuery(
            """
            SELECT *
            FROM cliente
            ORDER BY id_cliente
                 OFFSET :offset ROWS
                                FETCH NEXT :size ROWS ONLY
            """,
            ClienteEntity.class
        )
        .setParameter("size", size)
        .setParameter("offset", page * size)
        .getResultList();

    long total = ((Number) em
        .createNativeQuery("SELECT COUNT(*) FROM cliente")
        .getSingleResult())
        .longValue();

    return Optional.of(new PagedResult<>(data, total, page, size));
  }


}
