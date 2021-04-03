import java.util.Scanner;

public class ShopMenu {

    public static int getInteger(Scanner sc, String message){
        System.out.print(message);
        while(!sc.hasNextInt()){
            sc.nextLine();
            System.out.println(message);
        }
        return sc.nextInt();
    }

    public static double getDouble(Scanner sc, String message){
        System.out.print(message);
        while(!sc.hasNextDouble()){
            sc.nextLine();
            System.out.println(message);
        }
        return sc.nextDouble();
    }

    public static void showRoomMenu(ArrayManager s, Player p, Scanner sc){
        System.out.println("WELCOME TO THE SHOWROOM!!!!");
        System.out.println("You have "+p.money+" coins.");
        System.out.println("1) Add items to shop");
        System.out.println("2) Delete items from shop");
        System.out.println("3) Buy items  from Shop");
        System.out.println("4) View Backpack");
        System.out.println("5) View Player");
        System.out.println("6) Exit");

        int choice=sc.nextInt();
        while (choice != 0)
        {
            switch(choice){
                case 1:  addWeapons(s, p, sc);
                    break;
                case 2:  removeWeapons(s, p, sc);
                    break;
                case 3:  showRoom(s, p, sc);
                    break;
                case 4:  viewBackpack(s, p, sc);
                    break;
                case 5:  viewPlayer(s, p, sc);
                    break;
                case 6:  exit();
            }
        }
    }

    public static void addWeapons(ArrayManager s, Player p, Scanner sc){
        System.out.println("***********WELCOME TO THE WEAPON ADDING MENU*********");
        System.out.println("Please enter the NAME of the weapon:");
        String weaponName = sc.next();
        Item test = s.get(weaponName);
        if(test != null){
            int quantity = getInteger(sc,"Item exists! Please enter the quantity to add to stock:");
            test.increaseQuantity(quantity);
            System.out.println("Weapon quantity updated!");
        }
        else{
            int weaponRange = getInteger(sc,"Please enter the RANGE of the Weapon (0-10):");
            int weaponDamage = getInteger(sc,"Please enter the DAMAGE of the Weapon:");
            double weaponWeight = getDouble(sc,"Please enter the WEIGHT of the Weapon (in pounds):");
            double weaponCost = getDouble(sc,"Please enter the COST of the Weapon:");
            Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
            int quantity = getInteger(sc,"Please enter the quantity in stock:");
            if(s.addItem(w, quantity) == true){
                System.out.println("Weapon Added!");
            }
            else{
                System.out.println("Weapon could not be added.");
            }
        }
        System.out.print("------------------\n");
        showRoomMenu(s,p,sc);
    }

    public static void removeWeapons(ArrayManager s, Player p, Scanner sc){
        System.out.println("***********WELCOME TO THE WEAPON REMOVE MENU*********");
        s.printTable();
        System.out.println("Please enter the NAME of the Weapon:");
        String weaponName = sc.next();
        int quantity = getInteger(sc,"Please enter the QUANTITY to remove:" );
        Item item = s.get(weaponName);
        if(item != null && s.removeItem(item.weapon, quantity) == true){
            s.removeItem(item.weapon, quantity);
            System.out.println("Weapon removed!");
        }
        else{
            System.out.println("Could not remove weapon.");
        }
        System.out.print("------------------\n");
        showRoomMenu(s,p,sc);
    }

    public static void showRoom(ArrayManager s, Player p, Scanner sc){
        s.printTable();
        System.out.println("Please enter the name of the weapon you would like to buy:");
        String choice = sc.next();
        int quantity = getInteger(sc,"Please enter the amount you would like to buy:");


        while(p.inventoryFull() == false){
            Item shopItem = s.get(choice);
            Item bpItem = p.backpack.inventory.get(choice);
            if(shopItem != null && shopItem.quantity != 0 && shopItem.quantity - quantity >= 0) {
                if (p.buy(shopItem.weapon) == true ) {
                    if (bpItem != null) {
                        bpItem.increaseQuantity(quantity);
                    }
                    else {
                        p.backpack.addItem(s.get(choice).weapon, quantity);
                    }
                    shopItem.decreaseQuantity(quantity);
                    p.money -= (s.get(choice).weapon.cost * quantity);
                    System.out.println("Purchase Complete!");
                }
            }
            else{
                System.out.println("Could not complete purchase.");
            }
            System.out.print("------------------\n");
            showRoomMenu(s,p,sc);
        }
        System.out.println("Inventory already full!");
        System.out.print("------------------\n");
        showRoomMenu(s,p,sc);
    }

    public static void viewBackpack(ArrayManager s, Player p, Scanner sc){
        System.out.println("Backpack:");
        System.out.println("Weight:" + p.backpack.currWeight);
        System.out.println("Items: " + p.backpack.numItems);
        p.backpack.printBackpack();
        System.out.print("------------------\n");
        showRoomMenu(s,p,sc);
    }

    public static void viewPlayer(ArrayManager s, Player p, Scanner sc){
        System.out.println("Name:" +p.name + "\nMoney:" + p.money + "\nWeapons:");
        s.printBackpack();
        System.out.print("------------------\n");
        showRoomMenu(s,p,sc);
    }

    public static void exit(){
        System.out.println("Exiting program...");
        System.exit(0);
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Player name:");
        String name=sc.next();
        Player p= new Player(name);
        ArrayManager s= new ArrayManager(101);
        showRoomMenu(s,p,sc);
    }
}
