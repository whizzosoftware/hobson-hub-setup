package com.whizzosoftware.hobson.setup;

import com.whizzosoftware.hobson.webconsole.ClassLoaderOverrideDirectory;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.Reference;
import org.restlet.routing.Router;

public class SetupApplication extends Application {
    public static final String PATH = "/setup";

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router();
        router.attach("/", new ClassLoaderOverrideDirectory(getContext(), "clap://class/www/", getClass().getClassLoader()));
        return router;
    }

    @Override
    public void handle(Request request, Response response) {
        Reference ref = request.getResourceRef();
        if (PATH.equals(ref.getPath()) || (PATH + "/").equals(ref.getPath())) {
            response.redirectPermanent(PATH + "/index.html");
        } else {
            super.handle(request, response);
        }
    }
}
