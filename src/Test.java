import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Allen on 2015/2/16.
 */
public class Test {
    public static void main(String[] args) {

                final Random random = new Random();
                Timer executeSchedule = new Timer();
                executeSchedule.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println(random.nextInt());
                    }
                }, 0, 500);



    }
}
