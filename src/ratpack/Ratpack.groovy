import static ratpack.groovy.Groovy.ratpack

ratpack {
	bindings {

	}

	handlers {
		handler {
			if (request.headers['Authorization'] != "Token faketoken") {
				response.status(401)
				//We must send some response or the request will hang.
				response.send()
			} else {
				//We can choose to do nothing but allow the next handler in the chain to deal with the request.
				println "next"
				next()
			}
		}


		handler {
			println "hellow"
			render "Hello World"
		}


		//		assets "public"
	}
}
