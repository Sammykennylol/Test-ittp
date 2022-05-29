import org.apache.commons.lang.math.RandomUtils;

import java.util.*;
/**
 * Main - класс, который
 * выполняет основную задачу - выводить слова песни двумя потоками в правильном порядке.
 *
 * @author Semyon
 */
public class Main {
    /**
     * Всевозможные варианты вывода песни дуэтом для сравнения (песню дуэтом потоки могут выполнять начиная с Чер или начиная с Сонни, то есть в состоянии гонки)
     */
    public static int[][] indexesOfDuet = {
            {1,1,1,1},
            {1,1,1,2},
            {1,1,2,1},
            {1,1,2,2},
            {1,2,1,1},
            {1,2,1,2},
            {1,2,2,1},
            {1,2,2,2},

            {2,1,1,1},
            {2,1,1,2},
            {2,1,2,1},
            {2,1,2,2},
            {2,2,1,1},
            {2,2,1,2},
            {2,2,2,1},
            {2,2,2,2},


    };
    public static ArrayList<LineOfSong> songArray = new ArrayList<>();
    public static ArrayList<ArrayList<LineOfSong>> finalSongArray = new ArrayList<>();


    public static void main(String[] args) throws InterruptedException {
        doMain();
    }


    /**
     * Основной метод, который выводит на экран песню в нужной последовательности с помощью двух потоков и добавляет выведенные строки в массив finalSongArray
     * В массиве finalSongArray хранится созданный массив в результате работы потоков Cher и Sonny
     * Также в этом массиве хранятся всевозможные варианты исполениня этой песни (строки, которые исполняются дуэтом, могут исполняться как в последовательности "Cher","Sonny", так и наоборот)
     * return true, если совпадение с массивом верных вариантов песни найден
     */
    public static boolean doMain() throws InterruptedException
    {

        Worker worker = new Worker();
        worker.singASong();


        sing_for_test(worker);
        for (int i =1;i< finalSongArray.size();i++){
            if( finalSongArray.get(0).equals( finalSongArray.get(i))){

                return finalSongArray.get(0).equals( finalSongArray.get(i));
            }
        }

        return false;



    }

    /**
     * Создаются всевозможные варианты (16 вариантов) исполнения этой песни (строки, которые исполняются дуэтом, могут исполняться как в последовательности "Cher","Sonny", так и наоборот)
     * @param worker
     */
    public static void sing_for_test(Worker worker) {

        int countInd = 0;
        for (int k = 0; k < indexesOfDuet.length; k++) {
            songArray = new ArrayList<>();
            for (int i = 0; i < worker.lyrics.length; i++) {

                if ((worker.lyrics[i][0] == "Sonny")) {
                    songArray.add(new LineOfSong(worker.lyrics[i][0],worker.lyrics[i][1]));

                } else if ((worker.lyrics[i][0] == "Cher")) {
                    songArray.add(new LineOfSong(worker.lyrics[i][0],worker.lyrics[i][1]));

                }


                if ((worker.lyrics[i][0] == "Sonny, Cher") && indexesOfDuet[k][countInd] == 1) {
                    songArray.add(new LineOfSong("Cher",worker.lyrics[i][1]));
                    songArray.add(new LineOfSong("Sonny",worker.lyrics[i][1]));
                    countInd++;
                    if (countInd==4) countInd = 0;

                }
                else if ((worker.lyrics[i][0] == "Sonny, Cher") && indexesOfDuet[k][countInd] == 2) {
                    songArray.add(new LineOfSong("Sonny",worker.lyrics[i][1]));
                    songArray.add(new LineOfSong("Cher",worker.lyrics[i][1]));
                    countInd++;
                    if (countInd==4) countInd = 0;
                }
            } finalSongArray.add(songArray);
        }
    }

}



/**
 * Класс, в котором создаются и запускаются потоки Cher и Sonny
 */
class Worker{

    public static volatile int i = 0;
    public  String[][] lyrics = ArrayOfSong.getLyrics();
    public static volatile boolean isDuetCher = false;
    public static volatile boolean isDuetSonny = false;


    public Worker()
    {

    }

    public void singASong( ) throws InterruptedException {
        i=0;
        lyrics = ArrayOfSong.getLyrics();

        Main. finalSongArray.clear();
        Main.songArray.clear();
        Thread Cher = new Thread(new SingRunnableCher());
        Thread Sonny = new Thread(new SingRunnableSonny());
        Cher.start();
        Sonny.start();

        Cher.join();
        Sonny.join();

        Main. finalSongArray.add(Main.songArray);


    }
    public synchronized void ChersSong() throws InterruptedException {



        while(i<lyrics.length)
        {

            if ((lyrics[i][0] == "Cher") )
            {
                System.out.println( lyrics[i][0] + ": " + lyrics[i][1]);
                isDuetCher = false;
                Main.songArray.add(new LineOfSong(lyrics[i][0],lyrics[i][1]));
                i++;



            }
            else if ( (lyrics[i][0] == "Sonny, Cher"))
            {


                if (!isDuetCher)
                {
                    System.out.println( "Cher" + ": " + lyrics[i][1]);
                    Main.songArray.add(new LineOfSong("Cher",lyrics[i][1]));
                }

                isDuetCher = true;

                Thread.yield();

            }
            if (isDuetCher && isDuetSonny && i== lyrics.length-1) break;
        }

    }
    public synchronized void SonnysSong() throws InterruptedException {


        while(i<lyrics.length)
        {


            if ((lyrics[i][0] == "Sonny") )
            {
                System.out.println( lyrics[i][0] + ": " + lyrics[i][1]);
                Main.songArray.add(new LineOfSong(lyrics[i][0],lyrics[i][1]));
                isDuetSonny = false;


                i++;


            }
            else if ((lyrics[i][0] == "Sonny, Cher"))
            {



                if (!isDuetSonny)
                {
                    System.out.println( "Sonny" + ": " + lyrics[i][1]);
                    Main.songArray.add(new LineOfSong("Sonny",lyrics[i][1]));
                }

                isDuetSonny = true;
                Thread.yield();

                if (isDuetSonny && isDuetCher) {

                    i++;


                }

            }
            if (isDuetCher && isDuetSonny && i== lyrics.length-1) break;

        }
    }
}
class SingRunnableCher implements Runnable
{
    Worker worker = new Worker();


    @Override
    public void run()
    {
        try {

            worker.ChersSong();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SingRunnableSonny implements Runnable
{
    Worker worker = new Worker();

    @Override
    public void run()
    {
        try {
            worker.SonnysSong();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}



