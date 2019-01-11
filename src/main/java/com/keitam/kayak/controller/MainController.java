package com.keitam.kayak.controller;

import com.keitam.kayak.model.CustomerCart;
import com.keitam.kayak.model.KayakProduct;
import com.keitam.kayak.repository.KayakProductRepository;
import com.keitam.kayak.util.KayakUtil;
import com.keitam.kayak.util.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.NumberFormat;
import java.util.List;

@Controller
public class MainController {
    @FXML private AnchorPane root;
    @FXML private GridPane topRightGridPane;
    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;
    @FXML private Button signIn, signOut, account;
    @FXML private Label copyRight, discountPer, discountAmount, itemsTotalPrice;
    @FXML private Label totalItems;

    @FXML private TableView<CustomerCart> shoppingCartTable;
    @FXML private TableColumn<CustomerCart, String> itemNameColumn;
    @FXML private TableColumn<CustomerCart, Integer> itemQuantityColumn;
    @FXML private TableColumn<CustomerCart, Double> itemPriceColumn;

    private ObservableList<KayakProduct> kayakProducts = FXCollections.observableArrayList();
    private ObservableList<Button> buttonsList = FXCollections.observableArrayList();
    private ObservableList<CustomerCart> carts = FXCollections.observableArrayList();

    private final KayakProductRepository productRepository;


    @FXML
    public void initialize(){
        copyRight.setText(KayakUtil.getFooterText());
        changeTopRightPaneBehavior();
        root.setOnMouseEntered(e -> {

        });
        loadProductsInFlowPane(kayakProducts);
        addSelectedProductInCart();
    }

    private void addSelectedProductInCart(){
        for (int i = 0; i < buttonsList.size(); i++) {
            final int location = i;
            buttonsList.get(i).setOnAction(b -> kayakProducts.forEach(p -> {
                if (Integer.parseInt(buttonsList.get(location).getText()) == p.getProductID()) {
                    getSelectedItem(p.getProductID(), p.getpName(), p.getPrice(), KayakUtil.getProductImage(p.getImageName(), 100, 75));
                }
            }));
        }
    }

    private void getSelectedItem(Long productID, String productName, double productPrice,
                                 ImageView view) {
        if (carts.size() != 0) {
            for (CustomerCart item : carts) {
                if (item.getItemName().equals(productName)) {
                    Notification.operationNotPerform("Operation",
                            (productName + " is already in your cart. " +
                                    "Select\nit in your car and update it quantity."));
                    return;
                }
            }
        }

        carts.add(new CustomerCart(productID, productName, 1, productPrice, view));
        KayakUtil.shoppingCartsTableProperty(shoppingCartTable, itemNameColumn, itemQuantityColumn, itemPriceColumn, carts);
        discountPer.setText("" + KayakUtil.getDiscountSaving(carts) + "%");
        itemsTotalPrice.setText("Total Price: " + KayakUtil.NUMBER_FORMAT.format(KayakUtil.getTotalPrice(carts, discountAmount)));
        totalItems.setText("" + (Integer.parseInt(totalItems.getText()) + 1));

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
                    "\tQuantity: " + product.getQuantity()));
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

    /**
     * @param repository
     * Spring will encounter the MainController class while
     * doing a package scan and will initialize its instance
     * by calling the @Autowired annotated constructor.
     *
     * Field dependency "KayakProductRepository" could be autowired, but
     * it could also be result in a NullPointerException, so its not
     * considered a best practice to autowired it
     */
    @Autowired
    private MainController(KayakProductRepository repository){
        this.productRepository = repository;
    }

    @Autowired
    private void getProducts(){
        productRepository.findAll().forEach(e -> kayakProducts.addAll(e));
    }
}
