import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientStore {
    public static void main(String[] args) {
        ClientStore clientStore = new ClientStore();
        clientStore.httpInteraction();
    }

    @SneakyThrows
    public void addToCartHttp(String productName) {
        HttpClient client = HttpClient.newBuilder()
                .authenticator(new ClientAuthenticator())
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(productName))
                .uri(URI.create("http://localhost:8080/cart"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("The status code is: " + response.statusCode());
    }

    @SneakyThrows
    public void getProductsHttp() {
        HttpClient client = HttpClient.newBuilder()
                .authenticator(new ClientAuthenticator())
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/product"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("The status code is: " + response.statusCode());
        System.out.println(response.body());
    }

    @SneakyThrows
    public void httpInteraction() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean flagHttp = true;
        boolean flagHttp2 = true;
        while (flagHttp) {
            System.out.println("Dear customer enter the command add (for adding to cart)" +
                    " print (for printing the products) or quit: ");
            String command = reader.readLine();
            switch (command) {
                case "add":
                    flagHttp2 = true;
                    while (flagHttp2) {
                        System.out.println("Which product do you want to add to the cart?");
                        String orderedProductHttp = reader.readLine();
                        addToCartHttp(orderedProductHttp);
                        flagHttp2 = false;
                    }
                    break;
                case "print":
                    getProductsHttp();
                    break;
                case "quit":
                    flagHttp = false;
                    break;
                default:
                    System.out.println("The entered command doesn't exist.");
            }
        }
    }
}
