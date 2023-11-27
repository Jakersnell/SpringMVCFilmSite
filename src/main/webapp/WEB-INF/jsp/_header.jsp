<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a href="/MVCFilmSite/"><div class="logo-box">
					<h4>MVCFilmSite</h4>

				</div></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<div class="navbar-nav me-auto mb-2 mb-lg-0"></div>
				<form class="d-flex" action="search-film" method="POST">
					<input class="form-control me-2" type="text" name="query"
						placeholder="Search films by ID or keyword" aria-label="Search"
						required style="width: 40vw;">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>
	<section id="popcorn-banner"></section>