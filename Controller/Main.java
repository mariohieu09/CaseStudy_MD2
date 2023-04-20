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

        while (true) {
                int choice;
                String choi;
                do {
                    System.out.println("Enter choice:  \n1.Sign up \n2.Sign in \n3.Forget Password? \n0.Exit");
                    choi =  sc.nextLine();
                }while (!Validate.CilentValidate(choi));
                choice = (int)choi.charAt(0) - 48;
            switch (choice) {
                case 1 -> am.Signup();
                case 2 -> {
                    Account a = am.SignIn();
                    if (am.checkingAcc(a) && am.checkRole(a) == 0) {
                        System.out.println("Sign in success!");
                        int check = 0;
                        UserAccount user = new UserAccount(a.getAccountName(), a.getPassword());
                        ProductDisplay.display();
                        while (check != 1) {
                            do {
                                System.out.println("\n1.Check the cart \n2.Adding new product  \n3.Search \n4.Sort the List and display the list \n5.Display the invoice history \n0.Exit");
                                choi = sc.nextLine();
                            } while (!Validate.UserValidate(choi));
                            int t = (int) choi.charAt(0) - 48;
                            switch (t) {
                                case 1:
                                    user.eWalletDisplay(user);
                                    System.out.println("Your Shopping Cart:");
                                    user.display(user, "");
                                    System.out.println("------------------------------------");
                                    do {
                                        System.out.println("1.Payment                2.Deposit the amount          3.Remove product      0.Back to menu");
                                        choi = sc.nextLine();
                                    }while (!Validate.CartValidate(choi));
                                    int z = (int)choi.charAt(0) - 48;
                                    if (z == 1) {
                                        System.out.println("Enter the product name: ");
                                        String name = sc.nextLine();
                                        if (user.checkTheQuantity(name)) {
                                            user.reduceQuantity(name);
                                            boolean beenPaid = user.exchange(user, name);
                                            if (beenPaid) {
                                                user.addPaidcheck(user, name);
                                                user.getPaidmentcheck(user, name);
                                            }
                                        } else {
                                            System.out.println("The quantity is not enough!");
                                        }
                                    } else if (z == 2) {
                                        user.deposit(user);
                                    } else if(z == 3){
                                        System.out.println("Enter the product name: ");
                                        String name = sc.nextLine();
                                        user.removeProd(user,name);
                                    }else{
                                        break;
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
                                    do {
                                        System.out.println("1.Sort the list by name     2.Sort the list by price");
                                        choi = sc.nextLine();
                                    }while (!Validate.ListSortingValidate(choi));
                                    int n = (int)choi.charAt(0) - 48;
                                    System.out.println("Product List: ");
                                    if (n == 1) {
                                        ProductDisplay.SortByName();
                                    } else {
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
                            System.out.println("\n1.Display the Sell list \n2.Sort the list and display the list. \n3.Exit");
                            int t = sc.nextInt();
                            sc.nextLine();
                            switch (t) {
                                case 1:
                                    sell.eWalletDisplay(sell);
                                    sell.display(sell.getAccountName());
                                    do {
                                        System.out.println("1.Adding new product            2.Increase the price             3.Coupon        4.Reduce the quantity       0.Back to menu");
                                        choi = sc.nextLine();
                                    }while (!Validate.ListSellerValidate(choi));
                                    int d = (int)choi.charAt(0) - 48;
                                    if(d == 1){
                                        System.out.println("Enter new product name: ");
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
                                    }else if(d == 2){
                                        System.out.println("Enter product name: ");
                                        String name = sc.nextLine();
                                        boolean isTrue = sell.increasePrice(sell.getAccountName(), name);
                                        if (isTrue) {
                                            System.out.println("Success!");
                                        } else {
                                            System.out.println("Can't not find the product in your sellist!");
                                        }
                                    }else if(d == 3){
                                        System.out.println("Enter product name: ");
                                        String name = sc.nextLine();
                                        boolean decreaseAble = sell.coupon(sell.getAccountName(), name);
                                        if (decreaseAble) {
                                            System.out.println("Success!");
                                        } else {
                                            System.out.println("Can't not find the product in your sellist!");
                                        }
                                    }else if(d == 4){
                                        System.out.println("Enter the name of the product: ");
                                        String name = sc.nextLine();
                                        System.out.println("Enter the quantity you want to reduce: ");
                                        int quantity = sc.nextInt();
                                        sc.nextLine();
                                        sell.setQuantity(sell, name, quantity);
                                    }else{
                                        break;
                                    }
                                    break;
                                case 2:
                                        do {
                                            System.out.println("1.Sort the list by name     2.Sort the list by price");
                                            choi = sc.nextLine();
                                        }while (!Validate.ListSortingValidate(choi));
                                    int n = (int)choi.charAt(0) - 48;
                                    if (n == 1) {
                                        ProductDisplay.SortByName();
                                    } else {
                                        ProductDisplay.SortByPrice();
                                    }
                                    break;
                                case 3:
                                    check = 1;
                            }
                        }
                    } else if (am.checkRole(a) == 2) {
                        int check = 0;
                        ProductDisplay.display();
                        while (check != 1) {
                            do {
                                System.out.println("1.Sign up for now? 2.Sort the List  3.Exit");
                                choi = sc.nextLine();
                            } while (!Validate.choiceValidate(choi));
                            int i = (int) choi.charAt(0) - 48;
                            if (i == 1) {
                                check = 1;
                                am.Signup();
                            } else if (i == 2) {
                                do {
                                    System.out.println("1.Sort the list by name     2.Sort the list by price");
                                    choi = sc.nextLine();
                                }while (!Validate.ListSortingValidate(choi));
                                int n = (int)choi.charAt(0) - 48;
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
                }
                case 3 -> am.passWordForget();
                case 0 -> System.exit(0);
            }
            }

        }
    }
