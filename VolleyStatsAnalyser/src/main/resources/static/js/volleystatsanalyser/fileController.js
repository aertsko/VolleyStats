function Files($scope, $http) {
    $http.get('/dvw/files').
        success(function(data) {
            $scope.files = data;
        });
}
