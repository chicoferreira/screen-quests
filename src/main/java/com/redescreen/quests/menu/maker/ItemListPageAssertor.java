package com.redescreen.quests.menu.maker;

import com.redescreen.quests.menu.item.MenuItem;
import com.redescreen.quests.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ItemListPageAssertor<T> extends ItemListAssertor<T> {

    private int maxItensPerPage;

    public ItemListPageAssertor(Function<T, MenuItem> transformerFunction, Function<User, List<T>> tGetter, int maxItensPerPage) {
        super(transformerFunction, tGetter);
        this.maxItensPerPage = maxItensPerPage;
    }

    public Map<Integer, List<T>> buildPages(User user) {
        List<T> items = this.getTGetter().apply(user);

        Map<Integer, List<T>> pages = new HashMap<>();

        int pageIndex = 1;
        int itemIndex = 0;
        for (T item : items) {
            if (!pages.containsKey(pageIndex)) {
                pages.put(pageIndex, new ArrayList<>());
            }
            List<T> pageItems = pages.get(pageIndex);
            pageItems.add(item);

            if (++itemIndex == this.maxItensPerPage) {
                itemIndex = 0;
                pageIndex++;
            }
        }

        return pages;
    }

    @Override
    public MenuItem get(int slot, User user) {
        return super.get(slot, buildPages(user).get(user.getCurrentPage()));
    }

    @Override
    public Map<Integer, MenuItem> build(User user) {
        return super.build(buildPages(user).get(user.getCurrentPage()));
    }

}
