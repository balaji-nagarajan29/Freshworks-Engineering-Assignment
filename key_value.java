import org.json.simple.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;


public class Main {

    public static void create(String key, Object value, LocalTime time){
        KeyValuePair.put(key, value);                                           // Create the key and value and store in hashtable
        timing.put(key,time);
    }

    public static void read(String keyname){
        LocalTime keytime =timing.get(keyname);                 //Read the Key if system time is less than Time-To-Live property
        int t=LocalTime.now().compareTo(keytime);

        if(t<0){
            System.out.println(KeyValuePair.get(keyname));
        }
        else{
            System.out.println("The time limit has exceed");
        }

    }


    public static void delete(String keyn){
        LocalTime keyt =timing.get(keyn);
        int tim=LocalTime.now().compareTo(keyt);            //Delete the Key if system time is less than Time-To-Live property

        if(tim<0){
            KeyValuePair.remove(keyn);
            timing.remove(keyn);
        }
        else{
            System.out.println("The time limit has exceed");
        }
    }

    static Hashtable<String, Object> KeyValuePair = new Hashtable<>();      //Using Hashtable because it is thread safe
    static HashMap<String, LocalTime> timing = new HashMap<>();

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);

        final int filesize = 1024*1024*1024;    //File size must be less than 1GB

        JSONObject json = new JSONObject();     //JSON object that store key and value

        while(true){

            System.out.println("Enter the Option that you want to perform");
            System.out.println("1. Create");
            System.out.println("2. Read");
            System.out.println("3. Delete");
            System.out.println("4. Exit");

            System.out.println("Enter your option");
            int n=scan.nextInt();

            switch(n){

                case 1:
                    long bytes = Files.size(Paths.get("D:/json.txt"));
                    if(bytes>filesize) {
                        System.out.println("File size limt exceed");        // check the file size is less than the limit
                        break;
                    }
                    System.out.println("Enter the key and value");
                    String key=scan.next();
                    if(KeyValuePair.containsKey(key)){                      //Check the key is already present or not
                        System.out.println("Key is already present");
                        break;
                    }
                    else {
                        key = key.substring(0, Math.min(key.length(), 32));               //Key is capped at 32chars
                        int value = scan.nextInt();                                      //Getting Input from the user
                        Object obj = value;
                        System.out.println("Enter the timeLimit in second to expires");
                        int sec= scan.nextInt();
                        LocalTime now = LocalTime.now();
                        now=now.plusSeconds(sec);
                        create(key,value,now);
                        json.put(key, value);
                    }
                    break;
                case 2:
                    System.out.println("Enter the Key that should read");
                    String keyname=scan.next();                                 //Case to read the key and value
                    read(keyname);
                    break;

                case 3:
                    System.out.println("Enter the key that to delete");
                    String keyn=scan.next();
                    delete(keyn);                                               //Case to delete the key and value
                    break;
                case 4:
                    System.exit(1);
            }

            Files.write(Paths.get("D:/json.txt"), json.toJSONString().getBytes());      //write the JSON object to the file
        }
  }
}
