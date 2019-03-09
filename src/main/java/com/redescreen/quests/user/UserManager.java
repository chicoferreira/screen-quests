package com.redescreen.quests.user;

import com.redescreen.quests.manager.BasicManager;

public class UserManager extends BasicManager<User> {

    public void put(User user) {
        super.put(user.getName(), user);
    }

    @Override
    public void delete(String name) {
        super.delete(name);
    }
}
