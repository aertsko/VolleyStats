/**
 * Created by Sandeep on 01/06/14.
 */

angular.module('movieApp.services',[]).factory('Movie',function($resource){
    return $resource('http://localhost:8080/dvw/files',{
        update: {
            method: 'PUT'
        }
    });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});