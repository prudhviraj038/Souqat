package in.yellowsoft.souqat;

/**
 * Created by yellowsoft on 27/4/17.
 */
public class RootObject {
    Group group=null;
    Options options=null;
    public  RootObject(Group group){
        this.group=group;
    }
    public  RootObject(Options options){
        this.options=options;
    }
}
