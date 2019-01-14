package com.keitam.kayak.controller;

import com.keitam.kayak.model.CustomerCart;
import com.keitam.kayak.model.KayakProduct;
import com.keitam.kayak.repository.ProductService;
import com.keitam.kayak.util.KayakUtil;
import com.keitam.kayak.util.Notification;
import com.keitam.kayak.util.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Controller
public class MainController implements Initializable {
    @FXML private AnchorPane root;
    @FXML private GridPane topRightGridPane;
    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;
    @FXML private Button signIn;
    @FXML private Button signOut;
    @FXML private Button account;
    @FXML private Button removeItem;
    @FXML private Label copyRight;
    @FXML private Label discountPer;
    @FXML private Label discountAmount;
    @FXML private Label itemsTotalPrice;
    @FXML private Label totalItems;
    @FXML private Label itemsPrice;
    @FXML private ComboBox<Integer> numQuantity;

    @FXML private TableView<CustomerCart> shoppingCartTable;
    @FXML private TableColumn<CustomerCart, String> itemNameColumn;
    @FXML private TableColumn<CustomerCart, Integer> itemQuantityColumn;
    @FXML private TableColumn<CustomerCart, Double> itemPriceColumn;

    private ObservableList<KayakProduct> kayakProducts = FXCollections.observableArrayList();
    private ObservableList<Button> buttonsList = FXCollections.observableArrayList();
    private ObservableList<CustomerCart> carts = FXCollections.observableArrayList();

    private final ProductService productService;
    private Long itemSelected;

    @Lazy
    private StageManager stageManager;

    @FXML
    public void initialize(URL location, ResourceBundle resources){
        copyRight.setText(KayakUtil.getFooterText());
        changeTopRightPaneBehavior();

        loadProductsInFlowPane(kayakProducts);
        addSelectedProductInCart();
        updateCart();
        switchScene();
    }

    /**
     * addSelectedProductInCart method.
     * When the user click on an item, the item will
     * be pass into the getSelectedItem method to be
     * added into cart
     */
    private void addSelectedProductInCart(){
        for (int i = 0; i < buttonsList.size(); i++) {
            final int location = i;
            buttonsList.get(i).setOnAction(b -> kayakProducts.forEach(p -> {
                if (Integer.parseInt(buttonsList.get(location).getText()) == p.getProductID()) {
                    CustomerCart item = new CustomerCart(p.getProductID(), p.getpName(), 1, p.getPrice(), KayakUtil.getProductImage(p.getImageName(), 100, 75));
                    getSelectedItem(item);
        }
    }));
        }
    }

    private void getSelectedItem(CustomerCart itemToBeAdded) {
        if (carts.size() != 0) {
            for (CustomerCart item : carts) {
                if (item.getItemName().equals(itemToBeAdded.getItemName())) {
                    Notification.operationNotPerform("Operation",
                            (itemToBeAdded.getItemName() + " is already in your cart. " +
                                    "Select\nit in your car and update it quantity."));
                    return;
                }
            }
        }

        carts.add(itemToBeAdded);
        displayFinance();
    }

    private void updateCart() {
        shoppingCartTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int itemPosition = shoppingCartTable.getSelectionModel().getSelectedIndex();
            CustomerCart item = carts.get(itemPosition);
            itemSelected = item.getItemID();
            kayakProducts.forEach(product -> {
                if (product.getProductID() == item.getItemID()){
                    int productQuantity = product.getQuantity();
                    numQuantity.getItems().clear();
                    for (int i = 1; i <= productQuantity; i++){
                        numQuantity.getItems().add(i);
                    }
                }
            });
            numQuantity.getSelectionModel().select(item.getQuantity() - 1);
        });

        numQuantity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (numQuantity.getSelectionModel().getSelectedItem() != null) {
                carts.forEach(item -> {
                    if (item.getItemID() == itemSelected){
                        item.setQuantity(newValue);
                        double itemPrice = Objects.requireNonNull(KayakUtil.getProductOriginalPrice(kayakProducts, itemSelected)).getPrice();
                        item.setPrice(KayakUtil.format(item.getQuantity() * itemPrice));
                        displayFinance();
                    }
                });
            }
        });

        removeItem.setOnAction(e -> {
            carts.remove(getIndex(itemSelected));
            displayFinance();
        });
    }

    /**
     * @param products
     * List of product need to be loaded into the flow pane.
     * Products image and id are taking, and added into a new
     * button and that button is added to flow pane and into a
     * list of buttons.
     */
    @FXML
    private void loadProductsInFlowPane(List<KayakProduct> products) {
        Button button;
        for (KayakProduct product : products){
            button = new Button();
            button.setGraphic(KayakUtil.getProductImage(product.getImageName(), 150, 135));
            button.setText("" + product.getProductID());
            buttonsList.add(button);
            flowPane.getChildren().addAll(button, new Label("\tPrice: " + KayakUtil.NUMBER_FORMAT.format(product.getPrice()) + "" +
                    "\tQt: " + product.getQuantity()));
        }
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    /**
     * When the login text is "Sign In / Register"
     * the top right pane will kept it height to 52,
     * but it the text is not equal to that, it will
     * change the height behavior to 150 when the user
     * hover over it
     */
    @FXML
    private void changeTopRightPaneBehavior(){
        topRightGridPane.setPrefHeight(52);
        signOut.setVisible(false);
        account.setVisible(false);
        topRightGridPane.setOnMouseEntered(e -> {
            if (!signIn.getText().equals("Sign In / Register")) {
                signOut.setVisible(true);
                topRightGridPane.setPrefHeight(150);
                signOut.setVisible(true);
                account.setVisible(true);
            }
        });

        topRightGridPane.setOnMouseExited(e -> {
            topRightGridPane.setPrefHeight(52);
            signOut.setVisible(false);
            account.setVisible(false);
        });

    }

    private int getIndex(Long itemID) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getItemID() == itemID)
                return i;
        }
        return -1;
    }

    @FXML
    private void displayFinance(){
        KayakUtil.shoppingCartsTableProperty(shoppingCartTable, itemNameColumn, itemQuantityColumn, itemPriceColumn, carts);
        KayakUtil.getFinanceInfo(carts, totalItems, discountPer, itemsPrice, discountAmount, itemsTotalPrice);
    }

    @FXML
    private void switchScene() {
        signIn.setOnAction(e -> {
            stageManager.closeWindow(root);
            stageManager.switchScene("Kayak User Login");
        });
    }

    /**
     * @param service
     * Spring will encounter the MainController class while
     * doing a package scan and will initialize its instance
     * by calling the @Autowired annotated constructor.
     *
     * Field dependency "KayakProductRepository" could be autowired, but
     * it could also be result in a NullPointerException, so its not
     * considered a best practice to autowired it
     */
    @Autowired
    private MainController(ProductService service, StageManager manager){
        this.stageManager = manager;
        this.productService = service;
    }

    @Autowired
    private void getProducts(){
        kayakProducts = productService.getAllProduct();
    }

}
