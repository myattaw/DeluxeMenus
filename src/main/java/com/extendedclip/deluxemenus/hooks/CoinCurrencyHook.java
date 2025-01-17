package com.extendedclip.deluxemenus.hooks;

import com.olziedev.coincurrency.CoinCurrency;
import com.olziedev.coincurrency.db.SQLStorage;
import org.bukkit.entity.Player;

public class CoinCurrencyHook {

    private SQLStorage storage;

    public CoinCurrencyHook() {
        this.storage = CoinCurrency.getSqlStorage();
    }

    public boolean hasEnough(final Player player, final int amount) {
        return storage.getCoins(player.getUniqueId()) >= amount;
    }

    public void takeCoins(final Player player, final int amount) {
        storage.setCoins(player.getUniqueId(), storage.getCoins(player.getUniqueId()) - amount);
    }

    public void giveCoins(final Player player, final int amount) {
        storage.setCoins(player.getUniqueId(), storage.getCoins(player.getUniqueId()) + amount);
    }

    public boolean hooked() {
        return storage != null;
    }

}
