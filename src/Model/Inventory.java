package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    //Inventory Fields
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    //Inventory Getters
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    //Inventory Add Methods
    public static void addPart(Part part){
        allParts.add(part);
    }
    public static void addProduct(Product product){ allProducts.add(product); }

    //Inventory Lookup Method By ID
    private Part lookupPart(int partId){
        int index = -1;
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getPartID() == partId) {
                index = i;
            }
        }
        return allParts.get(index);
    }

    public Product lookupProduct(int productId){
        int index = -1;
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getProductID() == productId) {
                index = i;
            }
        }
        return allProducts.get(index);
    }

    //Inventory Lookup Method By Name
    public ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> listArray = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); i++){
            if (allParts.get(i).getPartName().equals(partName)){
               listArray.add(allParts.get(i));
            }
        }
        return listArray;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> listArray = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getProductName().equals(productName)) {
                listArray.add(allProducts.get(i));
            }
        }
        return listArray;
    }

    //Inventory Update Methods
    public void updatePart(int index, Part newPart){
        allParts.set(index, newPart);
    }
    public void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

    //Inventory Delete Methods
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }


}