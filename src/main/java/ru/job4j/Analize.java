package ru.job4j;

import java.util.*;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        User verifer;
        User verifiable;
        int changed = 0;
        int shift = 0;
        int deleted = 0;
        for (int i = 0; i < previous.size(); i++) {
            if (shift == current.size()) {
                deleted += (previous.size()) - i;
                break;
            }
            verifer = previous.get(i);
            verifiable = current.get(shift);
            if (verifer.id == verifiable.id) {
                shift++;
                if (!verifer.name.equals(verifiable.name)) {
                    changed++;
                }
            } else {
                deleted++;
            }
        }
        int added = (current.size() + deleted) - previous.size();
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
