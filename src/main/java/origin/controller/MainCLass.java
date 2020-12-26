package origin.controller;

public class MainCLass {
    public static void main(String[] args) {
        MainController controller = new MainController();
        ThreadGame threadGame = new ThreadGame(controller);
        threadGame.run();
    }
}
