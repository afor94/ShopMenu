public class Backpack {
    public ArrayManager inventory;
    public int numItems;
    public int maxItems;
    public double currWeight;
    public double maxWeight;

    public Backpack(){
        numItems = 0;
        maxItems = 30;
        currWeight = 0;
        maxWeight = 90;
        inventory = new ArrayManager(maxItems);
    }

    public boolean addItem(Weapon w, int q){
        if(inventory.addItem(w,q)==true){
            currWeight+=w.weight;
            numItems++;
            return true;
        }
        return false;
    }

    public Item get(String key){
        return inventory.get(key);
    }

    public void printBackpack(){
        inventory.printBackpack();
    }
}

