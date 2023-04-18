package Controller;

import AccountBase.Account;
import AccountBase.AccountManage;
import AccountBase.Seller;
import AccountBase.UserAccount;
import ProductModel.Product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountManage am = new AccountManage();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter choice:  \n1.Sign up \n2.Sign in \n3.Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    am.Signup();
                    break;
                case 2:
                    Account a = am.SignIn();
                    if(am.checkingAcc(a) && am.checkRole(a) == 0){
                        while(true){
                            System.out.println("Dang nhap thanh cong!");
                            System.out.println("\n1.Check gio hang \n2.Them vao gio hang \n3.Thanh toan \n0.Exit");
                            int t = sc.nextInt();
                            sc.nextLine();
                            UserAccount user = new UserAccount(a.getAccountName(), a.getPassword());
                            switch (t){
                                case 1:
                                   break;
                                case 2:
                                    System.out.println("Enter a name: ");
                                    String name = sc.nextLine();
                                    user.addToCart(name ,user);

                                case 3:
                                    System.exit(0);
                            }
                        }
                    }else if(am.checkingAcc(a) && am.checkRole(a) == 1) {
                        while (true) {
                            System.out.println("Chao mung!");
                            System.out.println("\n1.Them san pham \n2.Tang gia san pham \n3.Giam gia san pham \n4.Exit");
                            int t = sc.nextInt();
                            sc.nextLine();
                            Seller sell = new Seller(a.getAccountName(), a.getPassword());
                            switch (t){
                                case 1:
                                    System.out.println("Enter product name: ");
                                    String name = sc.nextLine();
                                    System.out.println("Enter the price: ");
                                    int price = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Enter the description: ");
                                    String des = sc.nextLine();
                                    Product prod = new Product(price, name, des);
                                    sell.addProduct(prod);
                                    break;

                            }
                        }
                    }
                    break;
                case 3:
                    System.exit(1);
            }
        }
    }
}
