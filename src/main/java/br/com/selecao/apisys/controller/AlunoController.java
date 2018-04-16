package br.com.selecao.apisys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.selecao.apisys.enums.Status;
import br.com.selecao.apisys.model.Aluno;
import br.com.selecao.apisys.model.Curso;
import br.com.selecao.apisys.repository.AlunoRepository;
import br.com.selecao.apisys.repository.CursoRepository;

@Controller
public class AlunoController {
	
	@Autowired
	private AlunoRepository repository;
	
	@Autowired
	private CursoRepository cursoRepository;

	@RequestMapping("listaAlunos")
	public String listaAlunos(Model model) {
		Iterable<Aluno> alunos = repository.findAll();
		model.addAttribute("alunos", alunos);
		return "listaAlunos";
	}
	
	
	@RequestMapping("addAluno")
	public String addAluno(Aluno aluno,Model model) {
		model.addAttribute("status", Status.values());
		model.addAttribute("cursos", cursoRepository.findAll());
		
		
		return "formAluno";
	}
	
	@RequestMapping("editAluno/{id}")
	public String editAluno(Aluno aluno, Model model) {
		
		model.addAttribute("aluno",repository.findById(aluno.getId()));
		model.addAttribute("status", Status.values());
		model.addAttribute("cursos", cursoRepository.findAll());
		
		return "formAluno";
	}
	
	@RequestMapping("deleteAluno/{id}")
	public String deleteAluno(Aluno aluno,  Model model)  {
		
		repository.deleteById(aluno.getId());

	    return "redirect:/listaAlunos";
	}
	
	@RequestMapping(value= "salvarAluno", method = RequestMethod.POST)
	public String salvar(Aluno aluno, Model model  ){
        
		System.out.println(aluno);
		repository.save(aluno); 

		Iterable<Aluno> alunos = repository.findAll();
		model.addAttribute("alunos", alunos);

	    return "listaAlunos";
	}

}
