package com.cavetale.itemmarker;

import com.cavetale.dirty.Dirty;
import java.util.Optional;
import java.util.UUID;
import org.bukkit.inventory.ItemStack;

public final class ItemMarker {
    public static final String ITEM_ID_KEY = "cavetale.id";
    public static final String ITEM_OWNER_KEY = "cavetale.owner";
    private ItemMarker() { }

    // --- Marker methods

    public static ItemStack setMarker(ItemStack item, String key, Object value) {
        item = Dirty.toCraftItemStack(item);
        Optional<Object> tag = Dirty.accessItemNBT(item, true);
        Dirty.setNBT(tag, key, value);
        return item;
    }

    public static Object getMarker(ItemStack item, String key) {
        Optional<Object> tag = Dirty.accessItemNBT(item, false);
        if (!tag.isPresent()) return null;
        tag = Dirty.getNBT(tag, key);
        return Dirty.fromNBT(tag);
    }

    // --- Custom ID methods

    public static ItemStack setCustomId(ItemStack item, String customId) {
        return setMarker(item, ITEM_ID_KEY, customId);
    }

    public static String getCustomId(ItemStack item) {
        Object result = getMarker(item, ITEM_ID_KEY);
        if (result == null) return null;
        if (result instanceof String) return (String)result;
        return null;
    }

    public static boolean hasCustomId(ItemStack item, String customId) {
        return customId.equals(getMarker(item, ITEM_ID_KEY));
    }

    // --- Owner marker

    public static ItemStack setOwner(ItemStack item, UUID owner) {
        return setMarker(item, ITEM_OWNER_KEY, owner.toString());
    }

    public static UUID getOwner(ItemStack item) {
        Object val = getMarker(item, ITEM_OWNER_KEY);
        if (val == null || !(val instanceof String)) return null;
        try {
            return UUID.fromString((String)val);
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }

    public static boolean isOwner(ItemStack item, UUID owner) {
        return owner.equals(getOwner(item));
    }
}
