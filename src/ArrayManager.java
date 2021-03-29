public class ArrayManager{
    private ShopItem[] table;
    private int maxItems;
    private int numItems;
    private double loadFactor;

    public ArrayManager(int size){
        maxItems = size;
        numItems = 0;
        table = new ShopItem[maxItems];
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

    public void put(Weapon item, int quantity){

        if(numItems/maxItems < loadFactor){
            int count = 1;
            int startLoc = hash(item.weaponName);
            int loc = startLoc;
            while(table[loc] != null && table[loc].item.weaponName.compareTo("DELETED")!=0){
                loc = (startLoc + count*count)%maxItems;
                count++;
            }
            if(table[loc] != null && table[loc].item.weaponName == item.weaponName){
                table[loc].numOfStock++;
            }
            else{
                table[loc]= new ShopItem(item, quantity);
            }
            numItems++;
        }
    }

    public ShopItem get(String key){
        int count = 1;
        int startLoc = hash(key);
        int loc = startLoc;

        while(table[loc] != null && key.compareTo(table[loc].item.weaponName) !=0)
        {
            loc = (startLoc + count*count)%maxItems;
            count++;
        }
        if(loc<numItems){
            return table[loc];
        }
        return null;
    }

    public void delete(String key){
        int count = 1;
        int startLoc = hash(key);
        int loc = startLoc;

        while(table[loc] != null && key.compareTo(table[loc].item.weaponName) !=0)
        {
            loc = (startLoc + count*count)%maxItems;
            count++;
        }
        if(table[loc]!=null){
            table[loc].item.weaponName = "DELETED";
            numItems--;
        }

    }

    public void printTable(){
        for (int x = 0; x < numItems; x++)
        {
            System.out.println("Name: " +table[x].item.weaponName+"   Damage:"+table[x].item.damage+"    Cost:"+table[x].item.cost+"     Quantity in stock:"+table[x].numOfStock);
        }
    }

}
