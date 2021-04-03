public class ArrayManager {
    private Item[] table;
    private int maxItems;
    private int numItems;
    private double loadFactor;

    public ArrayManager(int size){
        maxItems = size;
        numItems = 0;
        table = new Item[maxItems];
        loadFactor = 0.75;
    }

    private int hash(String name){
        int value=0;
        int weight=1;

        for(int x = 0; x<name.length();x++){
            value += (name.charAt(x)-'a'+1)*weight;
            weight++;
        }
        return value%maxItems;
    }

    public boolean addItem(Weapon w, int q){
        if(numItems/maxItems < loadFactor){
            int count = 1;
            int startLoc = hash(w.weaponName);
            int loc = startLoc;
            while(table[loc] != null && table[loc].weapon.weaponName.compareTo("DELETED")!=0){
                loc = (startLoc + count*count)%maxItems;
                count++;
            }
            table[loc] = new Item(w, q);
            numItems += q;
            return true;
        }
        return false;
    }

    public Item get(String key){
        int count = 1;
        int startLoc = hash(key);
        int loc = startLoc;

        while(table[loc] != null && key.compareTo(table[loc].weapon.weaponName) !=0)
        {
            loc = (startLoc + count*count)%maxItems;
            count++;
        }
        if(loc<numItems){
            return table[loc];
        }
        return null;
    }

    public boolean removeItem(Weapon w, int quantity){
        int count = 1;
        int startLoc = hash(w.weaponName);
        int loc = startLoc;

        while(table[loc] != null && w.weaponName.compareTo(table[loc].weapon.weaponName) != 0){
            loc = (startLoc + count * count)%maxItems;
            count++;
        }
        if(table[loc]!=null){
            if(table[loc].quantity > quantity){
                table[loc].decreaseQuantity(quantity);
            }
            table[loc].weapon.weaponName = "DELETED";
            numItems-=quantity;
            return true;
        }
        return false;
    }


    public void printTable(){
        System.out.println("Items in stock:");
        for (int x = 0; x < maxItems; x++)
        {
            if(table[x]!=null && table[x].quantity != 0){
                System.out.println("Name: " +table[x].weapon.weaponName+"   Damage:"+table[x].weapon.damage+"    Cost:"+table[x].weapon.cost+"     Quantity in stock:"+table[x].quantity);
            }
        }
    }
    public void printBackpack(){
        for (int x = 0; x < maxItems; x++)
        {
            if(table[x]!=null && table[x].quantity != 0){
                System.out.println("Name: " +table[x].weapon.weaponName+"   Damage:"+table[x].weapon.damage+"    Quantity in backpack:"+table[x].quantity);
            }
        }
    }

}
