public class Player {
    public String name;
    public Backpack backpack;
    public double money;

    public Player(String name){
        this.name = name;
        money = 45.00;
        backpack = new Backpack();
    }

    public boolean buy(Weapon w){
        return money >= w.cost;
    }

    public boolean inventoryFull(){
        return (backpack.numItems == backpack.maxItems);
    }
}
