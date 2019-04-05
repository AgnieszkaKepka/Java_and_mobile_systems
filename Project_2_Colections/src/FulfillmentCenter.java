import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FulfillmentCenter {
    public List<Item> items = new LinkedList<>();
    private String name;
    private double maxCapacity;
    private double currentCapacity;
    private double maxMassOfAllItems;


    public FulfillmentCenter() {
        this.name = "Center";
        this.maxCapacity = 100;
        this.currentCapacity = 0;
    }

    public FulfillmentCenter(String name, double maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
    }

    public FulfillmentCenter(List<Item> items, String name, double maxCapacity, double maxMassOfAllItems) {
        this.items = items;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.maxMassOfAllItems = maxMassOfAllItems;
        this.currentCapacity = 0;
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public double getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(double currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public double getMaxMassOfAllItems() {
        return maxMassOfAllItems;
    }

    public void setMaxMassOfAllItems(double maxMassOfAllItems) {
        this.maxMassOfAllItems = maxMassOfAllItems;
    }

    //Methods___________

    public void addProduct(Item item) {
        System.out.println("\n Add item named: " + item.getName());
        if ((item.getAmount() + getCurrentCapacity()) > getMaxCapacity()) {
            System.err.println("Failed to add item, the max capacity of the magazine is exceeded");
        } else {
            int k = searchIfExists(item.getName());
            if (k >= 0) {
                items.get(k).setAmount(items.get(k).getAmount() + item.getAmount());
            } else {
                items.add(item);
                maxMassOfAllItems = maxMassOfAllItems + item.getMass();
                currentCapacity = currentCapacity + item.getAmount();
            }
        }
    }

    public Item search(String name) {
        System.out.println("search item named " + name);
        int placeOfItem = -1;
        Item item = new Item();
        item.setName(name);

        for (int i = 0; i < items.size(); i++) {
            if (compare(items.get(i), item) == 0) {
                placeOfItem = i;
            }
        }
        try {
            if (placeOfItem == -1) {
                throw new NumberFormatException("there is no item with this name");
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return items.get(placeOfItem);
    }



    public int compare(Item a, Item b) {

        if (a == null || b == null) {
            return -1;   //0
        } else {
            return a.compareTo(b);
        }
    }

    public int searchIfExists(String name) {
        System.out.println("\n search item named " + name);
        int placeOfItem = -1;
        Item item = new Item();
        item.setName(name);

        for (int i = 0; i < items.size(); i++) {
            if (compare(items.get(i), item) == 0) {
                placeOfItem = i;
            }
        }
        return placeOfItem;
    }

    public void getProduct(Item item) {
        System.out.println("\n get item named " + item.getName() + " and amount " + item.getAmount());
        int k = searchIfExists(item.getName());
        try {
            if (k == -1) {
                throw new NumberFormatException("there is no item with this name");
            } else {
                if ((items.get(k).getAmount() - item.getAmount()) < 0) {
                    throw new NumberFormatException("insufficient amount");
                } else if ((items.get(k).getAmount() - item.getAmount()) == 0) {
                    items.remove(k);
                    System.out.println("you get all the items, empty center");
                } else {
                    items.get(k).setAmount(items.get(k).getAmount() - item.getAmount());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            //System.err.println(e);
        }
    }

    public void removeProduct(Item item) {
        System.out.println("\nremove item named " + item.getName());
        int k = searchIfExists(item.getName());
        if (k == -1) {
            System.out.println("item does not exist!");
        } else {
            items.remove(k);
            System.out.println("item removed");
        }
    }

    public void searchPartial(String name) {
        System.out.println("\n search partial (string)" + name);
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().contains(name)) {
                items.get(i).print();
            }
        }
    }

    public int countByCondition(ItemCondition itemCondition) {
        System.out.println("\nprint amount of item which condition=" + itemCondition);
        int licznik = 0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getCondition() == itemCondition) {
                licznik++;
            }
        }
        return licznik;
    }

    public void summary() {
        System.out.println("\nAll the items");
        if (items.size() == 0) {
            System.out.println("item does not exist");
        } else {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).print();
            }
        }
    }

    public void sortByName() {

        Collections.sort(items);

        for (int i = 0; i < items.size(); i++) {
            items.get(i).print();
        }
        //System.out.println(items);
    }

    public void sortByAmount() {

        Collections.sort(items, new MyComparator());
        //System.out.println(items);
        for (int i = 0; i < items.size(); i++) {
            items.get(i).print();
        }
    }

    public Item max() {
        System.out.println("\n max of all the product");
        return Collections.max(items, new MyComparator());
    }
}