package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Feature;
import com.skilldistillery.film.entities.Film;

public class FilmDaoJdbcImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String AUTH_USER = "student";
	private static final String AUTH_PASS = "student";

	private static final String BASE_FILM_QUERY = "SELECT f.*, l.name AS language FROM film f JOIN language l ON f.language_id = l.id ";
	private static final String FILM_ID_QUERY = BASE_FILM_QUERY + "WHERE f.id = ?";
	private static final String FILM_KEYWORD_QUERY = BASE_FILM_QUERY + "WHERE f.title LIKE ? OR f.description LIKE ?";
	private static final String ACTORS_FILM_ID_QUERY = "SELECT actor.* FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_actor.film_id = ?";

	private PreparedStatement FIND_FILM_BY_ID;
	private PreparedStatement SEARCH_FILMS_BY_KEYWORD;
	private PreparedStatement FIND_ACTORS_BY_FILM_ID;
	private Connection conn;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public FilmDaoJdbcImpl() {
		try {
			conn = DriverManager.getConnection(URL, AUTH_USER, AUTH_PASS);
			FIND_FILM_BY_ID = conn.prepareStatement(FILM_ID_QUERY);
			SEARCH_FILMS_BY_KEYWORD = conn.prepareStatement(FILM_KEYWORD_QUERY);
			FIND_ACTORS_BY_FILM_ID = conn.prepareStatement(ACTORS_FILM_ID_QUERY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			FIND_FILM_BY_ID.setInt(1, filmId);
			FIND_FILM_BY_ID.setMaxRows(1);
			ResultSet results = FIND_FILM_BY_ID.executeQuery();
			if (results.next()) {
				film = mapFilm(results);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public List<Film> searchByKeyword(String keyword, int limit) {
		List<Film> films = new ArrayList<>();
		try {
			SEARCH_FILMS_BY_KEYWORD.setString(1, "%" + keyword + "%");
			SEARCH_FILMS_BY_KEYWORD.setString(2, "%" + keyword + "%");
			SEARCH_FILMS_BY_KEYWORD.setMaxRows(limit);
			ResultSet results = SEARCH_FILMS_BY_KEYWORD.executeQuery();
			while (results.next()) {
				films.add(mapFilm(results));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	public List<Actor> searchActorsByFilmId(int filmId) {
		List<Actor> actorsInFilm = new ArrayList<>();
		try {
			FIND_ACTORS_BY_FILM_ID.setInt(1, filmId);
			ResultSet results = FIND_ACTORS_BY_FILM_ID.executeQuery();
			while (results.next()) {
				Actor actor = mapActor(results);
				actorsInFilm.add(actor);
			}
			results.close();
		} catch (SQLException e) {
		}
		return actorsInFilm;
	}

	private Film mapFilm(ResultSet results) throws SQLException { // need to get language.
		int filmId = results.getInt("id");
		String title = results.getString("title");
		String desc = results.getString("description");
		short releaseYear = results.getShort("release_year");
		int rentDur = results.getInt("rental_duration");
		double rate = results.getDouble("rental_rate");
		int length = results.getInt("length");
		double repCost = results.getDouble("replacement_cost");
		String rating = results.getString("rating");
		List<Feature> features = Feature
				.matchFeatures(Arrays.asList(results.getString("special_features").split("\\s*,\\s*")));
		String language = results.getString("language");
		List<Actor> actorsInFilm = searchActorsByFilmId(filmId);
		Film film = new Film(filmId, title, desc, releaseYear, rentDur, rate, length, repCost, rating, features,
				language, actorsInFilm);
		return film;
	}

	public Actor mapActor(ResultSet results) throws SQLException {
		int id = results.getInt("id");
		String firstName = results.getString("first_name");
		String lastName = results.getString("first_name");
		Actor actor = new Actor(id, firstName, lastName);
		return actor;
	}

}
