import ratpack.session.Session
import ratpack.session.SessionModule
import ratpack.session.store.MapSessionsModule
import ratpack.session.store.SessionStorage

import static ratpack.groovy.Groovy.ratpack

ratpack {
	bindings {
		add SessionModule
		add new MapSessionsModule(1000, 360)
	}

	handlers {

		handler {
			if (request.headers['Authorization'] != "Token faketoken") {
				response.status(401)
				//We must send some response or the request will hang.
				response.send()
			} else {

				def sessionStorage = context.request.get(SessionStorage)
				sessionStorage.put("example", "Galaxy")

				next()
			}
		}

		handler {
			def sessionStorage = context.request.get(SessionStorage)
			def place = sessionStorage.get("example") ?: "World"
			render "Hello $place"
		}
	}
}
