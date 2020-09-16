package org.halliburton.keycoak.user;

import org.halliburton.keycoak.user.utils.ExternalUser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author André de Mattos Ferraz
 */
class UserCache {
    private static final Map<String, ExternalUser> cache = new HashMap<>();

    static int getCount() {
        return cache.size();
    }

    static void addUser(ExternalUser user) {
        cache.put(user.getUsername(), user);
    }

    static List<ExternalUser> getUsers() {
        return cache.entrySet().stream()
                .map(Map.Entry::getValue)
                .sorted(Comparator.comparing(ExternalUser::getUsername))
                .collect(Collectors.toList());
    }

    static List<ExternalUser> findUsers(String search) {
        return cache.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(u -> (u.getUsername() + ";" + u.getFirstName() + ";" + u.getLastName()).contains(search))
                .sorted(Comparator.comparing(ExternalUser::getUsername))
                .collect(Collectors.toList());
    }

}
