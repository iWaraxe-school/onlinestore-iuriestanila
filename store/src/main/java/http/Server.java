package http;

import com.sun.net.httpserver.HttpServer;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;

public class Server {
    HttpServer server;
    @SneakyThrows
    public void startServer() {
        server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/cart", new CartHandler()).setAuthenticator(new Authenticator("cart"));
        server.createContext("/product", new ProductHandler()).setAuthenticator(new Authenticator("products"));

        server.setExecutor(null);
        server.start();
    }

    public void stopServer() {
        server.stop(0);
    }
}
