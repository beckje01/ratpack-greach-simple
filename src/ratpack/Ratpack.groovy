import ratpack.pac4j.Pac4jModule
import ratpack.session.Session
import ratpack.session.SessionModule
import ratpack.session.store.MapSessionsModule
import ratpack.session.store.SessionStorage
import org.pac4j.http.client.*

import static ratpack.groovy.Groovy.ratpack

ratpack {
	bindings {
		add SessionModule
		add new MapSessionsModule(1000, 360)
		add new Pac4jModule(new BasicAuthClient(new DumbUsernamePasswordAuthenticator()), new SecureAllAuthorizer())
	}

	handlers {
		handler {
			render "Hello World"
		}
	}
}
