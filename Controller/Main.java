package Controller;

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
                    if(am.checkingAcc(am.SignIn())){
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
                    }else{
                        System.out.println("Khong ton tai tai khoan nay");
                        break;
                    }
                case 3:
                    System.exit(1);
            }
        }
    }
}
