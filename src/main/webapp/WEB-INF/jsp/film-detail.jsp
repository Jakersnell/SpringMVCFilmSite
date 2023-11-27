<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="_head.jsp" />
<jsp:include page="_header.jsp" />
<div class="container mt-4 detail-block">
	<div class="spacer"></div>
	<div class="row mt-2 mb-0">
		<div class="col-2 mb-0  mb-0 ml-0 pl-0"></div>
		<div class="col-8 mb-0 ml-0 pl-0">
			<div class="row m-0 p-0">
				<div class="col-10">
					<h3 class="display-title text-center-left" id="film-title">
						<span class="display-accent">&#9134;</span>${film.title}<span
							class="display-accent">&#9134;</span>#${film.id} <span
							class="display-accent-secondary"> in movies today</span>
					</h3>
				</div>
				<div class="col-2 d-flex justify-content-end align-items-center">
					<div class="dropdown d-inline-block" id="dropdown-btn">
						<button
							class="btn btn-primary dropdown-toggle w-auto min-width-0 drop-btn"
							type="button" id="genreDropdown" data-bs-toggle="dropdown"
							aria-expanded="false">Watch Options</button>
						<ul class="dropdown-menu" aria-labelledby="genreDropdown">
							<li><a class="dropdown-item" href="#">Rent from
									${film.rentalRate}</a></li>
						</ul>
					</div>
				</div>
			</div>

		</div>
		<div class="col-2 mb-0"></div>
	</div>
	<div class="row mt-2 mb-3">
		<div class="col-1"></div>
		<div class="col-10 film-detail" id="film-detail">
			<div class="row">
				<h6 class="card-subtitle">${film.releaseYear}&nbsp;&#8226;&nbsp;${film.rating}&nbsp;&#8226;&nbsp;${film.length}m&nbsp;&#8226;&nbsp;${film.language}</h6>
			</div>
			<div class="row mt-2">
				<p class="card-text font-weight-bold">${film.description}</p>
			</div>
			<hr class="mt-2 mb-1">
			<div class="row mt-2">
				<div class="col-6">
					<h6 class="card-subtitle ml-3">Cast</h6>
					<p class="card-text">
						<c:forEach var="actor" items="${film.actorsInFilm}"
							varStatus="loopStatus"><span class="feature">${actor.name}</span><c:if
								test="${!loopStatus.last}">&nbsp;&#8226;&nbsp;</c:if>
						</c:forEach>
					</p>
				</div>
				<div class="col-6">
					<h6 class="card-subtitle">Special Features</h6>
					<p class="card-text">
						<c:forEach var="feature" items="${film.features}"
							varStatus="loopStatus"><span class="feature">${feature.type}</span><c:if
								test="${!loopStatus.last}">&nbsp;&#8226;&nbsp;</c:if>
						</c:forEach>
					</p>
				</div>
			</div>

		</div>
		<div class="col-1"></div>
	</div>

</div>

<div class="container mt-4 mb-4"></div>
<jsp:include page="_footer.jsp" />
