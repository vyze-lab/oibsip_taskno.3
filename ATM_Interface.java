//Designed by Jayanta Mohapatra
//Oasis Infobyte internship
//Task 3
//ATM interface
import java.io.IOException;
import java.util.*;
class ATM_Interface{
    public static void main(String[] args)throws IOException {
        double total=0.0;
        Scanner in=new Scanner(System.in);
        String history="";
        Account ob=new Account();
        Deposit db=new Deposit();
        Withdraw w=new Withdraw();
        Transfer t=new Transfer();

        System.out.println("\n*****************************Welcome to RMB Bank****************************************\n");
        System.out.println("Create your account------------------------------------------------------------\n");
        System.out.println("Create your user id");
        String id=in.next();
        System.out.println("Create your pin");
        int pass=in.nextInt();
        ob.create(id,pass);
        System.out.println("Congratulations! Acoount created succesfully-------------------------------------");

        System.out.println("\nLogin into your account----------------------------------------------------\n");
        System.out.println("Enter your user id");
        id=in.next();
        System.out.println("Enter your pin");
        pass= in.nextInt();
        int f=ob.login(id,pass);
        if(f==-1)
            System.out.println("Credentail mismatch error--------------------------------------------------");

        else{
            System.out.println("Login succesful------------------------------------------------------------");
            for(;;) {
                System.out.println("Please, deposit a certain amount into your new acoount");
                double dep = in.nextDouble();
                int k = db.deposit(dep);
                if (k == 1) {
                    total += dep;
                    System.out.println("Total amount in your account is Rs." + total);
                    history = history.concat("Deposit of Rs."+dep);
                    break;
                }
            }

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
            int ch=0;

            while(ch!=6){
                System.out.println("Press 1 to deposit      Press 2 to withdraw      Press 3 for transfer       Press 4 to view transaction history     Press 5 to view balance     Press 6 to exit");
                System.out.println("Enter your choice");
                ch = in.nextInt();
                switch (ch) {

                    case 1:
                        System.out.println("Enter the amount to be deposited--------------------------------");
                        double dep_amt = in.nextDouble();
                        int flag = db.deposit(dep_amt);
                        if (flag == 1) {
                            total += dep_amt;
                            System.out.println("Total amount in your account is Rs." + total);
                            history = history.concat("\tDeposit of Rs" + dep_amt);
                        }
                        System.out.println("----------------------------------------------------------------");
                        break;

                    case 2:
                        System.out.println("Enter the amount to be withdrawn--------------------------------");
                        double with_amt = in.nextDouble();
                        flag = w.withdraw(with_amt, total);
                        if (flag == 1) {
                            total -= with_amt;
                            System.out.println("Total amount in you account is Rs." + total);
                            history = history.concat("    Withdrawal of Rs" + with_amt);
                        }
                        System.out.println("----------------------------------------------------------------");
                        break;

                    case 3:
                        System.out.println("Enter the recipient's user Id-----------------------------------");
                        String recipient_id = in.next();
                        System.out.println("Enter the amount to be transferred");
                        double transfer_amt = in.nextDouble();
                        flag = t.transfer(recipient_id, transfer_amt, total);
                        if (flag == 1) {
                            total -= transfer_amt;
                            System.out.println("Total amount in you account is Rs." + total);
                            history = history.concat(" \tTransferred Rs" + transfer_amt + " to " + recipient_id);
                        }
                        System.out.println("-----------------------------------------------------------------");
                        break;

                    case 4:
                        System.out.println("Transaction history of user Id " + ob.user_id+"------------------");
                        System.out.println(history);
                        System.out.println("-----------------------------------------------------------------");
                        break;

                    case 5:
                        System.out.println("Total balance in user Id "+ob.user_id+"---------------------------");
                        System.out.println("Rs. "+total);
                        System.out.println("-------------------------------------------------------------------");
                        break;

                    case 6:
                        break;

                    default:
                        System.out.println("Error! Enter a valid choice................");

                }
            }

            System.out.println("Thank you!\nHave a good day----------------------------------------------------------------------------------------------------------");
        }
    }
}

class Account {
    String user_id="";
    int pin=0;

    public void create(String id,int pass){
        user_id=id;
        pin=pass;
    }
    public int login(String id,int pass){
        if((id.equals(user_id)==true)&&pin==pass)
            return 1;
        else
            return -1;
    }
}
class Deposit{
    public int deposit(double amt){
        int f=0;
        if(amt>5000.00){
            System.out.println("Limit exceded.\nLimit is Rs.5000.00");
            f=-1;
        }
        else if(amt<=0.00) {
            f = 0;
            System.out.println("Please enter a correct amount");
        }
        else {
            f = 1;
            System.out.println("Deposit succesful");
        }
        return f;
    }
}
class Withdraw{
    public int withdraw(double amt,double total){
        int f=0;
        if(amt>total){
            System.out.println("Error!\tAmount to be withdrawn is greater that the total amount in your account");
            f=-1;
        }
        else if(amt>5000) {
            f = 0;
            System.out.println("Limit exceded.\nLimit is Rs.5000.00");
        }
        else {
            f = 1;
            System.out.println("Withdrawal succesful");
        }
        return f;
    }
}
class Transfer{
    public int transfer(String id,double amt,double total){
        int f=0;
        if(amt>total){
            System.out.println("Error!\tAmount to be transferred is greater than the total amount in your account");
            f=-1;
        }
        else if(amt<0){
            System.out.println("Please, enter a correct amount to transfer");
            f=0;
        }
        else{
            f=1;
            System.out.println("Succesfully transferred Rs. "+amt+" to User Id "+id);
        }
        return f;
    }
}