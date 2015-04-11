import org.pac4j.oauth.client.GitHubClient
import org.pac4j.oauth.profile.github.GitHubProfile
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
		add new Pac4jModule(new GitHubClient(key, secret), new SecureAllAuthorizer())
	}

	handlers {
		handler {
			GitHubProfile profile = getUserProfile(context)

			render "Hello ${profile.username} you have ${profile.followers} followers and are using ${profile.diskUsage} Disk"
		}
	}
}




//Available by extending Pac4jProfileHandler
protected GitHubProfile getUserProfile(Context context) {
	return (GitHubProfile)((SessionStorage)context.getRequest().get(SessionStorage.class)).get("ratpack.pac4j-user-profile");
}