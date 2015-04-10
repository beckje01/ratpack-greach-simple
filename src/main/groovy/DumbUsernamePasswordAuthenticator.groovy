import groovy.util.logging.Slf4j
import org.pac4j.core.exception.CredentialsException
import org.pac4j.http.credentials.UsernamePasswordAuthenticator
import org.pac4j.http.credentials.UsernamePasswordCredentials

@Slf4j
class DumbUsernamePasswordAuthenticator implements UsernamePasswordAuthenticator {
	@Override
	void validate(UsernamePasswordCredentials credentials) {

		if (credentials.username != 'jeff'){
			throwsException("Only jeff is allowed.")
		} else {
			if(credentials.password != 'password'){
				throwsException("The only password is password .")
			}
		}

	}

	protected void throwsException(final String message) {
		log.error(message);
		throw new CredentialsException(message);
	}
}
