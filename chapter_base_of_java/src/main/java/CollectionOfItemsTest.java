import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionOfItemsTest {
    public static void main(String[] args) {

        Set<Org> orgSet = new HashSet<>();
        List<Org> orgList = new ArrayList<>();

        orgList.add(new Org("name1", "fname1", "1000", "101112"));
        orgList.add(new Org("name2", "fname2", "1001", "101113"));
        orgList.add(new Org("name3", "fname3", "1002", "101114"));
        orgList.add(new Org("name4", "fname4", "1003", "101115"));
        orgList.add(new Org("name5", "fname5", "1004", "101116"));
        orgList.add(new Org("name6", "fname6", "1005", "101117"));
        orgList.add(new Org("name7", "fname7", "1010", "101118"));
        orgList.add(new Org("name8", "fname8", "1011", "101119"));
        orgList.add(new Org("name9", "fname9", "1012", "101199"));
        orgList.add(new Org("name10", "fname10", "1013", "101888"));
        orgList.add(new Org("name11", "fname11", "1014", "101777"));

        String regNum = "101199";
        String inn = "1000";
        String fullName = "";
        String name = "name11";
        for (Org o : orgList){
            if(regNum.equalsIgnoreCase(o.getRegNum())){
                orgSet.add(o);
            }
            if(inn.equalsIgnoreCase(o.getInn())){
                orgSet.add(o);
            }
            if(name.equalsIgnoreCase(o.getName())){
                orgSet.add(o);
            }
            if(fullName.equalsIgnoreCase(o.getFullName())){
                orgSet.add(o);
            }
        }
        System.out.println(orgSet);
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Org{
    private String name;
    private String fullName;
    private String inn;
    private String regNum;
}
