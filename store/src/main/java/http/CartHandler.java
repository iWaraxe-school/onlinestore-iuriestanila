package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

public class CartHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        if (httpExchange.getRequestMethod().equalsIgnoreCase("POST")) {
            final InputStream requestBody = httpExchange.getRequestBody();

            final byte[] requestBodyContent = requestBody.readAllBytes();

            System.out.println("\nTo the cart was added the following product: "
                    + new String(requestBodyContent));
            httpExchange.sendResponseHeaders(200, 0);

        } else {
            httpExchange.sendResponseHeaders(405, 0);
        }

        httpExchange.close();
    }
}
