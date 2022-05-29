import java.util.Objects;

/**
 * Класс для хранения результата пения в массиве songArray
 */
public class LineOfSong{
    public String name;
    public String song;

    public LineOfSong(String name,String song)
    {
        this.name = name;
        this.song = song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineOfSong that = (LineOfSong) o;
        return Objects.equals(name, that.name) && Objects.equals(song, that.song);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, song);
    }
}