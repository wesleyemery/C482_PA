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
        primaryStage.setTitle("Main Screen");

        primaryStage.show();

    }

    void addTestData(Inventory inv){
        //InHouse Sample Data
        Part in_housePart1 = new InHouse(1, "inPart ih33", 80, 1.50, 1, 150, 66410);
        Part in_housePart2 = new InHouse(2, "inPart ih2", 100, 10.00, 1, 100, 66411);
        Part in_housePart3 = new InHouse(3, "inPart ih11", 5, 4.00, 5, 1000, 66412);
        Part in_housePart4 = new InHouse(4, "inPart ih9", 75, 25.50, 5, 200, 66413);
        Part in_housePart5 = new InHouse(5, "inPart ih5", 15, 3.25, 5, 75, 66414);

        inv.addPart(in_housePart1);
        inv.addPart(in_housePart2);
        inv.addPart(in_housePart3);
        inv.addPart(in_housePart4);
        inv.addPart(in_housePart5);

        //Outsourced Sample Data
        Part out_sourcedPart1 = new Outsourced(6, "outPart os6", 4.00, 3, 1, 10, "Gitlab");
        Part out_sourcedPart2 = new Outsourced(7, "outPart os12", 10.00, 4, 1, 10, "Ansible");
        Part out_sourcedPart3 = new Outsourced(8, "outPart os3", 3.00, 5, 1, 10, "Kubernetes");
        Part out_sourcedPart4 = new Outsourced(9, "outPart os14", 4.99, 7, 1, 10, "Docker");
        Part out_sourcedPart5 = new Outsourced(10, "outPart os25", 5.99, 150, 1, 1000, "Linux");

        inv.addPart(out_sourcedPart1);
        inv.addPart(out_sourcedPart2);
        inv.addPart(out_sourcedPart3);
        inv.addPart(out_sourcedPart4);
        inv.addPart(out_sourcedPart5);

        Product product1 = new Product(11, "CodeRepository", 4.99, 10, 5, 50);
        product1.addAssociatedPart(in_housePart1);
        product1.addAssociatedPart(out_sourcedPart1);
        inv.addProduct(product1);
        Product product2 = new Product(12, "ConfigManagement", 20.00, 15, 1, 50);
        product2.addAssociatedPart(in_housePart2);
        product2.addAssociatedPart(out_sourcedPart2);
        inv.addProduct(product2);
        Product product3 = new Product(13, "Cluster", 19.99, 10, 5, 100);
        product3.addAssociatedPart(in_housePart3);
        product3.addAssociatedPart(out_sourcedPart3);
        inv.addProduct(product3);
        Product product4 = new Product(14, "Containers", 100.00, 15, 10, 100);
        product4.addAssociatedPart(in_housePart4);
        product4.addAssociatedPart(out_sourcedPart4);
        inv.addProduct(product4);
        Product product5 = new Product(15, "OperatingSystem", .99, 20, 20, 100 );
        product5.addAssociatedPart(in_housePart5);
        product5.addAssociatedPart(out_sourcedPart5);
        inv.addProduct(product5);
    }
}
