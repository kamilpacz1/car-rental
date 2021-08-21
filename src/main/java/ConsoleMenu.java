public class ConsoleMenu {
    private String header;
    private String[] options;
    private int exitOptionId;

    public ConsoleMenu(String header, String[] options) {
        this.header = header;
        this.options = options;
        this.exitOptionId = findExitOption();
    }
    public void display() {
        System.out.println(header);
        for (int i = 0; i < options.length; i++) {
            System.out.format(" %d. %s%n", (i + 1), options[i]);
        }
        System.out.print("Choose your option: ");
    }
    private int findExitOption() {
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            if ("Exit".equals(option)) {
                return i + 1;
            }
        }
        System.out.println("No exit option!");
        return -1;
    }
}
