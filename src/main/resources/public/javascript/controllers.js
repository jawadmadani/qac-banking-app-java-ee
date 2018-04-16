angular.module('app')
    .controller('loginController',function($scope,$http,$location,$timeout,$state){
        $scope.attempts = 0;
        $scope.username = "";
        $scope.password = "";

        $scope.goToRegister = function(){
            $state.go('createCustomer');
        };

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
    .controller('createCustomerController',function($scope,$http,$location,$state) {
        $scope.username = "";
        $scope.password = "";
        $scope.checkPwd = "";

        $scope.log = function(){
          console.log('Running sign in protocol');
          console.log('For username: ' + $scope.username);
        };

        $scope.validateRegistration = function(){
            if($scope.username === ""){
                window.alert("Username can't be empty");
                console.log("Invalid: Username was empty");
                return false;
            }
            else if($scope.password === ""){
                window.alert("Password can't be empty");
                console.log("Invalid: Password was empty");
                return false;
            }
            else if ($scope.checkPwd === ""){
                window.alert("Must enter the same password in both fields");
                console.log("Invalid: Check password mismatch");
                return false;
            }
            else if ($scope.username !== ""){
                checkUniqueUsername();
            }
            else {
                console.log("Valid: Starting customer creation");
                $scope.createCustomer();
                console.log("customer created");
                return true;
            }
        };

        $scope.checkUniqueUsername = function () {
            $http.get($location.url).then(function(response){
                if (response !== null){
                    window.alert("Username is taken, try a different one");
                    console.log("Username in use");
                    return false;
                }
            });
        };

        $scope.createCustomer = function(){
            $http.post($location.url).then(function(response){
                if (response !== null){
                    console.log('Function run');
                    $state.go('login');
                }
                else {
                    console.log('Response is not null, error in createCustomer()')
                }
            });
        };
    })
    .controller('accountsController',function($scope,$http,$location,$state,logout){
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
    .controller('accountController',function($scope,$http,$location,$state,logout){
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