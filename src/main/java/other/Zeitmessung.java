package other;

public class Zeitmessung {

    private long time;

    // Konstruktor
    public Zeitmessung() {
       this.time = 0;
    }

    // Beginne Zeitmessung
    public void start() {
        this.time = System.nanoTime();
    }


    // Beende Zeitmessung und gebe die Zeit aus
    public String stop() {
        long nanoseconds = System.nanoTime() - time;

        return "Zeit: " + (nanoseconds/Math.pow(10, 9)) + " Sekunden";
    }
}
