(function(){
    let app = angular.module('app',['ui.router']);

    app.config(function($stateProvider){
        $stateProvider
            .state('login', {
                url: "/home",
                templateUrl: 'views/home.html',
                controller: 'loginController'
            })
            .state('createCustomer',{
                url:"/register",
                templateUrl: 'views/createCustomer.html',
                controller: 'createCustomerController'
            })
            .state('accountList',{
                url:'/customer/:id/accounts',
                templateUrl: 'views/accountList.html',
                controller: 'accountsController'
            })
            .state('createAccount',{
                url:'/customer/:id/accounts/new',
                templateUrl: 'views/createAccount.html',
                controller: 'createAccountController'
            })
            .state('accountView',{
                url:'/customer/:id/account/:accountId',
                templateUrl: 'views/accountView.html'
            })
            .state('statement',{
                url:'/customer/:id/account/:accountId/statement',
                templateUrl: 'views/statement.html'
            })
            .state('transactionMap',{
                url:'/customer/:id/account/:accountId/transactionMap',
                templateUrl: 'views/transactionMap.html'
            })
    });

    app.value('logout',function(){
        $state.go('login');
        return true;
    });
}());