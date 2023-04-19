package AccountBase;

public class GuestAccount extends Account{
    static final long serialVersionUID = -7034897190745766939L;
    public GuestAccount() {
        this.Role = "Guest";
        this.Password = "";
        this.AccountName = "";
    }

}
