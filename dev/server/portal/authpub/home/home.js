angular.module('home', ['auth0', 'toastr'])
.controller( 'HomeCtrl', function ($scope, auth, profileResource, toastr, $http, $location, $window, store) {

  $scope.auth = auth;

  $scope.logout = function() {
    var homeButton = angular.element(document.querySelector('#home'));
    homeButton.removeClass('active');
    var logoutButton = angular.element(document.querySelector('#logout'));
    logoutButton.addClass('active');
    auth.signout();
    store.remove('token');
    $location.path('/login');
  };

  $scope.changedList = [];  
  $scope.journalsList = [];  

  _fillPublicationsData();

  $scope.submitChanges = function() {  
           
    var method = "POST";
    var url = API_SERVER_URL + '/mng';
    if ($scope.changedList.length > 0) {                          
                    
                   	 $http({  
                       	 method : method,  
                      	  url : url,  
                       	 data : angular.toJson($scope.changedList),  
                       	 headers : {  
                       	     'Content-Type' : 'application/json'  
                       	 }  
                   	 }).then( _success, _error );  
    }
  };  

  $scope.update = function(journals) {

      $scope.changedList.push(journals.id);
			
  };  
   
  function _fillPublicationsData() {  

			$http({
			    method: 'GET',
			    url: API_SERVER_URL +  '/listing/' + $scope.auth.profile.user_id.split('|')[1],  
			    headers: {
                            'Accept': 'application/json' 
                            }
  			  }).then(function successCallback(response) {  

			angular.forEach(response.data.content, function(journals) {

                        	$scope.journalsList.push(journals);
			});

                    }, function errorCallback(response) {  
                        console.log(response.statusText);  
                    });  
  }  

  //
  // PRIVATE METHODS
  //
  function _success(response) {  
  }  
           
  function _error(response) {  
    console.log(response.statusText);  
  }  
           
});
