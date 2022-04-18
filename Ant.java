public class Ant{
    private String name;
    public Ant front;
    public Ant behind;

    public Ant(String name){
        this.name = name;
        this.front = null;
        this.behind = null;
    }

    public String getName(){
        return this.name;
    }
    public Ant getFront(){
        return front;
    }
    
    public Ant getBehind(){
        return behind;
    }
    
    public void setName(String newName)
    {
        newName = this.name;
    }

    public void setFront(Ant newFront)
    {
        newFront = this.front;
    }
    
    public void setBehind(Ant newBehind){
        newBehind = this.behind;
    }
}

