
/*This js is responsible to route URLs and call relevant methods to render data on UI */

/* Globally defined URL of Web-Service */
var ServiceURL = 'http://172.168.1.103:8080/dpr-data/rest/';



var ApplicationRouter = Backbone.Router.extend({
    routes: {
        "": "list"
    }, 

    /*Method to retrive Admin home details */

    list: function () { 
        
    }

});


