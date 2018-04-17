package br.com.selecao.apisys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "salvarCurso", method = RequestMethod.POST)
	public String salvaCurso(Curso curso, Model model) {

		repository.save(curso);

		Iterable<Curso> cursos = repository.findAll();
		model.addAttribute("cursos", cursos);

		return "listaCursos";
	}

	@RequestMapping("editCurso/{id}")
	public String editCurso(@PathVariable("id") Long cursoId, Model model) {

		model.addAttribute("curso", repository.findById(cursoId));

		return "formCurso";
	}

	@RequestMapping("deleteCurso/{id}")
	public String deleteCurso(@PathVariable("id") Long cursoId, Model model) {

		repository.deleteById(cursoId);

		return "redirect:/listaCursos";
	}

}
