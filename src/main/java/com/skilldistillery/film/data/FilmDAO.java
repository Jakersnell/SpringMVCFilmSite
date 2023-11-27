package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	Film findFilmById(int filmId);
	List<Film> searchByKeyword(String keyword, int limit);
}
