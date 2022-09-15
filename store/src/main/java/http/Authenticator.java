package http;

import com.sun.net.httpserver.BasicAuthenticator;

public class Authenticator extends BasicAuthenticator {
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";

    public Authenticator(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return username.equals(USERNAME) && password.equals(PASSWORD);
    }
}
