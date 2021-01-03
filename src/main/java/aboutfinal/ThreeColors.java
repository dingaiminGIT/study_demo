package aboutfinal;

import java.util.HashSet;
import java.util.Set;

public class ThreeColors {
    private final Set<String> colors = new HashSet<>();
    private final int  num = 1;

    public ThreeColors() {
        colors.add("red");
        colors.add("blue");
        colors.add("yellow");
    }

    public boolean isColor(String name) {
        return colors.contains(name);
    }

    public static void main(String[] args) {
        ThreeColors colors = new ThreeColors();
        boolean idColor = colors.isColor("dog");
        System.out.println(idColor);

        colors.colors.add("black");
        boolean black = colors.isColor("black");
        System.out.println(black);

    }
}
