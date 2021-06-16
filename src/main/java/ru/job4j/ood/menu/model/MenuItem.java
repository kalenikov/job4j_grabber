package ru.job4j.ood.menu.model;

public class MenuItem implements Item {
    private final int id;
    private final Item parent;
    private final String label;
    private final Callbackable callback;
    private final int level;

    public MenuItem(int id, Item parent, String label) {
        this(id, parent, label, () -> true);
    }

    public MenuItem(int id, Item parent, String label, Callbackable callback) {
        this.id = id;
        this.parent = parent;
        this.label = label;
        this.callback = callback;
        level = parent != null ? parent.getLevel() + 1 : 0;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public boolean execute() {
        return callback.execute();
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
