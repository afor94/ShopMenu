public class Item {
    public Weapon weapon;
    int quantity;

    public Item(Weapon w, int quantity){
        this.weapon = w;
        this.quantity = quantity;
    }

    public void increaseQuantity(int num){
        quantity+=num;
    }

    public void decreaseQuantity(int num){
        quantity-=num;
    }
}
