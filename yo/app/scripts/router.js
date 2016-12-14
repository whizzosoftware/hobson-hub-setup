// Filename: router.js
define([
    'jquery',
    'underscore',
    'backbone',
    'views/app'
], function($, _, Backbone, AppView) {

    var AppRouter = Backbone.Router.extend({

        routes: {
            '': 'showStart',
            'email': 'showEmail',
            'password': 'showPassword',
            'complete': 'showComplete'
        },

        initialize: function() {
            this.appView = new AppView();
            this.appView.render();
            Backbone.history.start();
        },

        showStart: function() {
            this.appView.showStart();
        },

        showEmail: function() {
            this.appView.showEmail();
        },

        showPassword: function() {
            this.appView.showPassword();
        },

        showComplete: function() {
            this.appView.showComplete();
        }

    });

    return AppRouter;
});
