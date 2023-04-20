package AccountBase;

import AccountBase.Account;
import Controller.Validate;
import FileIO.ReadFile;
import FileIO.WriteFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountManage {
    List<Account> list = new ArrayList<>();
    File AccountStorage = new File("DataBase.txt");
    public boolean checkingAcc(Account acc){
        ReadFile rf = new ReadFile();
        list = rf.readFile(AccountStorage);
        for(Account a : list){
            if(a.getAccountName().equals(acc.getAccountName()) && a.getPassword().equals(acc.getPassword())){
                return true;
            }
        }
        return false;
    }
    private void addAcc(Account ss){
        ReadFile rf = new ReadFile();
        list = rf.readFile(AccountStorage);
        list.add(ss);
        WriteFile wf = new WriteFile();
        wf.writeFile(AccountStorage, list);

    }
    public void Signup(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Who do you want to sign up?   \n1.Sign up as User  \n2.Sign up as Seller");
        int choice = sc.nextInt();
        sc.nextLine();
        String name;
        do{
            System.out.println("Enter new acc name (6 character long): ");
            name = sc.nextLine();
        }while (!Validate.SignUpValidate(name));
        System.out.println("Enter new password: ");
        String pass = sc.nextLine();
        if(choice == 1) {
            Account n = new UserAccount(name, pass);
            if (!checkingAcc(n)) {
                addAcc(n);
            } else {
                System.out.println("The Account name is already existed!");
            }
        }else if(choice == 2){
            Account n = new Seller(name, pass);
            if(!checkingAcc(n)){
                addAcc(n);
            }else{
                System.out.println("The Account name is already existed!");
            }
        }

    }
    public Account SignIn(){
        Scanner sc = new Scanner(System.in);
        Account n;
        System.out.println("Who do you want to sign in?    \n1.Sign in as guest  \n2.Sign in as User ");
        int choice = sc.nextInt();
        sc.nextLine();
        if(choice == 2) {
            System.out.println("Enter account name: ");
            String name = sc.nextLine();
            System.out.println("Enter your password: ");
            String pass = sc.nextLine();
            n = new UserAccount(name, pass);
            return n;
        }else{
            n = new GuestAccount();
            return n;
        }
    }
    public int checkRole(Account a){
        ReadFile rf = new ReadFile();
        list = rf.readFile(AccountStorage);
        String Rolie = "Guest";
        if(a.getPassword().equals("") && a.getAccountName().equals("")){
            return 2;
        }
        for(Account p : list){
            if(a.getAccountName().equals(p.getAccountName()) && a.getPassword().equals(p.getPassword())){
                Rolie = p.getRole();
                break;
            }
        }
        if(Rolie.equals("User")){
            return 0;
        } else if (Rolie.equals("Seller")) {
            return 1;
        }else{
            return -1;
        }
    }
    public void passWordForget(){
        Scanner sc = new Scanner(System.in);
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        list = rf.readFile(AccountStorage);
        System.out.println("Enter your account name: ");
        String name = sc.nextLine();
        Account p = list.stream().filter(x -> x.getAccountName().equals(name)).findAny().orElse(new Account() {
        });
        if(p.getRole().equals("default")){
            System.out.println("Can't not find the account.");
        }else{
            for(Account account : list){
                if(account.getAccountName().equals(name)){
                    System.out.println("Enter new password: ");
                    String newPassWord = sc.nextLine();
                    account.setPassword(newPassWord);
                    break;
                }
            }
        }
        wf.writeFile(AccountStorage, list);
    }
}
