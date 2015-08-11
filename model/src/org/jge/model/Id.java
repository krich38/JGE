package org.jge.model;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Id<T> {

    public final String id;

    /**
     * Required for Kryonet deserialisation
     */
    public Id() {
        this(UUID.randomUUID().toString());
    }

    public Id(String id) {
        this.id = id;
    }

    public static <TT> Id<TT> generate() {
        return new Id<>(UUID.randomUUID().toString());

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Id && Objects.equals(this.id, ((Id) other).id));
    }

    @Override
    public String toString() {
        return "Id(" + id + ")";
    }

}
