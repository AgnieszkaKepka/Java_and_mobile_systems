import java.util.*;

public class FulFillmentCenterContainer {

    //FulfillmentCenter MAP__________

    private Map<String, FulfillmentCenter> ffcmap=new TreeMap<>();

    //GET,SET__________

    public Map<String, FulfillmentCenter> getFfcmap() {
        return ffcmap;
    }

    public void setFfcmap(Map<String, FulfillmentCenter> ffcmap) {
        this.ffcmap = ffcmap;
    }

    //Methods__________

    public void addCenter(String name, double maxCapacity){
        System.out.println("\n add new center named "+name+" and capacity"+maxCapacity);
        FulfillmentCenter m=new FulfillmentCenter(name,maxCapacity);
        ffcmap.put(name, m);
    }

    public void addCenter(FulfillmentCenter FFC){
        System.out.println("\n add center named "+FFC.getName());
        ffcmap.put(FFC.getName(),FFC);
    }

    public void removeCenter(String name){
        System.out.println("\n remove center named"+name);
        ffcmap.remove(name);
    }

    public List<FulfillmentCenter> findEmpty(){

        List<FulfillmentCenter> Ffc=new LinkedList<>(); //l.pusta
        Set<Map.Entry<String, FulfillmentCenter>> entrySet = ffcmap.entrySet();
        for(Map.Entry<String, FulfillmentCenter> entry:entrySet)
        {
            if ( entry.getValue().getCurrentCapacity() == 0 )
            {
                Ffc.add(entry.getValue());
            }
        }
        return Ffc;
    }

    public void summary(){
        System.out.println("\n All information in container");
        List<FulfillmentCenter> Ffc=new LinkedList<>();
        Set<Map.Entry<String, FulfillmentCenter>> entrySet = ffcmap.entrySet();
        for(Map.Entry<String, FulfillmentCenter> entry:entrySet)
        {
            System.out.println("Center name:"+entry.getValue().getName());
            double k=entry.getValue().getCurrentCapacity();
            double z=entry.getValue().getMaxCapacity();
            System.out.println("Center percentage filling "+(k/z)*100+"%");
        }
    }
}
