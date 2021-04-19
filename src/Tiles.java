public class Tiles {
    public Type tiletype;
    public int shipid;

    public static Tiles makeTile(Type tiletype) {
        Tiles tiles = new Tiles();
        tiles.tiletype = tiletype;
        tiles.shipid = -1;
        return tiles;
    }

    public static Tiles ship(int shipid) {
        if (shipid == -1) System.out.println(MikolasovyConsoleBarvy.PURPLE + "WARN: ID lodě nemůže být -1");

        Tiles tiles = new Tiles();
        tiles.tiletype = Type.SHIP;
        tiles.shipid = shipid;
        return tiles;
    }

    public static Tiles water() {
        return makeTile(Tiles.Type.WATER);
    }

    public void hit() {
        if (this.tiletype == Type.SHIP) this.tiletype = Type.HIT;
        if (this.tiletype == Type.WATER) this.tiletype = Type.MISS;
    }

    public enum Type {
        WATER, SHIP, MISS, HIT
    }
}

