package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    //Product Fields
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int productID;
    private String productName;
    private double productPrice;
    private int productInvLevel;
    private int productMin;
    private int productMax;

    //Product Constructor
        public Product(int productID, String productName, double productPrice, int productInvLevel, int productMin, int productMax) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productInvLevel = productInvLevel;
        this.productMin = productMin;
        this.productMax = productMax;
    }

    //Product Getter and Setter
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductInvLevel() {
        return productInvLevel;
    }

    public void setProductInvLevel(int productInvLevel) {
        this.productInvLevel = productInvLevel;
    }

    public int getProductMin() {
        return productMin;
    }

    public void setProductMin(int productMin) {
        this.productMin = productMin;
    }

    public int getProductMax() {
        return productMax;
    }

    public void setProductMax(int productMax) {
        this.productMax = productMax;
    }

    //Product Methods
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part selectedPart){
        return associatedParts.remove(selectedPart);
    }

    public ObservableList<Part> getAssociatedParts(){
        return associatedParts;
    }
}
