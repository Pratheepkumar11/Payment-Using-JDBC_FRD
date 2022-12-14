package controller;



import dto.Wallet;
import exception.AlreadyExistingAccountNumberException;
import exception.InsufficeintAmountException;
import exception.WalletException;
import exception.passwordexp;
import service.WalletService;
import service.WalletServiceImpl;


import java.util.InputMismatchException;
import java.util.Scanner;


public class Controller {

	public static void main(String[] args) {
		boolean ans;
		Scanner sc = new Scanner(System.in);

		WalletService ws = new WalletServiceImpl();
		do {
			try {
				System.out.println(" 1. Login , 2. Register");
				int choice = sc.nextInt();
				if (choice == 1) {
					Integer accountNumber;
					String password;
					Double transferAmount;
					Integer toAccountNumber;
					boolean userLoggedIn;

					System.out.println("Enter Account number : ");
					accountNumber = (sc.nextInt());
					sc.nextLine();
					System.out.println("Enter User Password : ");
					password = sc.nextLine();
					userLoggedIn = ws.login(accountNumber, password);
					System.out.println(userLoggedIn);

					System.out.println("Login Successfully ");

					if (userLoggedIn) {
						System.out.println("1. Add Fund , 2. ShowBalance , 3. FundTransfer ,  4. Withdraw , 5. UnRegister ");
						int userChoice = sc.nextInt();
						if (userChoice == 1) {

							Double addedFund;
							System.out.println("Enter Account number : ");
							accountNumber = (sc.nextInt());
							System.out.println("Enter initial amount : ");
							addedFund = sc.nextDouble();
							System.out.println("current Balance after Add Fund:" + ws.addFundsToWallet(accountNumber, addedFund));


						} else if (userChoice == 2) {


							System.out.println("Enter Account number : ");
							accountNumber = (sc.nextInt());


							System.out.println(ws.showWalletBalance(accountNumber));


						} else if (userChoice == 3) {


							System.out.println("Enter From Account number : ");
							accountNumber = (sc.nextInt());
							System.out.println("Enter To Account number : ");
							toAccountNumber = (sc.nextInt());
							System.out.println("Enter Transfer amount : ");
							transferAmount = sc.nextDouble();
							System.out.println("current Balance after Transfer: " + ws.fundTransfer(accountNumber, toAccountNumber, transferAmount));


						} else if (userChoice == 4) {


							System.out.println("Enter Account number : ");
							accountNumber = (sc.nextInt());
							System.out.println("Enter Amount : ");
							transferAmount = sc.nextDouble();

							System.out.println("current Balance after Withdraw: " + ws.withdraw(accountNumber, transferAmount));

						} else if (userChoice == 5) {


							System.out.println("Enter Account number : ");
							accountNumber = (sc.nextInt());
							sc.nextLine();
							System.out.println("Enter User Password : ");
							password = sc.nextLine();

							System.out.println(ws.unRegisterWallet(accountNumber, password));

						}


					} else {
						System.out.println("do Registration");
					}
				} else if (choice == 2) {

                            Double amount;
							Wallet account = new Wallet();
							System.out.println("Enter Account number : ");
							account.setId(sc.nextInt());
							sc.nextLine();
							System.out.println("Enter Account owner's name : ");
							account.setName(sc.nextLine());

								System.out.println("Enter initial amount : ");
								amount = sc.nextDouble();
								account.setBalance(amount);
								sc.nextLine();

							System.out.println("Enter Account Password : ");
							account.setPassword(sc.nextLine());

							ws.registerWallet(account);





				}


			} catch (WalletException e) {
				System.out.println("Invalid account Number");
			} catch (InsufficeintAmountException e) {
				System.out.println("Insufficient");
			} catch (passwordexp f) {
				System.out.println("Password Mis-match");
			}
			catch (InputMismatchException a){
				sc.nextLine();
				System.out.println("Give correct input");
			} catch (AlreadyExistingAccountNumberException e) {
				System.out.println("Already exists");
			}
			System.out.println("choose 1 for continue or choose 2 for exit");
			int choose=sc.nextInt();

			if(choose==1){
				ans=true;
			}else{
				ans=false;
			}
		}while (ans);

	}
	}


