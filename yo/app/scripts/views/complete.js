// Filename: views/complete.js
define([
    'jquery',
    'underscore',
    'backbone',
    'toastr',
    'services/hub',
    'views/footer',
    'i18n!nls/strings',
    'text!templates/complete.html'
], function($, _, Backbone, toastr, HubService, FooterView, strings, completeTemplate) {

    return Backbone.View.extend({
        template: _.template(completeTemplate),

        events: {
            'next': 'onNext' // this event is fired by the footer view
        },

        initialize: function(options) {
            this.footerView = new FooterView({previousTab: 'password', activeTab: 'complete', nextTab: null, showBack: false});
            this.password = options.password;
        },

        remove: function() {
            this.footerView.remove();
            Backbone.View.prototype.remove.call(this);
        },

        render: function() {
            this.$el.append(this.template({ password: this.password, strings: strings }));
            this.$el.append(this.footerView.render().el);
            return this;
        },

        onNext: function() {
          HubService.setSetupComplete('local').success(function(response) {
            window.location.replace('/console/index.html');
          }).fail(function(response) {
            window.location.replace('/console/index.html');
          });
        }

    });

});
