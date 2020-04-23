package Socket;

public class player {

    public String name;
    public int score;
    public int time;

    public player(String n, String a) {
        name = n;
        score = Integer.parseInt(a);
        time = 0;
    }
}
