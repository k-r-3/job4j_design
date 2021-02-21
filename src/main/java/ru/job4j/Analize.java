package ru.job4j;

import java.util.*;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Map<Integer, User> map = new HashMap<>();
        User user;
        int changed = 0;
        int added = 0;
        for (int i = 0; i < previous.size(); i++) {
            user = previous.get(i);
            if (!Objects.isNull(user)) {
                map.put(user.id, user);
            }
        }
        for (int i = 0; i < current.size(); i++) {
            user = current.get(i);
            if (!Objects.isNull(user)) {
                User verifiable = map.get(user.id);
                if (verifiable != null) {
                    if (!verifiable.equals(user)) {
                        changed++;
                    }
                } else {
                    added++;
                }
            }
        }
        int deleted =  map.size() - current.size() + added;
        return new Info.Builder()
                .setAdded(added)
                .setChanged(changed)
                .setDeleted(deleted)
                .build();
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            int result = Integer.hashCode(id);
            result = result * 31 + name.hashCode();
            return result;
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        static class Builder {
            private int added;
            private int changed;
            private int deleted;

            public Builder setAdded(int added) {
                this.added = added;
                return this;
            }

            public Builder setChanged(int changed) {
                this.changed = changed;
                return this;
            }

            public Builder setDeleted(int deleted) {
                this.deleted = deleted;
                return this;
            }

            public Info build() {
                Info info = new Info();
                info.added = added;
                info.changed = changed;
                info.deleted = deleted;
                return info;
            }
        }

        @Override
        public String toString() {
            return "added=" + added
                    + ", changed=" + changed
                    + ", deleted=" + deleted;
        }
    }
}
