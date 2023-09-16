package org.github.sxntido;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
    public static ItemStack item(Material paramMaterial, String paramString1, String paramString2) {
        ItemStack itemStack = new ItemStack(paramMaterial);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(paramString1);
        itemMeta.setLore((List)(paramString2.isEmpty() ? new ArrayList() : Arrays.asList(paramString2.split("\\n"))));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack item(Material paramMaterial, int paramInt, short paramShort, String paramString1, String paramString2) {
        ItemStack itemStack = new ItemStack(paramMaterial, paramInt, paramShort);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(paramString1);
        itemMeta.setLore((List)(paramString2.isEmpty() ? new ArrayList() : Arrays.asList(paramString2.split("\\n"))));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack item(Material paramMaterial, int paramInt1, short paramShort, String paramString1, String paramString2, Enchantment paramEnchantment, int paramInt2) {
        ItemStack itemStack = new ItemStack(paramMaterial, paramInt1, paramShort);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(paramString1);
        itemMeta.addEnchant(paramEnchantment, paramInt2, true);
        itemMeta.setLore((List)(paramString2.isEmpty() ? new ArrayList() : Arrays.asList(paramString2.split("\\n"))));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack item(Material paramMaterial, int paramInt, short paramShort, String paramString, List<String> paramList) {
        ItemStack itemStack = new ItemStack(paramMaterial, paramInt, paramShort);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(paramString);
        itemMeta.setLore(paramList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack item(Material paramMaterial, int paramInt1, short paramShort, String paramString, List<String> paramList, Enchantment paramEnchantment, int paramInt2) {
        ItemStack itemStack = new ItemStack(paramMaterial, paramInt1, paramShort);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(paramString);
        itemMeta.setLore(paramList);
        itemMeta.addEnchant(paramEnchantment, paramInt2, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack skull(Material paramMaterial, int paramInt, short paramShort, String paramString1, String paramString2, String paramString3) {
        ItemStack itemStack = new ItemStack(paramMaterial, paramInt, paramShort);
        SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
        skullMeta.setOwner(paramString3);
        skullMeta.setDisplayName(paramString1);
        skullMeta.setLore((List)(paramString2.isEmpty() ? new ArrayList() : Arrays.asList(paramString2.split("\\n"))));
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    public static ItemStack createSkull(Material paramMaterial, int paramInt, short paramShort, String paramString1, String paramString2, String paramString3) {
        if (!paramString3.startsWith("http://textures.minecraft.net/texture/")) {
            paramString3 = "http://textures.minecraft.net/texture/" + paramString3;
        }

        ItemStack itemStack = new ItemStack(paramMaterial, paramInt, paramShort);
        SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
        skullMeta.setDisplayName(paramString1);
        skullMeta.setLore((List)(paramString2.isEmpty() ? new ArrayList() : Arrays.asList(paramString2.split("\\n"))));
        if (paramString3.isEmpty()) {
            return itemStack;
        } else {
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), (String)null);
            byte[] arrayOfByte = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", paramString3).getBytes());
            gameProfile.getProperties().put("textures", new Property("textures", new String(arrayOfByte)));

            try {
                Field field = skullMeta.getClass().getDeclaredField("profile");
                field.setAccessible(true);
                field.set(skullMeta, gameProfile);
            } catch (IllegalArgumentException | NoSuchFieldException | IllegalAccessException var11) {
                var11.printStackTrace();
            }

            itemStack.setItemMeta(skullMeta);
            return itemStack;
        }
    }

    public static ItemStack lore(Material paramMaterial, short paramShort, int paramInt, List<String> paramList) {
        ItemStack itemStack = new ItemStack(paramMaterial, paramInt, paramShort);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(paramList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack lore(ItemStack paramItemStack, List<String> paramList) {
        ItemMeta itemMeta = paramItemStack.getItemMeta();
        itemMeta.setLore(paramList);
        paramItemStack.setItemMeta(itemMeta);
        return paramItemStack;
    }

    public static ItemStack lore(Material paramMaterial, short paramShort, int paramInt, String paramString) {
        ItemStack itemStack = new ItemStack(paramMaterial, paramInt, paramShort);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(paramString);
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack lore(ItemStack paramItemStack, String paramString) {
        ItemMeta itemMeta = paramItemStack.getItemMeta();
        List<String> list = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList();
        ((List)list).add(paramString);
        itemMeta.setLore((List)list);
        paramItemStack.setItemMeta(itemMeta);
        return paramItemStack;
    }

    public static ItemStack name(Material paramMaterial, short paramShort, int paramInt, String paramString) {
        ItemStack itemStack = new ItemStack(paramMaterial, paramInt, paramShort);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(paramString);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack name(ItemStack paramItemStack, String paramString) {
        ItemMeta itemMeta = paramItemStack.getItemMeta();
        itemMeta.setDisplayName(paramString);
        paramItemStack.setItemMeta(itemMeta);
        return paramItemStack;
    }

    public static ItemStack nameLore(Material paramMaterial, short paramShort, int paramInt, List<String> paramList, String paramString) {
        ItemStack itemStack = new ItemStack(paramMaterial, paramInt, paramShort);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(paramString);
        itemMeta.setLore(paramList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack nameLore(ItemStack paramItemStack, List<String> paramList, String paramString) {
        ItemMeta itemMeta = paramItemStack.getItemMeta();
        itemMeta.setDisplayName(paramString);
        itemMeta.setLore(paramList);
        paramItemStack.setItemMeta(itemMeta);
        return paramItemStack;
    }

    public static ItemStack nameLore(Material paramMaterial, short paramShort, int paramInt, String paramString1, String paramString2) {
        ItemStack itemStack = new ItemStack(paramMaterial, paramInt, paramShort);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(paramString2);
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(paramString1);
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack nameLore(ItemStack paramItemStack, String paramString1, String paramString2) {
        ItemMeta itemMeta = paramItemStack.getItemMeta();
        itemMeta.setDisplayName(paramString2);
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(paramString1);
        itemMeta.setLore(arrayList);
        paramItemStack.setItemMeta(itemMeta);
        return paramItemStack;
    }
}
