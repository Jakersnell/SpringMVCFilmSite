package com.skilldistillery.film.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String description;
	private short releaseYear;
	private int rentalDur;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private List<Feature> features;
	private List<Actor> actorsInFilm;
	private String language;

	public Film(int id, String title, String description, short releaseYear, int rentalDur, double rentalRate,
			int length, double replacementCost, String rating, List<Feature> features, String language,
			List<Actor> actorsInFilm) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.rentalDur = rentalDur;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.features = features;
		this.language = language;
		this.actorsInFilm = actorsInFilm;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actorsInFilm, description, features, id, language, length, rating, releaseYear, rentalDur,
				rentalRate, replacementCost, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actorsInFilm, other.actorsInFilm) && Objects.equals(description, other.description)
				&& Objects.equals(features, other.features) && id == other.id
				&& Objects.equals(language, other.language) && length == other.length
				&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear
				&& rentalDur == other.rentalDur
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", description=" + description + ", releaseYear=" + releaseYear
				+ ", rentalDur=" + rentalDur + ", rentalRate=" + rentalRate + ", length=" + length
				+ ", replacementCost=" + replacementCost + ", rating=" + rating + ", features=" + features
				+ ", actorsInFilm=" + actorsInFilm + ", language=" + language + "]";
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public short getReleaseYear() {
		return releaseYear;
	}

	public int getRentalDur() {
		return rentalDur;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public int getLength() {
		return length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public List<Actor> getActorsInFilm() {
		return actorsInFilm;
	}

	public String getLanguage() {
		return language;
	}

}
