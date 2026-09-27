package br.com.farmaconecta.infrastructure.persistence;

import br.com.farmaconecta.domain.model.Ubs;
import br.com.farmaconecta.domain.repository.UbsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UbsRepositoryAdapter implements UbsRepository {

    private final UbsJpaRepository ubsJpaRepository;

    public UbsRepositoryAdapter(UbsJpaRepository ubsJpaRepository) {
        this.ubsJpaRepository = ubsJpaRepository;
    }

    @Override
    public Ubs salvar(Ubs ubs) {

        UbsEntity entity = paraEntity(ubs);

        UbsEntity entitySalva = ubsJpaRepository.save(entity);

        return paraDominio(entitySalva);
    }

    @Override
    public Optional<Ubs> buscarPorId(Long id) {

        return ubsJpaRepository.findById(id)
                .map(this::paraDominio);
    }

    @Override
    public Optional<Ubs> buscarPorCnes(String cnes) {

        return ubsJpaRepository.findByCnes(cnes)
                .map(this::paraDominio);
    }

    @Override
    public List<Ubs> listarTodas() {

        return ubsJpaRepository.findAll()
                .stream()
                .map(this::paraDominio)
                .toList();
    }

    @Override
    public boolean existePorCnes(String cnes) {

        return ubsJpaRepository.existsByCnes(cnes);
    }

    private UbsEntity paraEntity(Ubs ubs) {

        return new UbsEntity(
                ubs.getId(),
                ubs.getNome(),
                ubs.getCnes(),
                ubs.getEndereco(),
                ubs.getCidade(),
                ubs.getUf(),
                ubs.isAtiva()
        );
    }

    private Ubs paraDominio(UbsEntity entity) {

        return new Ubs(
                entity.getId(),
                entity.getNome(),
                entity.getCnes(),
                entity.getEndereco(),
                entity.getCidade(),
                entity.getUf(),
                entity.isAtiva()
        );
    }
}