package info.androidhive.rxandroidexamples.observers.model;

/**
 * Created by ravi on 30/01/18.
 */

public class Note {
    int id;
    String note;

    public Note(int id, String note) {
        this.id = id;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Note)) {
            return false;
        }

        return note.equalsIgnoreCase(((Note) obj).getNote());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.note != null ? this.note.hashCode() : 0);
        return hash;
    }
}
