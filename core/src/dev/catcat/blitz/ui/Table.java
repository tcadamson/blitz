package dev.catcat.blitz.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Table implements Node {
    private final Vector2 pos = new Vector2();
    private final Vector2 box = new Vector2();
    private static final Array<Node> nodes = new Array<>();

    @Override
    public void update() {
        this.box.setZero();
        for (Node node : nodes) {
            Vector2 pos = node.getPos();
            Vector2 box = node.getBox();
            node.update();
            pos.set(this.box.x, this.pos.y);
            this.box.set(this.box.x + box.x, Math.max(this.box.y, box.y));
        }
    }

    @Override
    public void draw() {
        for (Node node : nodes) {
            node.draw();
        }
    }

    @Override
    public Vector2 getPos() {
        return pos;
    }

    @Override
    public Vector2 getBox() {
        return box;
    }

    public void setBox(Vector2 box) {
        this.box.set(box);
    }

    public void add(Node node) {
        nodes.add(node);
    }
}
