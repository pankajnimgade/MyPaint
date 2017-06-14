package irobot.test.assignment.paint.color.picker;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import irobot.test.assignment.paint.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ColorPickerFragment.OnFragmentColorSelectListener} interface
 * to handle interaction events.
 * Use the {@link ColorPickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ColorPickerFragment extends DialogFragment {

    public static final String TAG = ColorPickerFragment.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_COLOR_VALUE = "COLOR_VALUE";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private SeekBar red_SeekBar;
    private int colorRedValue;

    private SeekBar green_SeekBar;
    private int colorGreenValue;

    private SeekBar blue_SeekBar;
    private int colorBlueValue;

    private int colorValue = Color.BLACK;

    private View view_color;

    private Button selectButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentColorSelectListener mListener;

    public ColorPickerFragment() {
        // Required empty public constructor
    }


    public static ColorPickerFragment newInstance(int colorValues) {
        ColorPickerFragment fragment = new ColorPickerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLOR_VALUE, colorValues);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            colorRedValue = Color.red(getArguments().getInt(ARG_COLOR_VALUE));
            colorGreenValue = Color.green(getArguments().getInt(ARG_COLOR_VALUE));
            colorBlueValue = Color.blue(getArguments().getInt(ARG_COLOR_VALUE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View colorPickView = inflater.inflate(R.layout.fragment_color_picker, container, false);
        initializeView(colorPickView);

        return colorPickView;
    }

    private void initializeView(final View colorPickView) {
        view_color = (View) colorPickView.findViewById(R.id.ColorPickerFragment_View_color);

        red_SeekBar = (SeekBar) colorPickView.findViewById(R.id.ColorPickerFragment_seekBar_red);
        red_SeekBar.setOnSeekBarChangeListener(redBarChangeListener);
        red_SeekBar.setProgress(colorRedValue);

        green_SeekBar = (SeekBar) colorPickView.findViewById(R.id.ColorPickerFragment_seekBar_green);
        green_SeekBar.setOnSeekBarChangeListener(greenBarChangeListener);
        green_SeekBar.setProgress(colorGreenValue);

        blue_SeekBar = (SeekBar) colorPickView.findViewById(R.id.ColorPickerFragment_seekBar_blue);
        blue_SeekBar.setOnSeekBarChangeListener(blueBarChangeListener);
        blue_SeekBar.setProgress(colorBlueValue);


        selectButton = (Button) colorPickView.findViewById(R.id.ColorPickerFragment_select_button);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onColorSelection(colorValue);
                dismiss();
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onColorSelection(colorValue);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentColorSelectListener) {
            mListener = (OnFragmentColorSelectListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentColorSelectListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentColorSelectListener {
        // TODO: Update argument type and name
        void onColorSelection(int colorValue);
    }

    SeekBar.OnSeekBarChangeListener redBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            colorRedValue = seekBar.getProgress();
            colorValue = Color.rgb(colorRedValue, colorGreenValue, colorBlueValue);
            view_color.setBackgroundColor(colorValue);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d(TAG, "onStopTrackingTouch: red" + seekBar.getProgress());
            colorRedValue = seekBar.getProgress();
            colorValue = Color.rgb(colorRedValue, colorGreenValue, colorBlueValue);
            view_color.setBackgroundColor(colorValue);
        }
    };

    SeekBar.OnSeekBarChangeListener greenBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            colorGreenValue = seekBar.getProgress();
            colorValue = Color.rgb(colorRedValue, colorGreenValue, colorBlueValue);
            view_color.setBackgroundColor(colorValue);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d(TAG, "onStopTrackingTouch: green" + seekBar.getProgress());
            colorGreenValue = seekBar.getProgress();
            colorValue = Color.rgb(colorRedValue, colorGreenValue, colorBlueValue);
            view_color.setBackgroundColor(colorValue);
        }
    };


    SeekBar.OnSeekBarChangeListener blueBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            colorBlueValue = seekBar.getProgress();
            colorValue = Color.rgb(colorRedValue, colorGreenValue, colorBlueValue);
            view_color.setBackgroundColor(colorValue);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d(TAG, "onStopTrackingTouch: blue" + seekBar.getProgress());
            colorBlueValue = seekBar.getProgress();
            colorValue = Color.rgb(colorRedValue, colorGreenValue, colorBlueValue);
            view_color.setBackgroundColor(colorValue);
        }
    };

}
