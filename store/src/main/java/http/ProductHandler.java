package http;

import by.issoft.store.Store;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class ProductHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        if(httpExchange.getRequestMethod().equalsIgnoreCase("GET")){

            byte[] storeBytes = Store.getStoreInstance().toString().getBytes();
            OutputStream outputStreamResponse = httpExchange.getResponseBody();
            httpExchange.sendResponseHeaders(200, storeBytes.length);
            outputStreamResponse.write(storeBytes);
            outputStreamResponse.close();
        }
    }
}
