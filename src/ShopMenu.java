import java.util.Scanner;

public class ShopMenu {

    public static int getInteger(Scanner sc,String message){
        System.out.print(message);
        while (!sc.hasNextInt())
        {
            sc.nextLine(); //clear the invalid input ...
            System.out.print(message);
        }
        return sc.nextInt();
    }

    public static double getDouble(Scanner sc,String message){
        System.out.print(message);
        while (!sc.hasNextDouble())
        {
            sc.nextLine(); //clear the invalid input ...
            System.out.print(message);
        }
        return sc.nextDouble();
    }


    public static void addWeapons(ArrayManager h,Scanner sc,Player pl)
    {
        System.out.println("***********WELCOME TO THE WEAPON ADDING MENU*********");
        String weaponName, session; int weaponRange; int weaponDamage; double weaponWeight; double weaponCost;
        int quantity;
        System.out.print("Please enter the NAME of the Weapon):");
        weaponName=sc.next();
        while (weaponName.compareTo("end") != 0)
        {
            weaponRange= getInteger(sc,"Please enter the Range of the Weapon (0-10):");
            weaponDamage=getInteger(sc,"Please enter the Damage of the Weapon:");
            weaponWeight= getDouble(sc,"Please enter the Weight of the Weapon (in pounds):");
            weaponCost=getDouble(sc,"Please enter the Cost of the Weapon:");
            Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
            quantity=getInteger(sc,"Please enter the quantity in stock:");
            h.put(w,quantity);
            System.out.println("Weapon Added!");
            System.out.print("Please enter a character to return to the shop menu\n");
            session=sc.nextLine();
            showRoomMenu(h,pl,sc);


        }
    }

    public static void viewBackpack(Player p,ArrayManager h,Scanner sc){
        String session;
        System.out.println("Backpack:");
        h.printTable();
        System.out.print("Please enter a character to return to the shop menu");
        session=sc.next();
        showRoom(h, p,sc);

    }

    public static void viewPlayer(Player p,ArrayManager h,Scanner sc){
        String session;
        System.out.println("Name:" +p.name + "\nMoney:" + p.money + "\nWeapons:");
        h.printTable();
        System.out.print("Please enter a character to return to the shop menu");
        session=sc.next();
        showRoom(h, p,sc);


    }
    public static void exit(){
        System.out.println("Exiting program...");
        System.exit(0);
    }

    public static void showRoomMenu(ArrayManager ht,Player p,Scanner sc){
        System.out.println("WELCOME TO THE SHOWROOM!!!!");
        System.out.println("You have "+p.money+" coins.");
        System.out.println("1) Add items to shop");
        System.out.println("2) Delete items from shop");
        System.out.println("3) Buy items  from Shop");
        System.out.println("4) View Backpack");
        System.out.println("5) View Player");
        System.out.println("6) Exit");

        int choice=sc.nextInt();
        while (choice != 0 && !p.inventoryFull())
        {
            switch(choice){
                case 1:  addWeapons(ht,sc,p);
                    break;
                case 2:  exit();
                    break;
                case 3:  showRoom(ht,p,sc);
                    break;
                case 4:  viewBackpack(p,ht,sc);
                    break;
                case 5:  viewPlayer(p,ht,sc);
                    break;
                case 6:  exit();

            }

        }

    }

    public static void showRoom(ArrayManager ht, Player p,Scanner sc)
    {
        String choice,session;
        ht.printTable();
        choice=sc.next();
        while ( !p.inventoryFull())
        {
            ShopItem si = ht.get(choice);
            if (si != null)
            {

                p.buy(si.item);
                p.withdraw(si.item.cost);
                si.numOfStock--;

            }

            showRoomMenu(ht,p,sc);
            choice = sc.next();
        }
        System.out.println("");
        showRoomMenu(ht,p,sc);
    }


    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String pname;
        System.out.println("Please enter Player name:");
        pname=sc.next();
        Player pl= new Player(pname,45);
        ArrayManager ht= new ArrayManager(101);
        showRoomMenu(ht, pl,sc);
        int option = sc.nextInt();


        pl.printCharacter();


    }


}