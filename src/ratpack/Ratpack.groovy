import org.pac4j.oauth.client.TwitterClient
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.handling.Context
import ratpack.pac4j.Pac4jModule
import ratpack.session.SessionModule
import ratpack.session.store.MapSessionsModule
import ratpack.session.store.SessionStorage

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
			TwitterProfile profile = getUserProfile(context)
			render "Hello ${profile.username}"
		}
	}
}




//Available by extending Pac4jProfileHandler
protected TwitterProfile getUserProfile(Context context) {
	return (TwitterProfile)((SessionStorage)context.getRequest().get(SessionStorage.class)).get("ratpack.pac4j-user-profile");
}