import java.io.*;
import java.util.*;
class Week1 {
  
  private static final List<String> stop_words = new ArrayList<String>();
  private static final TreeMap<String, Integer> frequencies = new TreeMap<String, Integer>(Collections.reverseOrder());

  private static void loadStopWords() throws IOException{
    String str = "";
    File file = new File("stop_words.txt");
    BufferedReader br = new BufferedReader(new FileReader(file));
    while ((str = br.readLine()) != null) {
      String[] words = str.split(",");
      for(String word:words){
        String w = word.toLowerCase();
        stop_words.add(w);
      }  
    }
  }

  private static List<Map.Entry<String, Integer>> sort() {
    Set<Map.Entry<String, Integer>> set = frequencies.entrySet();
    List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(set);
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
        public int compare(Map.Entry<String, Integer> o1,
                           Map.Entry<String, Integer> o2) {
            return o2.getValue().compareTo(o1.getValue());
        }
    });
    return list;
  }
  
  public static void main(String[] args) throws IOException {
    File file = new File("pride-and-prejudice.txt");
    BufferedReader br = new BufferedReader(new FileReader(file));
    loadStopWords();
    String st;
    while ((st = br.readLine()) != null) {
      // String[] words = st.split("\\W");
      String[] words = st.split("[^a-zA-Z0-9]+");
      for(String word:words){
        String w = word.toLowerCase();
        if (!stop_words.contains(w) && w.length() > 1) {
          if (frequencies.containsKey(w))
            frequencies.put(w, frequencies.get(w) + 1);
          else
            frequencies.put(w, 1);
        }
      }
    }
    List<Map.Entry<String, Integer>> sortedMap = sort();
    int counter = 0;
    // System.out.println(frequencies);
    for (Map.Entry<String,Integer> entry : sortedMap) {
      if(counter == 25) break;
      System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
      counter++;
    }

  }
}
