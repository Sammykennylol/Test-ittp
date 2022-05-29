import java.util.*;

import org.apache.commons.lang.RandomStringUtils;
import java.lang.instrument.Instrumentation;

/**
 * Класс, который использовался для выполнения первого задания, его можно не смотреть, все умозаключения приведены в Word файле
 */
public class MainEats {
    public static int len = 999;
    public static void main(String[] args) {
        //быстрее работать с map, потому что поиск стабильно не зависит от количества записей, а у нас их целых 18млн
        //однако он занимает больше места, чем другие коллекции.
        String number = "+79608495791";
        //двумерный массив
        String[][] array = new String[len+1][2];

        Map<String,String> map = new HashMap<>();
        Map<String,String> treeMap = new TreeMap<>();
        Map<String,String> linkedHashMap = new LinkedHashMap<>();
        Map<String,String> identityHashMap = new IdentityHashMap<>();
        arrayNumbers();

        List<numberAndName> numArray = new ArrayList<>();

        for (int i=0;i<array.length;i++)
        {
            numArray.add(new numberAndName(newRandint(),RandomStringUtils.random(20, true, false)));


        }

        for (int i=0;i<array.length;i++)
        {
            array[i][1] = RandomStringUtils.random(20, true, false);
            array[i][0] = newRandint(); //11


        }
        array[0][0] = number;



        //поиск элемента в массиве
        // System.out.println(ObjectSizeFetcher.getObjectSize(array));

        long start = System.currentTimeMillis();

        for (int i =0; i<array.length;i++) {
            String firstResult = Arrays
                    .stream(array[i])
                    .filter(x -> x.equals(number))
                    .findFirst()
                    .orElse(null);
        }

        long end = System.currentTimeMillis();

        System.out.println("Itog "+(end-start) + " ms");
        //конец 18758328

        map.put(number,newRandint());
        for (int i=0;i<len;i++)
        {
            map.put(newRandint(),RandomStringUtils.random(20, true, false));

        }

        //поиск элемента в массиве
        start = System.currentTimeMillis();

        map.get(number);

        end = System.currentTimeMillis();
        System.out.println("Itog "+(end-start) + " ms");


        treeMap.put(number,newRandint());
        for (int i=0;i<len;i++)
        {
            treeMap.put(newRandint(),RandomStringUtils.random(20, true, false));

        }

        //поиск элемента в массиве
        start = System.currentTimeMillis();

        treeMap.get(number);

        end = System.currentTimeMillis();
        System.out.println("Itog "+(end-start) + " ms");


        linkedHashMap.put(number,newRandint());
        for (int i=0;i<len;i++)
        {
            linkedHashMap.put(newRandint(),RandomStringUtils.random(20, true, false));

        }

        //поиск элемента в массиве
        start = System.currentTimeMillis();

        linkedHashMap.get(number);

        end = System.currentTimeMillis();
        System.out.println("Itog "+(end-start) + " ms");

        identityHashMap.put(number,RandomStringUtils.random(20, true, false));

        for (int i=0;i<len;i++)
        {
            identityHashMap.put(newRandint(),RandomStringUtils.random(20, true, false));

        }

        //поиск элемента в массиве
        start = System.currentTimeMillis();
        String a = new String("Привет");
        String b = new String("Привет");
        System.out.println(number ==  "+79608495791");

        System.out.println( identityHashMap.get(number));


        end = System.currentTimeMillis();
        System.out.println("Itog "+(end-start) + " ms");


        System.out.println("THE END");
    }

    public static String newRandint()
    {
        Random random = new Random();
        String s = "+7";
        for (int i = 0; i<10;i++)
            s += String.valueOf(random.ints(0,10).findAny().getAsInt());
        return s;

    }


    public static  void  arrayNumbers(){
        ArrayList<ArrayList<String>> bigArray = new ArrayList<ArrayList<String>>();
        for (int i=0; i<len+1;i++) {
            ArrayList<String> small = new ArrayList<String>();

            small.add(newRandint());
            small.add(RandomStringUtils.random(20, true, false));

            bigArray.add(small);
        }

        System.out.println(bigArray);

        System.out.println(bigArray.get(0).get(0));

    }

}

class numberAndName{


    private String number;
    private String name;
    numberAndName(String number,String name)
    {
        this.number = number;
        this.name = name;
    }

}

