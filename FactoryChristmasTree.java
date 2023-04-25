
public class FactoryChristmasTree {

    public static void main(String[] args) {
        ChristmasTree defaultTree = new DefaultChristmasTree();
        ChristmasTree addStar = DecorationFactory.getMyTree(DecoratedItem.STAR, defaultTree);

        System.out.println(DecorationFactory.getMyTree(DecoratedItem.LIGHT, addStar).getDescription());

    }

}

interface ChristmasTree {
    String getDescription();
}

class DefaultChristmasTree implements ChristmasTree {

    @Override
    public String getDescription() {
        return "Default tree";
    }

}

class Decorator implements ChristmasTree {
    ChristmasTree currentTree;

    public Decorator(ChristmasTree tree) {
        currentTree = tree;
    }

    @Override
    public String getDescription() {

        return currentTree.getDescription();
    }

}

class Star extends Decorator {

    public Star(ChristmasTree tree) {
        super(tree);
    }

    @Override
    public String getDescription() {

        return currentTree.getDescription() + " + Star";
    }

}

class Garland extends Decorator {

    public Garland(ChristmasTree tree) {
        super(tree);
    }

    @Override
    public String getDescription() {

        return currentTree.getDescription() + " + Garland";
    }

}

class Light extends Decorator {

    public Light(ChristmasTree tree) {
        super(tree);
    }

    @Override
    public String getDescription() {

        return currentTree.getDescription() + " + Light";
    }

}

class DecorationFactory {

    public static ChristmasTree getMyTree(DecoratedItem item, ChristmasTree currentTree) {

        if (item == DecoratedItem.STAR) {
            return new Star(currentTree);
        } else if (item == DecoratedItem.GARLAND) {
            return new Garland(currentTree);
        } else if (item == DecoratedItem.LIGHT) {
            return new Light(currentTree);
        } else {
            return currentTree;
        }

    }

}

enum DecoratedItem {
    STAR, GARLAND, LIGHT
}
