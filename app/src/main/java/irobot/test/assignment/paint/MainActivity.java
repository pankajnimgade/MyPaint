package irobot.test.assignment.paint;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import irobot.test.assignment.paint.color.picker.ColorPickerFragment;
import irobot.test.assignment.paint.view.MyDrawView;

public class MainActivity extends AppCompatActivity implements ColorPickerFragment.OnFragmentColorSelectListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private CoordinatorLayout mainActivityCoordinatorLayout;
    private Snackbar snackbar;
    private Button color_Button;
    private Button eraser_Button;

    private MyDrawView myDrawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeView();
        if (savedInstanceState == null) {
            snackNotify("Pick a color...", Snackbar.LENGTH_INDEFINITE);
        }
    }

    private void initializeView() {
        mainActivityCoordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .MainActivity_CoordinatorLayout);
        myDrawView = (MyDrawView) findViewById(R.id.Main_Activity_MyDrawView_canvas);

        color_Button = (Button) findViewById(R.id.Main_Activity_color_button);
        color_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackNotify("Pick color", Snackbar.LENGTH_SHORT);
                DialogFragment dialogFragment = ColorPickerFragment.newInstance(myDrawView
                        .getPaint().getColor());
                dialogFragment.show(getSupportFragmentManager(), "ColorPickFragment");
            }
        });


        eraser_Button = (Button) findViewById(R.id.Main_Activity_eraser_button);
        eraser_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackNotify("Eraser ..", Snackbar.LENGTH_SHORT);
                myDrawView.setPaintColor(Color.WHITE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void snackNotify(String message, int snackDuration) {
        dismissSnack();
        if (message != null) {
            snackbar = Snackbar.make(mainActivityCoordinatorLayout, message,
                    snackDuration).setAction("Action", null);
            snackbar.show();
        } else {
            Log.d(TAG, "snackNotify: message is null");
        }
    }


    private void dismissSnack() {
        if (snackbar != null) {
            if (snackbar.isShown()) {
                snackbar.dismiss();
            }
        }
    }

    @Override
    public void onColorSelection(int colorValue) {
        Log.d(TAG, "onColorSelection: " + colorValue);
        myDrawView.setPaintColor(colorValue);
    }
}
