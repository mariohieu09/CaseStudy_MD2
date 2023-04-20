package Controller;

import AccountBase.Account;
import AccountBase.AccountManage;
//import AccountBase.Seller;
import AccountBase.Seller;
import AccountBase.UserAccount;
import ProductModel.Product;
import ProductModel.ProductDisplay;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountManage am = new AccountManage();
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Enter choice:  \n1.Sign up \n2.Sign in \n3.Exit");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        am.Signup();
                        break;
                    case 2:
                        Account a = am.SignIn();
                        if (am.checkingAcc(a) && am.checkRole(a) == 0) {
                            System.out.println("Sign in success!");
                            int check = 0;
                            UserAccount user = new UserAccount(a.getAccountName(), a.getPassword());
                            ProductDisplay.display();
                            while (check != 1) {
                                System.out.println("\n1.Check the cart \n2.Adding new product  \n3.Search \n4.Sort the List and display the list \n5.Display the invoice history \n0.Exit");
                                int t = sc.nextInt();
                                sc.nextLine();
                                switch (t) {
                                    case 1:
                                        user.eWalletDisplay(user);
                                        System.out.print("1.Payment                     2.Deposit the amount ");
                                        System.out.println("Your Shopping Cart:");
                                        user.display(user, "");
                                        System.out.println("------------------------------------");
                                        int z = sc.nextInt();
                                        sc.nextLine();
                                        if (z == 1) {
                                            System.out.println("Enter the product name: ");
                                            String name = sc.nextLine();
                                            if (user.checkTheQuantity(name)) {
                                                user.reduceQuantity(name);
                                                boolean beenPaid = user.Payment(user, name);
                                                if (beenPaid) {
                                                    user.exchange(user, name);
                                                    user.addPaidcheck(user, name);
                                                    user.getPaidmentcheck(user, name);
                                                }
                                            } else {
                                                System.out.println("The quantity is not enough!");
                                            }
                                        } else if (z == 2) {
                                            user.deposit(user);
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Enter a name: ");
                                        String name = sc.nextLine();
                                        user.addToCart(name, user);
                                        break;
                                    case 3:
                                        System.out.println("Enter the product name: ");
                                        name = sc.nextLine();
                                        ProductDisplay.searchProduct(name);
                                        break;
                                    case 4:
                                        System.out.println("1.Sort the list by name     2.Sort the list by price");
                                        int n = sc.nextInt();
                                        sc.nextLine();
                                        if (n == 1) {
                                            System.out.println("Product List: ");
                                            ProductDisplay.SortByName();
                                        } else {
                                            System.out.println("Product List: ");
                                            ProductDisplay.SortByPrice();
                                        }
                                        break;
                                    case 5:
                                        System.out.println("Here is your transaction history: ");
                                        user.eInvoiceDisplay(user);
                                        break;
                                    case 0:
                                        check = 1;
                                        break;
                                }
                            }
                        } else if (am.checkingAcc(a) && am.checkRole(a) == 1) {
                            int check = 0;
                            System.out.println("Welcome!");
                            Seller sell = new Seller(a.getAccountName(), a.getPassword());
                            System.out.println("Product List: ");
                            ProductDisplay.display();
                            while (check != 1) {
                                System.out.println("\n1.Adding new product \n2.Increase the price \n3.Coupon \n4.Display the Sell list \n5.Sort the list \n6.Display the amount \n7.Reduce Quantity \n0.Exit");
                                int t = sc.nextInt();
                                sc.nextLine();
                                switch (t) {
                                    case 1:
                                        System.out.println("Enter product name: ");
                                        String Newname = sc.nextLine();
                                        System.out.println("Enter the price: ");
                                        int Newprice = sc.nextInt();
                                        sc.nextLine();
                                        System.out.println("Enter the description: ");
                                        String description = sc.nextLine();
                                        System.out.println("Enter the quantity you want to sell: ");
                                        int quantity = sc.nextInt();
                                        sc.nextLine();
                                        Product prod = new Product(Newprice, Newname, description, quantity);
                                        sell.addProduct(sell.getAccountName(), prod);
                                        break;
                                    case 2:
                                        System.out.println("Enter product name: ");
                                        String name = sc.nextLine();
                                        boolean isTrue = sell.increasePrice(sell.getAccountName(), name);
                                        if (isTrue) {
                                            System.out.println("Success!");
                                        } else {
                                            System.out.println("Can't not find the product in your sellist!");
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Enter product name: ");
                                        name = sc.nextLine();
                                        boolean decreaseAble = sell.coupon(sell.getAccountName(), name);
                                        if (decreaseAble) {
                                            System.out.println("Success!");
                                        } else {
                                            System.out.println("Can't not find the product in your sellist!");
                                        }
                                        break;
                                    case 4:
                                        sell.display(sell.getAccountName());
                                        break;
                                    case 5:
                                        System.out.println("1.Sort the list by name     2.Sort the list by price");
                                        int n = sc.nextInt();
                                        sc.nextLine();
                                        if (n == 1) {
                                            ProductDisplay.SortByName();
                                        } else {
                                            ProductDisplay.SortByPrice();
                                        }
                                        break;
                                    case 6:
                                        sell.eWalletDisplay(sell);
                                        break;
                                    case 7:
                                        System.out.println("Enter the name of the product: ");
                                        name = sc.nextLine();
                                        System.out.println("Enter the quantity you want to reduce: ");
                                        quantity = sc.nextInt();
                                        sc.nextLine();
                                        sell.setQuantity(sell, name, quantity);
                                        break;
                                    case 0:
                                        check = 1;
                                }
                            }
                        } else if (am.checkRole(a) == 2) {
                            int check = 0;
                            ProductDisplay.display();
                            while (check != 1) {
                                System.out.println("1.Sign up for now? 2.Sort the List  0.Exit");
                                int i = sc.nextInt();
                                sc.nextLine();
                                if (i == 1) {
                                    check = 1;
                                    am.Signup();
                                } else if (i == 2) {
                                    System.out.println("1.Sort the list by name     2.Sort the list by price");
                                    int n = sc.nextInt();
                                    sc.nextLine();
                                    if (n == 1) {
                                        ProductDisplay.SortByName();
                                    } else {
                                        ProductDisplay.SortByPrice();
                                    }
                                } else {
                                    check = 1;
                                }
                            }
                        } else {
                            System.out.println("The Account is not exist!");
                        }
                        break;
                    case 3:
                        System.exit(1);
                }
            }

        }catch (InputMismatchException e){
            System.out.println("Number please");
        }
    }
}