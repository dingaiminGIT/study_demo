package exception;

public class DealException {

    public static void main(String[] args) {
        String a = null;
        try {
            if (a.equalsIgnoreCase("2")) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("---");
            System.out.println("e.getCause():" + e.getCause());
            System.out.println("e.getMessage():" + e.getMessage());

        }
    }
}
