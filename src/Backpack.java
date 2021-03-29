public class Backpack {
    public int numItems;
    public int maxItems = 30;
    public double currWeight;
    public double maxWeight=90;
    public Weapon obj;


    public Backpack(int num,int max,double curr,double weight){
        this.numItems = num;
        this.maxItems = max;
        this.currWeight = curr;
        this.maxWeight = weight;
    }
}