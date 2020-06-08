package ru.topper.graphs.entities;

/**An endge implementation,can use wighted and directed graphs */
public class UserEdge implements Edge{
    
    private Long start;
    private Long finish;
    private Double weight;
    private boolean directed;
    
    public UserEdge(Long start, Long finish) {
        this.start = start;
        this.finish = finish;
        this.weight = 1.0;
        this.directed = false;
    }

    public UserEdge(Long start, Long finish, Double weight, boolean directed) {
        this.start = start;
        this.finish = finish;
        this.weight = weight;
        this.directed = directed;
    }
    
    
    public UserEdge(Long start, Long finish,  boolean directed) {
        this.start = start;
        this.finish = finish;
        this.weight = 1.0;
        this.directed = directed;
    }
    

    public UserEdge(Long start, Long finish, Double weight) {
        this.start = start;
        this.finish = finish;
        this.weight = weight;
        this.directed = false;
    }
    

    @Override
    public Long getStart() {
        return start;
    }

    @Override
    public void setStart(Long start) {
        this.start = start;
    }
    
    @Override
    public Long getFinish() {
        return finish;
    }

    @Override
    public void setFinish(Long finish) {
        this.finish = finish;
    }

    @Override
    public Double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }
    
    

    @Override
   public UserEdge clone(){
       
     return  new UserEdge(getStart(), getFinish(),getWeight(),isDirected());
   }
    
}
