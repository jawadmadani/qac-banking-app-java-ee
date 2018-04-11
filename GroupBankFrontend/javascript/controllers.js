angular.module('app')
    .controller('loginController',function($scope,$http,$location,$timeout){
        $scope.attempts = 0;
        $scope.usermae = "";
        $scope.password = "";

        $scope.validateLogin = function(){
            if($scope.attempts === 3){
                window.alert("You have been locked out. Come back in 5 minutes");
                $timeout(function(){
                    $scope.attempts = 0;
                },30); //Someone make this better
                return false;
            }
            if($scope.username === ""){
                window.alert("Username can't be empty");
                return false;
            }
            else if($scope.password === ""){
              window.alert("Password can't be empty");
              return false;
            }
        };

        $scope.signIn = function(){
            $http.get($location.url()).then(function(response){
                $scope.customer = response.data;
                if($scope.customer === ""){
                    $scope.attempts+=1;
                    $state.go('login');
                }
                else{
                    $location.path('/customer/' + $scope.customer.id + '/accounts');
                }
            })
        };
    })
    .controller('createCustomer',function($scope,$http,$location) {
            $scope.checkUniqueUsername = function () {
                //placeholder - GET customers with filter, if username exists return false
            };

            $scope.createCustomer = function(){
                //placeholder - POST
            };
        }
    )
    .controller('accountsController',function($scope,$http,$location,logout){
        $scope.getAccounts = function(){
            //placeholder - GET - shipwreck example required another service to retrieve all, not sure that is necessary here
        };

        $scope.accounts = getAccounts();

        $scope.signOut = function(){
            logout();
        };

        $scope.removeAccount = function(){
            //placeholder - DELETE
        };

        $scope.addAccount = function(){
            //placeholder - POST - also requires new view for add form
        };

        $scope.editAccount = function(){
            //placeholder - PUT - also requires new view for edit form
        }
    })
    .controller('accountController',function($scope,$http,$location,logout){
       $scope.getTransactions = function(){
           //placeholder - GET
       };

       $scope.transactions = getTransactions();

       $scope.signOut = function(){
           logout();
       };

       $scope.mapTransactions = function(){
           //placeholder ----- this is the map API. Good luck
       };

       $scope.balancePlotTransactions = function(){
           //placeholder ----- this where google charts magic is done
       };
    });