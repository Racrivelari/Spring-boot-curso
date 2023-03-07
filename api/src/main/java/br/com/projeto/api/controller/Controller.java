package br.com.projeto.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.projeto.api.model.Pessoa;
import br.com.projeto.api.repository.Repo;
import br.com.projeto.api.servico.Servico;

@RestController
public class Controller {

    @Autowired
    private Repo acao;

    @Autowired
    private Servico servico;

    @PostMapping("/api/cadastrar")
    public ResponseEntity<?> cadastrarPessoas(@RequestBody Pessoa obj){
        return servico.cadastrar(obj);
    }

    @GetMapping("/api/pessoas")
    public ResponseEntity<?> getAllPessoas(){
        return servico.getPessoas();
    }

    @GetMapping("/api/pessoaId/{id}")
    public ResponseEntity<?> getPessoaById(@PathVariable int id){
        try{
            return servico.getPessoaById(id);
        }catch(Exception x){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada", x);
        }    
    }

    @PutMapping("/api/editar") // no put tem q ser o obj inteiro
    public ResponseEntity<?> editarPessoa(@RequestBody Pessoa obj){
        return servico.editar(obj);
    }

    @DeleteMapping("/api/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable int id){
        return servico.deletar(id);
    }



    @GetMapping("/api/count")
    public int contarPessoas(){
        return acao.sumPessoas();
    }

    @GetMapping("/api/pessoaNome/{nome}")    // esse metodo n deu certo
    public List<Pessoa> getPessoaByName(@PathVariable String name){
        return acao.findByNome(name);
    }




    

    @GetMapping("/")
    public String Mensagem(){      //cada método é uma rota, deve especificar um caminho, toda rota pode ter uma especificacao pra dizer se vc quer cadastrar/selecionar,alterar/excluir
            return "Hello World!";
    }

    @GetMapping("/boasVindas")     //nao pode ter 2 rotas com o msm caminho com o getmapping
    public String boasVindas(){
        return "Seja bem vindo! ";
    }
    
    @GetMapping("/boasVindas/{nome}")     //nao pode ter 2 rotas com o msm caminho com o getmapping
    public String boasVindas(@PathVariable String nome){
        return "Seja bem vindo! " + nome;
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p){
        return p;
    }
}
