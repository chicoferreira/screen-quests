package com.redescreen.quests.user;

import com.redescreen.quests.manager.BasicManager;

public class UserManager extends BasicManager<User> {

    private static UserManager instance;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    @Override
    public User get(String name) {
        User user = super.get(name);

        if (user == null) user = this.put(new User(name));
        return user;
    }

    public User put(User user) {
        super.put(user.getName(), user);
        return user;
    }

    @Override
    public void delete(String name) {
        super.delete(name);
    }
}
