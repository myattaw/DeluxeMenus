package com.extendedclip.deluxemenus.requirement;

import com.extendedclip.deluxemenus.DeluxeMenus;
import com.extendedclip.deluxemenus.menu.MenuHolder;
import org.jetbrains.annotations.NotNull;

public class HasCoinsRequirement extends Requirement {

  private final DeluxeMenus plugin;
  private final boolean invert;
  private final String placeholder;
  private int amount;

  public HasCoinsRequirement(@NotNull final DeluxeMenus plugin, int amount, boolean invert, String placeholder) {
    this.plugin = plugin;
    this.amount = amount;
    this.invert = invert;
    this.placeholder = placeholder;
  }

  @Override
  public boolean evaluate(MenuHolder holder) {
    if (plugin.getVault() == null) {
      return false;
    }

    if (placeholder != null) {
      try {
        String expected = holder.setPlaceholdersAndArguments(placeholder);
        amount = Integer.parseInt(expected);
      } catch (final NumberFormatException exception) {
        plugin.printStacktrace(
            "Invalid amount found for has coins requirement: " + holder.setPlaceholdersAndArguments(placeholder),
            exception
        );
      }
    }
    if (invert) {
      return !plugin.getCoinCurrencyHook().hasEnough(holder.getViewer(), amount);
    } else {
      return plugin.getCoinCurrencyHook().hasEnough(holder.getViewer(), amount);
    }
  }
}
