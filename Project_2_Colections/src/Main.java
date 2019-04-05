import java.util.List;

public class Main {
    public static void main(String[] args){

        System.out.println("Add item:____________________________________________________");

        Item item=new Item("Headphones",ItemCondition.NEW, 20, 15);
        Item item2=new Item("Telephone",ItemCondition.REFURBISHED, 10, 10);
        Item item4=new Item("Laptop",ItemCondition.NEW,5,30);
        item.print();

        /*_______________________________________________________________________________*/

        System.out.println("add to Centrum1 and Centrum2,search__________________________");

        FulfillmentCenter Centrum1=new FulfillmentCenter();
        FulfillmentCenter Centrum2=new FulfillmentCenter("Magazyn", 200);

        Centrum1.addProduct(item);
        Centrum2.addProduct(item2);
        Centrum2.addProduct(item4);
        Item z =Centrum2.search("Telephone");
        z.print();

        System.out.println("\n result compareTo: "+item.compareTo(item2));

        /*_______________________________________________________________________________*/

        System.out.println("show summary,get item,summary________________________________");
        Centrum2.summary();
        Item item3=new Item("Telephone",ItemCondition.REFURBISHED, 5, 5);
        Centrum2.getProduct(item3);
        Centrum2.summary();

        /*_______________________________________________________________________________*/

        System.out.println("remove product,summary,search partial (string)________________");
        Centrum2.removeProduct(item3);
        Centrum2.summary();
        Centrum1.searchPartial("Pre");

        /*_______________________________________________________________________________*/

        System.out.println("amount of products NEW:____________________________________" );
        System.out.println (+Centrum2.countByCondition(ItemCondition.NEW));
        Centrum2.addProduct(item); //add two products
        Centrum2.addProduct(item2);

        /*_______________________________________________________________________________*/

        System.out.println("Sort by name, by amount______________________________________");
        Centrum2.sortByName();
        Centrum2.sortByAmount();

        /*_______________________________________________________________________________*/

        System.out.println("Condition New - how much in Centrum 2_________________________");
        System.out.println(Centrum2.countByCondition(ItemCondition.NEW));

        /*_______________________________________________________________________________*/

        System.out.println("Max of Centrum 2_____________________________________________");
        Item k=Centrum2.max();
        k.print();

        /*_______________________________________________________________________________*/
        System.out.println("___________________________________________________________");
        System.out.println("Create new centrum, add it to container , add two previous");

        FulFillmentCenterContainer Container=new FulFillmentCenterContainer();
        FulfillmentCenter CentrumPuste=new FulfillmentCenter("Puste",500);
        Container.addCenter("phone",300);
        Container.addCenter(Centrum1);
        Container.addCenter(Centrum2);

        /*_______________________________________________________________________________*/

        System.out.println("Empty Containers from FulfillmentCenter______________________");
        List<FulfillmentCenter> emptyList=Container.findEmpty();
        for(int i=0; i<emptyList.size();i++){
            System.out.println(emptyList.get(i).getName());
        }

        /*___________________________________________________________________________________*/

        System.out.println("Container summary, percentage fill_______________________________");
        Container.summary();
    }
}