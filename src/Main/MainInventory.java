package Main;

import View_Controller.MainScreen;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import Model.*;


public class MainInventory extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Inventory inv = new Inventory();
        addTestData(inv);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/MainScreen.fxml"));
        View_Controller.MainScreen controller = new View_Controller.MainScreen(inv);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    void addTestData(Inventory inv){
        //InHouse Sample Data
        Part in_housePart1 = new InHouse(1, "Part 1", 7, 1.50, 1, 150, 993881);
        Part in_housePart2 = new InHouse(2, "Part 2", 6, 10.00, 1, 100, 993882);
        Part in_housePart3 = new InHouse(3, "Part 3", 5, 4.00, 50, 1000, 993883);
        Part in_housePart4 = new InHouse(4, "Part 4", 4, 25.50, 5, 65, 993884);
        Part in_housePart5 = new InHouse(5, "Part 5", 3, 3.25, 5, 75, 993885);

        inv.addPart(in_housePart1);
        inv.addPart(in_housePart2);
        inv.addPart(in_housePart3);
        inv.addPart(in_housePart4);
        inv.addPart(in_housePart5);

        //Outsourced Sample Data
        Part out_sourcedPart1 = new Outsourced(1, "Part 1", 4.00, 3, 1, 10, "Gitlab");
        Part out_sourcedPart2 = new Outsourced(2, "Part 2", 10.00, 4, 1, 10, "Ansible");
        Part out_sourcedPart3 = new Outsourced(3, "Part 3", 3.00, 5, 1, 10, "Kubernetes");
        Part out_sourcedPart4 = new Outsourced(4, "Part 4", 4.99, 7, 1, 10, "Docker");
        Part out_sourcedPart5 = new Outsourced(5, "Part 5", 5.99, 150, 1, 1000, "Linux");

        inv.addPart(out_sourcedPart1);
        inv.addPart(out_sourcedPart2);
        inv.addPart(out_sourcedPart3);
        inv.addPart(out_sourcedPart4);
        inv.addPart(out_sourcedPart5);

        Product product1 = new Product(1, "Product 1", .99, 1, 3, 5);
        product1.addAssociatedPart(in_housePart1);
        product1.addAssociatedPart(out_sourcedPart1);
        inv.addProduct(product1);
        Product product2 = new Product(2, "Product 2", .99, 2, 4, 6);
        product2.addAssociatedPart(in_housePart2);
        product2.addAssociatedPart(out_sourcedPart2);
        inv.addProduct(product2);
        Product product3 = new Product(3, "Product 3", .99, 3, 5, 7);
        product2.addAssociatedPart(in_housePart3);
        product3.addAssociatedPart(out_sourcedPart3);
        inv.addProduct(product3);
        inv.addProduct(new Product(4, "Product 4", .99, 4, 10, 8));
        inv.addProduct(new Product(4, "Product 5", .99, 4, 24, 50 ));
    }
}
