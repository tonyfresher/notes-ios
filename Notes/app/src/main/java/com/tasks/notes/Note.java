package com.tasks.notes;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Note implements Serializable, Parcelable {
    public final static String NAME = "Note";
    public final static int DEFAULT_COLOR = Color.parseColor("#ffffff");
    public final static SimpleDateFormat ISO8601_DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    public final static Comparator<Note> BY_NAME_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
    public final static Comparator<Note> BY_CREATED_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {
            return o2.getCreated().compareTo(o1.getCreated());
        }
    };
    public final static Comparator<Note> BY_EDITED_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {
            return o2.getEdited().compareTo(o1.getEdited());
        }
    };
    public final static Comparator<Note> BY_VIEWED_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {
            return o2.getViewed().compareTo(o1.getViewed());
        }
    };

    private long id;
    private String name;
    private String description;
    private int color;

    private String created;
    private String edited;
    private String viewed;

    public Note() {
        color = DEFAULT_COLOR;
        String now = ISO8601_DATE_FORMAT.format(new Date());
        created = now;
        edited = now;
        viewed = now;
    }

    public Note(long id, String name, String description, int color,
                String created, String edited, String viewed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.created = created;
        this.edited = edited;
        this.viewed = viewed;
    }

    protected Note(Parcel in) {
        id = in.readLong();
        name = in.readString();
        description = in.readString();
        color = in.readInt();
        created = in.readString();
        edited = in.readString();
        viewed = in.readString();

    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(color);
        dest.writeString(created);
        dest.writeString(edited);
        dest.writeString(viewed);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getViewed() {
        return viewed;
    }

    public void setViewed(String viewed) {
        this.viewed = viewed;
    }

    public static String dateToIsoString(Date date) {
        return ISO8601_DATE_FORMAT.format(date);
    }

    @Override
    public String toString() {
        return Long.toString(id) + "\n" + created + "\n" + edited + "\n" + viewed;
    }
}