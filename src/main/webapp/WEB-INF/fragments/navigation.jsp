<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a href="home" class="navbar-brand">
            <img src="https://static.tildacdn.com/tild6431-3066-4264-b661-306535386264/logo.svg" height="16"
                 alt="CoolBrand">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarScroll">
            <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Link</a>
                </li>
            </ul>
            <form class="d-flex flex-row">
                <div class="p-2 bd-highlight">
                    <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                        <c:choose>
                            <c:when test="${sessionScope.user != null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="logoutAction">Log out</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a class="nav-link" href="login">Log in</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="registration">Registration</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <li class="nav-item">
                            <div class="d-flex">
                                <form action="search" method="post">
                                    <input class="form-control me-2" type="search" name="search" placeholder="Search"
                                           aria-label="Search">
                                    <button class="btn btn-outline-light" type="submit">Search</button>
                                </form>
                            </div>
                        </li>
                    </ul>
                </div>
            </form>
        </div>
    </div>
</nav>
