import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ClientAuthenticator extends Authenticator {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("root", "root".toCharArray());
    }
}
