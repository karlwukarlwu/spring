package proxy;

/**
 * Karl Rules!
 * 2023/11/3
 * now File Encoding is UTF-8
 */
public class Bus implements Vehicle{
    @Override
    public void run() {
        System.out.println("bus running");
    }
    public void stop(){
        System.out.println("bus stop");
    }
}
