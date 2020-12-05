////////////////////////////////////////////////////////////////////
// [LORENZO] [PERINELLO] [1193553]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.model;

public class MenuItem {
    ItemType itemType;
    String name;
    double price;

    public MenuItem(ItemType itemType, String name, double price) {
        this.itemType = itemType;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public ItemType getItemType() {
        return this.itemType;
    }
}