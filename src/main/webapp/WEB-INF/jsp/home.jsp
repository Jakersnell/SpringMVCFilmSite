<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="_head.jsp" />
<jsp:include page="_header.jsp" />
<div class="container mt-4">
	<div class="row mt-2 mb-3">
		
		<div class="col-4">
			<h3 class="display-title">
				<span class="display-accent">&#9134;</span>
				<c:choose>
					<c:when test="${searched == false}">Top picks</c:when>
					<c:otherwise>Top search results</c:otherwise>
				</c:choose>
			</h3>
		</div>
		<div class="col-4"></div>
		<div class="col-4">
			
		</div>
	</div>
	<div class="row">
		<c:forEach var="film" items="${films}" varStatus="status">
			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card film-box">
					<a href="film?id=${film.id}">
						<div class="card-body">
							<h5 class="card-title">${film.title}</h5>
							<p class="card-text">${film.description}</p>
							<div class="b-inline">
								<h6 class="card-subtitle"></h6>
								<h6 class="card-subtitle mt-3">${film.releaseYear}&nbsp;&#8226;&nbsp;${film.length}<small>m</small>&nbsp;&#8226;&nbsp;${film.rating}
								</h6>
								<h6 class="card-subtitle"></h6>
							</div>
						</div>
					</a>
				</div>
			</div>
			<c:if test="${status.index % 3 == 2}">
				<div class="clearfix d-none d-md-block"></div>
			</c:if>
		</c:forEach>
	</div>
</div>
<div class="container mt-4 mb-4"></div>
<jsp:include page="_footer.jsp" />
