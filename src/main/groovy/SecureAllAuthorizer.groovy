import ratpack.handling.Context
import ratpack.pac4j.AbstractAuthorizer

class SecureAllAuthorizer extends AbstractAuthorizer {

	@Override
	boolean isAuthenticationRequired(Context context) {
		return true
	}
}
