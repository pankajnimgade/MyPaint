package irobot.test.assignment.paint.models;

import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Pankaj Nimgade on 6/13/2017.
 */

public class MyLine {

    private Path path;
    private Paint paint;

    public MyLine(Path path, Paint paint) {
        this.path = path;
        this.paint = paint;
    }

    public Paint getPaint() {
        return paint;
    }

    public Path getPath() {
        return path;
    }
}
