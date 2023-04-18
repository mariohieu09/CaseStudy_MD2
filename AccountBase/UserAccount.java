package AccountBase;

import ShoppingCart.ShoppingCart;

public class UserAccount extends Account {
    public ShoppingCart cart;

    public UserAccount() {
    }

    public UserAccount(String accountName, String password) {
        super(accountName, password);
        this.cart = new ShoppingCart(super.getId());
        this.Role = "User";
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Override
    public void setId(int id) {
        super.setId(id);
        this.cart.setId(id);
    }
}
