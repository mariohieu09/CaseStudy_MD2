import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountManage {
    Scanner sc = new Scanner(System.in);
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
        System.out.println("Enter new acc name: ");
        String name = sc.nextLine();
        System.out.println("Enter new password: ");
        String pass = sc.nextLine();
        Account n = new Account(name, pass);
        if(!checkingAcc(n)){
            addAcc(n);
        }else{
            System.out.println("Da ton tai");
        }

    }
    public Account SignIn(){
        System.out.println("Enter acc name: ");
        String name = sc.nextLine();
        System.out.println("Enter your password: ");
        String pass = sc.nextLine();
        Account n = new Account(name,pass);
        return n;
    }
}
