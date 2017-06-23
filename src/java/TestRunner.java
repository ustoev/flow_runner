/**
 * Created by Sergei_Kovalkov on 3/21/2017.
 */
public class TestRunner {


    public static void main(String[] args) throws InterruptedException {

       for (int i=0; i < 1; i++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        new FlowRunnerInfant().test();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
       /* new FlowRunnerInfant().test(); */

    }
}
