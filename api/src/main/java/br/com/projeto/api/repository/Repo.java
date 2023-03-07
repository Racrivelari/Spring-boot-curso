package br.com.projeto.api.repository;

import br.com.projeto.api.model.Pessoa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends CrudRepository<Pessoa, Integer>{

    List<Pessoa> findAll();   //o find all é nativo do repository, aq so digo q quando chamar esse metodo ele deve retornar uma lista de pessoas

    Pessoa findById(int codigo); //uma pessoa só

    List<Pessoa> findByNome(String name); // aq pd ter + de 1 pessoa com msm nome / NAO DEU CERTO NO CONTROLLER

    @Query(value = "SELECT count(*) FROM pessoas",nativeQuery = true)
    int sumPessoas(); // aq faz sentido pq eu defini uma query logica pra ela a cima

    int countByCodigo(int codigo);// só n entendi como q essa func la no servico ela é intuitiva automaticamente a contar/localizar o codigo no bd

}
