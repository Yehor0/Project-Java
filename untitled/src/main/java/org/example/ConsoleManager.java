package org.example;

public class ConsoleManager {

   public void showMenuSecond() {
        System.out.println("Press : ");
        System.out.println("1 see shares, what you bought");
        System.out.println("2 see shares, what you can bought");
        System.out.println("3 exit");
        System.out.println("4 see your profile");
        System.out.println("5 sell your shares");
        System.out.println("6 buy shares");
    }
    public void showMenuFirst() {
        System.out.println("Press : ");
        System.out.println("1 login");
        System.out.println("2 registration");
        System.out.println("3 exit");
    }
    public void wrongNum() {
        System.out.println("Wrong num");
    }
    public void successful() {
        System.out.println("Successful \n");
    }
    public void addAdditionalInfo() {
        System.out.println("Do you want add an additional information ");
        System.out.println("If yes press 1 , else press 2");
    }
    public void writeNum() {
        System.out.println("Please write a number");
    }
    public void whatSell() {
        System.out.println("Write a name of share : " );
    }
    public void howMany() {
        System.out.println("How many : ");
    }
    public void incorrectLogIn() {
        System.out.println("Password or nickname incorrect");
        System.out.println("If want registration write 1 or if you want go back write 2");
    }
    public void showShareInfo(String name ,int quantity , int price ) {
        System.out.println("\nName : " + name);
        System.out.println("Price of one : " + price);
        System.out.println("Quantity : " + quantity + "\n");
    }
    public void showProfileInfo(String name , String country ,  String city , int account) {
        System.out.println("Name : " + name);
        System.out.println("City : " + city);
        System.out.println("Country : " + country);
        System.out.println("Account: " + account + "\n");
    }
    public void lessShares() {
        System.out.println("ERROR \n You don't have so many shares");
    }
    public void wrongNameOfShare() {
        System.out.println("ERROR! \nWrong name\n");
    }
    public void error() {
        System.out.println("\nERROR \n");
    }
    public void yourNick() {
        System.out.println("Nickname : ");
    }
    public void yourPassword(){
        System.out.println("Password : ");
    }
    public void yourCity() {
        System.out.println("City :");
    }
    public void yourCountry() {
        System.out.println("Country");
    }
}
