package br.senac.tads.crudspring.controller;

import DAO.ProdutoDAO;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController {

    @GetMapping("/")
    public ModelAndView obter() {
        try {
            return new ModelAndView("gerenciar-produtos").addObject("produtos", ProdutoDAO.listar());
        } catch (Exception ex) {
            System.out.print(ex);
            return new ModelAndView("erro").addObject("erro", ex.getMessage());
        }
    };

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        return new ModelAndView("cadastrar");
    };

    @PostMapping("/cadastrar")
    @ResponseBody
    public ResponseEntity<Object> cadastar(@RequestBody Produto pd) {
        try {
            ProdutoDAO.inserir(pd);
            return ResponseEntity.status(HttpStatus.OK).body("Produto cadastrado com sucesso.");
        } catch (Exception ex) {
            System.out.print(ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar o produto.<br>" + ex.getMessage());
        }
    };

    @GetMapping("/editar/{id}")
    public ModelAndView editarProduto(@PathVariable("id") Long id) {
        try {
            return new ModelAndView("editar").addObject("produto", ProdutoDAO.listar(id));
        } catch (Exception ex) {
            System.out.print(ex);
            return new ModelAndView("erro").addObject("erro", ex.getMessage());
        }
    };

    @PostMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<Object> editarProduto(@PathVariable("id") Long id, @RequestBody Produto prd) {
        try {
            prd.setId(id.intValue());
            ProdutoDAO.editar(prd);
            return ResponseEntity.status(HttpStatus.OK).body("Produto atualizado com sucesso.");
        } catch (Exception ex) {
            System.out.print(ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao editar o produto.<br>" + ex.getMessage());
        }
    };

    @PostMapping("/remover/{id}")
    @ResponseBody
    public ResponseEntity<Object> removerProduto(@PathVariable("id") Long id) {
        try {
            ProdutoDAO.remover(id);
            return ResponseEntity.status(HttpStatus.OK).body("Produto removido com sucesso.");
        } catch (Exception ex) {
            System.out.print(ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao remover o produto.<br>" + ex.getMessage());
        }
    };
}