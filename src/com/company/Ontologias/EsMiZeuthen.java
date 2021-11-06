import jade.content.Predicate;

public class EsMiZeuthen implements Predicate{

//se debe llamar de la misma manera que en la ontologia para que funcione
    private float zeuthen;
    
    public EsMiZeuthen() {
        super();
    }
    
    public EsMiZeuthen(float zeuthen) {
        super();
        this.zeuthen = zeuthen;
    }
    
    public String getZeuthen() {
        return zeuthen;
    }
    
    public void setZeuthen(float zeuthen) {
        this.zeuthen = zeuthen;
    }
    
    @Override
    public String toString() {
        return "EsMiZeuthen [zeuthen=" + zeuthen + "]";
    }
    
}
