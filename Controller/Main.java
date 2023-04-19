package Controller;

import AccountBase.Account;
import AccountBase.AccountManage;
//import AccountBase.Seller;
import AccountBase.Seller;
import AccountBase.UserAccount;
import ProductModel.Product;
import ProductModel.ProductDisplay;

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
                        System.out.println("Dang nhap thanh cong!");
                        int check = 0;
                        UserAccount user = new UserAccount(a.getAccountName(), a.getPassword());
                        while(check != 1){
                            ProductDisplay.display();
                            System.out.println("\n1.Check gio hang \n2.Them vao gio hang \n3.Thanh toan \n0.Exit");
                            int t = sc.nextInt();
                            sc.nextLine();
                            switch (t){
                                case 1:
                                    System.out.println("Enter a name: ");
                                    String Prodname = sc.nextLine();
                                    System.out.println("Your Shopping Cart:");
                                    user.display(user, Prodname);
                                    System.out.println("List product: ");
                                   break;
                                case 2:
                                    System.out.println("Enter a name: ");
                                    String name = sc.nextLine();
                                    user.addToCart(name ,user);
                                    break;
                                case 0:
                                    check = 1;
                                    break;
                            }

                        }
                    }else if(am.checkingAcc(a) && am.checkRole(a) == 1) {
                        int check = 0;
                        Seller sell = new Seller(a.getAccountName(), a.getPassword());
                        while (check != 1) {
                            ProductDisplay.display();
                            System.out.println("Chao mung!");
                            System.out.println("\n1.Them san pham \n2.Tang gia san pham \n3.Giam gia san pham \n4.Exit");
                            int t = sc.nextInt();
                            sc.nextLine();
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
                                case 3:

                                case 4:
                                    check = 1;
                                    break;

                            }
                        }
                    }else if(am.checkRole(a) == 2){
                        int check = 0;
                        ProductDisplay.display();
                        while (check != 1){
                            System.out.println("1.Sign up for now?   2.Exit");
                            int i = sc.nextInt();
                            sc.nextLine();
                            if(i == 1){
                                check = 1;
                                am.Signup();
                            }else{
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
