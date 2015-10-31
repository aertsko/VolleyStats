angular.module('volleystatsanalyser',['ui.router', 'ngResource','volleystatsanalyser.controllers','volleystatsanalyser.services']);


angular.module('volleystatsanalyser.services',[]).factory('DvwFile', function($resource) {
    return $resource('/dvw/files');
});

angular.module('volleystatsanalyser').config(function($stateProvider) {
    $stateProvider.state('files', { // state for showing all movies
        url: '/files',
        templateUrl: '/partials/games.html',
        controller: 'DvwFileController'
    }).state('viewMovie', { //state for showing single movie
        url: '/movies/:id/view',
        templateUrl: 'partials/movie-view.html',
        controller: 'MovieViewController'
    }).state('newMovie', { //state for adding a new movie
        url: '/movies/new',
        templateUrl: 'partials/movie-add.html',
        controller: 'MovieCreateController'
    }).state('editMovie', { //state for updating a movie
        url: '/movies/:id/edit',
        templateUrl: 'partials/movie-edit.html',
        controller: 'MovieEditController'
    });
}).run(function($state) {
    $state.go('files'); //make a transition to movies state when app starts
});

angular.module('volleystatsanalyser.controllers', [])
    .controller('DvwFileController', function($scope, $state, popupService, $window, DvwFile) {
    $scope.files = DvwFile.query(); //fetch all movies. Issues a GET to /api/movies

    $scope.deleteMovie = function(movie) { // Delete a movie. Issues a DELETE to /api/movies/:id
        if (popupService.showPopup('Really delete this?')) {
            movie.$delete(function() {
                $window.location.href = ''; //redirect to home
            });
        }
    };
}).controller('MovieViewController', function($scope, $stateParams, Movie) {
    $scope.movie = Movie.get({ id: $stateParams.id }); //Get a single movie.Issues a GET to /api/movies/:id
}).controller('MovieCreateController', function($scope, $state, $stateParams, Movie) {
    $scope.movie = new Movie();  //create new movie instance. Properties will be set via ng-model on UI

    $scope.addMovie = function() { //create a new movie. Issues a POST to /api/movies
        $scope.movie.$save(function() {
            $state.go('movies'); // on success go back to home i.e. movies state.
        });
    };
}).controller('MovieEditController', function($scope, $state, $stateParams, Movie) {
    $scope.updateMovie = function() { //Update the edited movie. Issues a PUT to /api/movies/:id
        $scope.movie.$update(function() {
            $state.go('movies'); // on success go back to home i.e. movies state.
        });
    };

    $scope.loadMovie = function() { //Issues a GET request to /api/movies/:id to get a movie to update
        $scope.movie = Movie.get({ id: $stateParams.id });
    };

    $scope.loadMovie(); // Load a movie which can be edited on UI
});