package com.skilldistillery.film.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class FilmDaoJdbcImplTest {

	private FilmDaoJdbcImpl dao;

	@BeforeEach
	void setUp() throws Exception {
		dao = new FilmDaoJdbcImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		dao = null;
	}

	@Test
	void test_findFilmById_returns_film() {
		Film film = dao.findFilmById(1);
		assertNotNull(film);
		assertEquals("ACADEMY DINOSAUR", film.getTitle());
	}

	@Test
	void test_findFilmById_returns_null_for_invalid_id() {
		Film film = dao.findFilmById(1234567);
		assertNull(film);
	}
	
	@Test
	void test_searchByKeyWord_returns_correct_results() {
		Set<Integer> dbFilmIds = dao.searchByKeyword("aca").stream().map(Film::getId).collect(Collectors.toSet());
		Set<Integer> testFilmIds = new HashSet<>(Arrays.asList(1, 114, 930, 940));
		assertEquals(dbFilmIds, testFilmIds);
	}
	
	@Test
	void test_findActorsByFilmId_returns_correct_results() {
		Set<Integer> dbActorIds = dao.searchActorsByFilmId(1).stream().map(Actor::getId).collect(Collectors.toSet());
		Set<Integer> testActorIds = new HashSet<>(Arrays.asList(1, 10, 20, 30, 40, 53, 108, 162, 188, 198));	
		assertEquals(dbActorIds, testActorIds);
	}

}
