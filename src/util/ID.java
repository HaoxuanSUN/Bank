package util;

import java.util.UUID;

public class ID {
    private final UUID id;

    public ID() {
        this.id = UUID.randomUUID();
    }
    public ID(String s) {
        this.id = UUID.fromString(s);
    }
    public UUID getId() {
        return id;
    }
    @Override
    public String toString() {
        return id.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ID)) {
            return false;
        }
        return id.equals(((ID)o).getId());
    }
}
