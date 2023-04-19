package AccountBase;

public class GuestAccount extends Account{
    public GuestAccount() {
        this.Role = "Guest";
        this.Password = "";
        this.AccountName = "";
    }

}
