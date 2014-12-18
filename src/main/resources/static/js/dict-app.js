var dictManagerModule = angular.module('dictManagerApp', ['ngAnimate']);

dictManagerModule.controller('dictManagerController', function ($scope,$http) {
	
	var urlBase="";
	$scope.toggle=true;
	$scope.selection = [];
	$scope.parses=['N','D', 'A', 'V', 'P', 'B','I','C'];
	$http.defaults.headers.post["Content-Type"] = "application/json";

    function findAllEntries() {
        //get all greekLexemes and display initially
        $http.get(urlBase + '/greekLexemes/search/findByPars?pars=B').
            success(function (data) {
                if (data._embedded != undefined) {
                    $scope.greekLexemes = data._embedded.greekLexemes;
                } else {
                    $scope.greekLexemes = [];
                }
                for (var i = 0; i < $scope.greekLexemes.length; i++) {
                    //if ($scope.greekLexemes[i].taskStatus == 'COMPLETED') {
                        $scope.selection.push($scope.greekLexemes[i].Id);
                    //}
                }
                $scope.lex="";
                $scope.pars="";
                $scope.semanid="";
                $scope.toggle='!toggle';
            });
    }

    findAllEntries();

	//add a new greekLexeme
	$scope.addEntry = function addEntry() {
		if($scope.lex=="" || $scope.pars=="") {
            alert("Insufficient Data!");
        } else {
            $http.post(urlBase + '/greekLexemes', {
             lex: $scope.lex,
             pars: $scope.pars,
             semanid: $scope.semanid
         }).
		  success(function(data, status, headers) {
			 alert("Lexeme added");
             var newEntryUri = headers()["location"];
             console.log("Might be good to GET " + newEntryUri + " and append the lexeme.");
             // Refetching EVERYTHING every time can get expensive over time
             // Better solution would be to $http.get(headers()["location"]) and add it to the list
             findAllEntries();
		    });
		}
	};
		
	// toggle selection for a given greekLexeme by greekLexeme id
	  $scope.toggleSelection = function toggleSelection(entryUri) {
	    var idx = $scope.selection.indexOf(entryUri);
/*
	    // is currently selected
        // HTTP PATCH to ACTIVE state
	    if (idx > -1) {
	      $http.patch(entryUri, { taskStatus: 'ACTIVE' }).
		  success(function(data) {
		      alert("Task unmarked");
              findAllTasks();
		    });
	      $scope.selection.splice(idx, 1);
	    }

	    // is newly selected
        // HTTP PATCH to COMPLETED state
	    else {
	      $http.patch(taskUri, { taskStatus: 'COMPLETED' }).
		  success(function(data) {
			  alert("Task marked completed");
              findAllTasks();
		    });
	      $scope.selection.push(taskUri);
	    }
*/

	  };
/*
	// Archive Completed Tasks
	  $scope.archiveTasks = function archiveTasks() {
          $scope.selection.forEach(function(taskUri) {
              if (taskUri != undefined) {
                  $http.patch(taskUri, { taskArchived: 1});
              }
          });
          alert("Successfully Archived");
          console.log("It's risky to run this without confirming all the patches are done. when.js is great for that");
          findAllTasks();
	  };
*/

});

//Angularjs Directive for confirm dialog box
dictManagerModule.directive('ngConfirmClick', [
	function(){
         return {
             link: function (scope, element, attr) {
                 var msg = attr.ngConfirmClick || "Are you sure?";
                 var clickAction = attr.confirmedClick;
                 element.bind('click',function (event) {
                     if ( window.confirm(msg) ) {
                         scope.$eval(clickAction);
                     }
                 });
             }
         };
 }]);