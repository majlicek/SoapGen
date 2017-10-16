package sk.hlad.velky.soap;

import javax.xml.ws.Endpoint;

/**
 *
 * Created by Milan Chrastina on 01.03.2016.
 */
public class Server {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/recipes",
                new RecipesService());
    }
}