package com.utopik.sensors;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
            case 7:
                mTitle = getString(R.string.title_section7);
                break;
            case 8:
                mTitle = getString(R.string.title_section8);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private SensorManager mSensorManager;
        private Sensor mSensor;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        private SensorEventListener listener = new SensorEventListener() {
            public void onSensorChanged(SensorEvent event){
                TextView textView = (TextView) getActivity().findViewById(R.id.section_label);
                switch (event.sensor.getType()) {
                    case Sensor.TYPE_ACCELEROMETER:
                        textView.setText("X: " + Float.toString(event.values[0]) + " m/s\n");
                        textView.append("Y: " + Float.toString(event.values[1]) + " m/s\n");
                        textView.append("Z: " + Float.toString(event.values[2]) + " m/s");
                        break;

                    case Sensor.TYPE_GRAVITY:
                        textView.setText("X: " + Float.toString(event.values[0]) + " m/s\n");
                        textView.append("Y: " + Float.toString(event.values[1]) + " m/s\n");
                        textView.append("Z: " + Float.toString(event.values[2]) + " m/s");
                        break;

                    case Sensor.TYPE_GYROSCOPE:
                        textView.setText("X: " + Float.toString(event.values[0]) + " rad/s\n");
                        textView.append("Y: " + Float.toString(event.values[1]) + " rad/s\n");
                        textView.append("Z: " + Float.toString(event.values[2]) + " rad/s");
                        break;

                    case Sensor.TYPE_MAGNETIC_FIELD:
                        textView.setText("X: " + Float.toString(event.values[0]) + " uT\n");
                        textView.append("Y: " + Float.toString(event.values[1]) + " uT\n");
                        textView.append("Z: " + Float.toString(event.values[2]) + " uT");
                        break;

                    case Sensor.TYPE_LIGHT:
                        textView.setText("Ambient light level: " + Float.toString(event.values[0])
                                + " lux");
                        break;

                    case Sensor.TYPE_PROXIMITY:
                        textView.setText("Distance: " + Float.toString(event.values[0]) + " cm");
                        break;

                    case Sensor.TYPE_PRESSURE:
                        textView.setText("Pression atmoshpérique: " +
                                Float.toString(event.values[0]) + " hPa");
                        break;

                    case Sensor.TYPE_LINEAR_ACCELERATION:
                        textView.setText("X: " + Float.toString(event.values[0]) + " m/s²\n");
                        textView.append("Y: " + Float.toString(event.values[1]) + " m/s²\n");
                        textView.append("Z: " + Float.toString(event.values[2]) + " m/s²");
                        break;
                }
            }

            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
                    for (Sensor sensor : deviceSensors)
                        textView.append(sensor.getName() + "\n");
                    break;

                case 2:
                    mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                    mSensorManager.registerListener(listener, mSensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
                    break;

                case 3:
                    mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
                    mSensorManager.registerListener(listener, mSensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
                    break;

                case 4:
                    mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                    mSensorManager.registerListener(listener, mSensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
                    break;

                case 5:
                    mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                    mSensorManager.registerListener(listener, mSensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
                    break;

                case 6:
                    mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                    mSensorManager.registerListener(listener, mSensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
                    break;

                case 7:
                    mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                    mSensorManager.registerListener(listener, mSensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
                    break;

                case 8:
                    mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
                    mSensorManager.registerListener(listener, mSensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
                    break;

                case 9:
                    mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
                    mSensorManager.registerListener(listener, mSensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
                    break;
            }
            return rootView;
        }
        @Override
        public void onDetach() {
            super.onDetach();
            mSensorManager.unregisterListener(listener);
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
