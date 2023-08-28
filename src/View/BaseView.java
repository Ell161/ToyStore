package View;

import java.util.Scanner;

public class BaseView {

    public String getData(String message) {
        System.out.print(message);
        Scanner input = new Scanner(System.in);
        String data = input.nextLine();
        return data;
    }
}
