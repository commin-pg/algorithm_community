import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//To-Do 중복값 제거

public class jcrs0907 {
  public static void main(String[] args) {
    new jcrs0907();
  }

  jcrs0907(){
    // String[] id_list = {"muzi", "frodo", "apeach", "neo"};
    String[] id_list = {"con", "ryan"};
    // String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
    
    String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
    // int k = 2;
    int k = 3;
    solution(id_list,report,k);
  }            
  public int[] solution(String[] id_list, String[] report, int k) {

    int[] answer = new int[id_list.length];
    Map<String, Integer> reportMap = new HashMap<String, Integer>();
    List<Map<String, String>> list = new ArrayList<>();
    
    for(int i = 0; i< report.length; i++){
      Map<String,String> map = new HashMap<>();                 
      map.put(report[i].split(" ")[0],report[i].split(" ")[1]);        

      if(reportMap.containsKey(report[i].split(" ")[1])){
        int count = (int)reportMap.get(report[i].split(" ")[1]);
        reportMap.put(report[i].split(" ")[1], count + 1);
      }else{
        reportMap.put(report[i].split(" ")[1],1);
      }
      list.add(map);
    }
    
    for(Map.Entry<String, Integer> entry: reportMap.entrySet()){
      if(entry.getValue() == k || entry.getValue() > k){
        for(int i=0; i<list.size(); i++) {
          Map<String, String> id = list.get(i);
          if(id.containsValue(entry.getKey())){           
            for(Map.Entry<String, String> elem: id.entrySet()) {              
              int id_index = Arrays.asList(id_list).indexOf(elem.getKey());
              answer[id_index]++;
            }      
          }                
        }  
      }
    }
    System.out.println(Arrays.toString(answer));
    return answer;
  }
}