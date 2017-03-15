package greycat.samples;

import greycat.Type;
import greycat.Graph;
import greycat.GraphBuilder;
import greycat.Node;

public class Minimal {

    public static void main(String[] args) {

        //Create a minimal graph with default configuration
        Graph g = new GraphBuilder().build();

        //Connect the graph
        g.connect(isConnected -> {
            System.out.println("Connected : " + isConnected);

            Node sensor0 = g.newNode(0, 0); //create new node for world 0 and time 0
            sensor0.set("snesorId", Type.INT, 12); //set the id attribute as an integer
            sensor0.set("name",Type.STRING, "sensor0"); //set the name attribute as a string

            System.out.println(sensor0); //print {"world":0,"time":0,"id":1,"id":19,"name":"sensor0"}

            Node room0 = g.newNode(0, 0); //create new node for world 0 and time 0
            room0.set("name",Type.STRING, "room0"); //set the name attribute
            room0.addToRelation("sensors", sensor0); //add the sensor0 to the relation sensors of room0

            System.out.println(room0); //print {"world":0,"time":0,"id":2,"name":"room0","sensors":[1]}

            room0.relation("sensors", (Node[] sensors) -> { //iterate over the saved sensors relation
                System.out.println("Relationship Sensors:");
                for (Node sensor : sensors) {
                    System.out.println("\t" + sensor.toString());
                }
                g.disconnect(result -> {
                    System.out.println("GoodBye!");
                });

            });

        });
    }

}