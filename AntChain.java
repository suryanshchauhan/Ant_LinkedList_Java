public class AntChain {
    private Ant start;
    private Ant end;
    private int numAnts;
    
    public void rollCallFromStart(){
        Ant helper = this.start;
        for(int i = 0; i<this.numAnts; i++){
            System.out.println(helper.getName());
            helper = helper.behind;
        }

    }
    public void rollCallFromEnd(){
        Ant helper = this.end;
        for(int i = 0; i<this.numAnts; i++){
            System.out.println(helper.getName());
            helper = helper.front;
        }
    }

    public int getNumberOfAnts(){
        return this.numAnts;
    }

    public void addToEnd(String newAntName){
        Ant antAtEnd = new Ant(newAntName);
        antAtEnd.front = this.end;
        antAtEnd.front.behind = antAtEnd;
        antAtEnd.behind = null;
        numAnts+=1;
        this.end = antAtEnd;
    }

    public void addToStart(String newAntName){
        Ant antAtStart = new Ant(newAntName);
        antAtStart.front = null;
        antAtStart.behind = this.start;
        antAtStart.behind.front = antAtStart;
        numAnts+=1;
        this.start = antAtStart;
    }

    public void addAt(String newAntName, int index){
        Ant newAntAdded = new Ant(newAntName);
        Ant helper = this.start;
        if(index == 0){
            this.addToStart(newAntName);
        }
        else if(index == numAnts){
            this.addToEnd(newAntName);
        }
        else if(index > 0 && index < numAnts){
            for(int i = 0; i<index; i++){
                helper = helper.behind;
            }
            newAntAdded.front = helper.front;
            newAntAdded.behind = helper;
            newAntAdded.front.behind = newAntAdded;
            newAntAdded.behind.front = newAntAdded;
            this.numAnts+=1;
        }
        else
        {
            System.out.println("Please enter a valid index.");
        }
    }

    public int positionOf(String antName){
        int currIndex = -1;
        Ant helper = this.start;
        for (int i = 0; i<this.numAnts; i++){
            if(helper.getName().equals(antName)){
                currIndex = i;
                break;
            }
                helper = helper.behind;
            }
        return currIndex;
    }

    public void kickout(String removeAntName){
        Ant helper = this.start;
        int currIndex = -1;
        for(int i = 0; i<this.numAnts; i++){
            if(helper.getName().equals(removeAntName)){ 
                currIndex = i;
                if(currIndex == 0){
                this.start = helper.behind;
                start.front = null;
                } 
                else if(currIndex == numAnts-1){
                this.end = this.end.front; 
                this.end.behind = null;                   
                }
                else{
                helper.front.behind = helper.behind;
                helper.behind.front = helper.front;
               }
               this.numAnts-=1;
            }
            helper = helper.behind;
        }
    }

    public void connectToChain(AntChain other){
        if (other.start == null) {
            other.start = this.start;
            other.end = this.end;
            other.numAnts = this.numAnts;
            
        }
        else{
            int swapNumAnts = this.numAnts;
            other.start.front = this.end;
            this.end.behind = other.start;
            this.end = other.end;
            other.start = this.start;
            this.numAnts+=other.numAnts;
            other.numAnts+=swapNumAnts;
        }
    }

    public boolean isSame(AntChain other){
        Ant thisHelper = this.start;
        Ant otherHelper = other.start;
        int count = 0;
        if(this.numAnts == 0 && this.numAnts == other.numAnts)
        {
            return true;
        }
        else if (this.numAnts == other.numAnts)
        {   for(int i = 0; i<this.numAnts; i++){
                if (thisHelper.getName().equals(otherHelper.getName()))
                {
                    count++;
                }
                thisHelper = thisHelper.behind;
                otherHelper = otherHelper.behind;
            }
            if (count == this.numAnts)
            {
                return true;
            }
        }
        return false;
    }

}
