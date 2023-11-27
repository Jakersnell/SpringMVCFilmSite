package com.skilldistillery.film.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum Feature {
	TRAI("Trailers"), COMM("Commentaries"), DEL("Deleted Scenes"), BTS("Behind the Scenes");

	private String type;

	Feature(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public static Feature matchFeature(String string) {
		return Arrays.stream(Feature.values()).filter(film -> film.type.equals(string)).findFirst().orElseGet(null);
	}

	public static List<Feature> matchFeatures(List<String> strings) {
		return strings.stream().map(Feature::matchFeature).filter(Objects::nonNull).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return this.type;
	}
}
