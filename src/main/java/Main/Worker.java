package Main;

public class Worker implements Runnable {

    private int id;

    public Worker(int id) {
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println("Starting: " + id);

    }
}
