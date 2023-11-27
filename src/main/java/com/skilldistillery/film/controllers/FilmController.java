package com.skilldistillery.film.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	private static final String JSP_ROOT = "WEB-INF/jsp/";
	private int resultLimit = 18;
	@Autowired
	private FilmDAO dao;

	public void setDAO(FilmDAO dao) {
		this.dao = dao;
	}

	@RequestMapping()
	public ModelAndView homeView() {
		ModelAndView view = new ModelAndView();
		view.setViewName(JSP_ROOT + "home.jsp");
		view.addObject("films", getRandomFilms(9, 1000));
		view.addObject("searched", false);
		return view;
	}

	@RequestMapping(path = "search-film", params = "query")
	public ModelAndView filmSearchDispatch(@RequestParam("query") String query) {
		ModelAndView view = new ModelAndView();
		view.addObject("searched", true);
		if (query.chars().allMatch(Character::isDigit)) {
			return findFilmById(Integer.parseInt(query));
		} else {
			return searchFilmsByKeyWords(query, resultLimit);
		}
	}

	@RequestMapping(path = "film", params = "id")
	public ModelAndView filmView(@RequestParam("id") int id) {
		ModelAndView view = findFilmById(id);
		view.addObject("searched", false);
		return view;
	}

	public ModelAndView findFilmById(int id) {
		ModelAndView view = new ModelAndView();
		Film film = dao.findFilmById(id);
		if (film == null) {
			view.setViewName(JSP_ROOT + "no-results.jsp");
			view.addObject("query", "film id is " + id);
		} else {
			view.setViewName(JSP_ROOT + "film-detail.jsp");
			view.addObject("film", film);
		}
		return view;
	}

	public ModelAndView searchFilmsByKeyWords(@RequestParam("keywords") String keywords, int limit) {
		ModelAndView view = new ModelAndView();
		List<Film> films = dao.searchByKeyword(keywords, limit);
		if (films.isEmpty()) {
			view.setViewName(JSP_ROOT + "no-results.jsp");
			view.addObject("query", keywords + " in film title or description.");
		} else {
			view.setViewName(JSP_ROOT + "home.jsp");
			view.addObject("searched", true);
			view.addObject("films", films);
		}
		return view;
	}

	public List<Film> getRandomFilms(int count, int range) {
		List<Film> randomFilms = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Film film = dao.findFilmById((int) (Math.random() * range));
			if (film == null) {
				continue;
			}
			randomFilms.add(dao.findFilmById((int) (Math.random() * range + 1)));
		}
		return randomFilms;
	}
}
