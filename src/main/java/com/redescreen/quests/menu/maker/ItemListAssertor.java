package com.redescreen.quests.menu.maker;

import com.redescreen.quests.menu.item.MenuItem;
import com.redescreen.quests.user.User;
import com.redescreen.quests.util.InventoryUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ItemListAssertor<T> implements ItemAssertor {

    private Function<T, MenuItem> transformerFunction;
    private Function<User, List<T>> tGetter;

    public ItemListAssertor(Function<T, MenuItem> transformerFunction, Function<User, List<T>> tGetter) {
        this.transformerFunction = transformerFunction;
        this.tGetter = tGetter;
    }

    @Override
    public MenuItem get(int slot, User user) {
        return get(slot, tGetter.apply(user));
    }

    @Override
    public Map<Integer, MenuItem> build(User user) {
        return build(tGetter.apply(user));
    }

    protected MenuItem get(int slot, List<T> tList) {
        int currentSlot = 0;
        for (T t : tList) {
            while (!InventoryUtils.isCentralized(currentSlot)) {
                currentSlot++;
            }

            if (currentSlot++ == slot) {
                return transformerFunction.apply(t);
            }
        }
        return null;
    }

    protected Map<Integer, MenuItem> build(List<T> tList) {
        Map<Integer, MenuItem> build = new HashMap<>();

        int slot = 0;
        for (T t : tList) {
            while (!InventoryUtils.isCentralized(slot)) {
                slot++;
            }

            MenuItem menuItem = transformerFunction.apply(t);
            build.put(slot++, menuItem);
        }
        return build;
    }

    protected Function<T, MenuItem> getTransformerFunction() {
        return transformerFunction;
    }

    protected Function<User, List<T>> getTGetter() {
        return tGetter;
    }
}
