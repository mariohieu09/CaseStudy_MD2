package AccountBase;

import ShoppingCart.ShoppingCart;

public class UserAccount extends Account {
    public ShoppingCart cart;

    public UserAccount() {
    }

    public UserAccount(String accountName, String password) {
        super(accountName, password);
        cart = new ShoppingCart();
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
}
