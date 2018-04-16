package br.com.selecao.apisys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import br.com.selecao.apisys.model.Curso;
import br.com.selecao.apisys.repository.CursoRepository;

@Controller
public class CursoController {

	@Autowired
	private CursoRepository repository;

	@RequestMapping("listaCursos")
	public String listaCursos(Model model) {
		Iterable<Curso> cursos = repository.findAll();
		model.addAttribute("cursos", cursos);
		return "listaCursos";
	}
	
	
	@RequestMapping("addCurso")
	public String addCurso(Curso curso) {
		
		return "formCurso";
	}
	
	@RequestMapping("editCurso/{id}")
	public String editCurso(Curso curso, Model model) {
		
		model.addAttribute("curso",repository.findById(curso.getId()));
		
		
		return "formCurso";
	}
	
	@RequestMapping("deleteCurso/{id}")
	public String deleteCurso(Curso curso,  Model model)  {
		
		repository.deleteById(curso.getId());

	    return "redirect:/listaCursos";
	}
	
	@RequestMapping(value= "salvarCurso", method = RequestMethod.POST)
	public String salvar(Curso curso, Model model  ){
        
		System.out.println(curso);
		repository.save(curso); 

		Iterable<Curso> cursos = repository.findAll();
		model.addAttribute("cursos", cursos);

	    return "listaCursos";
	}
	
	

}
