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
}
