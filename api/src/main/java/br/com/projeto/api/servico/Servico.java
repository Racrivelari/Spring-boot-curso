package br.com.projeto.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.model.Mensagem;
import br.com.projeto.api.model.Pessoa;
import br.com.projeto.api.repository.Repo;

@Service
public class Servico {
    
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repo acao;


    //Método de cadastro pessoas
    public ResponseEntity<?> cadastrar(Pessoa obj){
        if(obj.getNome().equals("")){
            mensagem.setMensagem("Nome não pode ser vazio");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(obj.getIdade() <= 0){
            mensagem.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(obj.getEmail().equals("")){
            mensagem.setMensagem("Informe um email válido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            acao.save(obj);
            mensagem.setMensagem("Pessoa cadastrada com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
        }
    }

    //Método pra get pessoas
    public ResponseEntity<?> getPessoas(){ 
        if(acao.count() == 0){   //esse count fumego
            mensagem.setMensagem("Nenhuma pessoa existente no banco de dados ainda!");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    //Método get pessoa by codigo
    public ResponseEntity<?> getPessoaById(int codigo){
        if(acao.countByCodigo(codigo) == 0){
            mensagem.setMensagem("Pessoa não foi encontrada");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.findById(codigo), HttpStatus.OK);
        }
    }

    //Método pra editar
    public ResponseEntity<?> editar(Pessoa obj){
        if(acao.countByCodigo(obj.getCodigo()) == 0){
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else if(obj.getNome().equals("")){
            mensagem.setMensagem("Nome não pode ser vazio");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(obj.getIdade() <= 0){
            mensagem.setMensagem("Idade não pode ser vazia");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(obj.getEmail().equals("")){
            mensagem.setMensagem("Email não pode ser vazio");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            acao.save(obj);
            mensagem.setMensagem("Pessoa editada com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }

    //Método pra deletar pessoa pelo id
    public ResponseEntity<?> deletar(int codigo){
        if(acao.countByCodigo(codigo) == 0){
            mensagem.setMensagem("Pessoa não foi encontrada");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            Pessoa obj = acao.findById(codigo);
            acao.delete(obj);
            mensagem.setMensagem("Pessoa deletada com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
