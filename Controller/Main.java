package Controller;

import AccountBase.Account;
import AccountBase.AccountManage;

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
//                    String t = am.checkRole(a);
//                    System.out.println(t);
//                    break;
                    if(am.checkingAcc(a) && am.checkRole(a) == 0){
                        while(true){
                            System.out.println("Dang nhap thanh cong!");
                            System.out.println("\n1. Check gio hang \n2.Thanh toan \n3.Exit");
                            int t = sc.nextInt();
                            sc.nextLine();
                            switch (t){

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
                            switch (t){
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
