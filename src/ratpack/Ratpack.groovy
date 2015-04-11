import org.pac4j.oauth.client.TwitterClient
import ratpack.pac4j.Pac4jModule
import ratpack.session.SessionModule
import ratpack.session.store.MapSessionsModule

import static ratpack.groovy.Groovy.ratpack


ratpack {
	bindings {

		String key = ""
		String secret =""
		add SessionModule
		add new MapSessionsModule(1000, 360)
		add new Pac4jModule(new TwitterClient(key, secret), new SecureAllAuthorizer())
	}

	handlers {
		handler {
			render "Hello Twitter"
		}
	}
}
