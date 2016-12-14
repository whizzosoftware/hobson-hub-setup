// Filename: services/hub.js
define([
	'jquery',
], function($) {
	var HubService = {

		sendTestEmail: function(hubId, model) {
			var url = '/api/v1/hubs/' + hubId + '/configuration/sendTestEmail';
			var json = {values: model.toJSON()};
			return $.ajax(url, {
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(json),
				dataType: 'json'
			});
		},

		setPassword: function(hubId, password) {
			var url = '/api/v1/hubs/' + hubId + '/password';
			var data = {currentPassword: 'password', newPassword: password};
			return $.ajax(url, {
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(data),
				dataType: 'json'
			});
		},

    setSetupComplete: function(hubId) {
      var url = '/api/v1/hubs/' + hubId + '/configuration';
      var data = {values: {setupComplete: true}};
      return $.ajax(url, {
        type: 'PATCH',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json'
      });
    },

		getPluginImage: function(url) {
			return $.ajax({
				url: url + '?base64=true',
				type: 'GET'
			});
		},

		installPlugin: function(url) {
			return $.ajax(url, {
				type: 'POST'
			});
		}

	};

	return HubService;
});
