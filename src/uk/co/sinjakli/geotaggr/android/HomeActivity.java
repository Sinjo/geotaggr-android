package uk.co.sinjakli.geotaggr.android;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class HomeActivity extends Activity {
	
	private final static String TAG = "HomeActivity";
	
	private LocationManager locationManager;
	private LocationListener locationListener;
	private int frequency = 60000;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
      	locationListener = new LocationListener() {

    		public void onStatusChanged(String provider, int status, Bundle extras) {
    			// TODO Auto-generated method stub

    		}

    		public void onProviderEnabled(String provider) {
    			// TODO Auto-generated method stub

    		}

    		public void onProviderDisabled(String provider) {
    			// TODO Auto-generated method stub

    		}

    		public void onLocationChanged(Location location) {
    			Log.i(TAG, "Latitude: " + location.getLatitude() + " Longitude: " + location.getLongitude());
    		}
    	};
        
        setContentView(R.layout.main);
        
        // Get value from spinner
        final Spinner frequencySpinner = (Spinner)findViewById(R.id.spi_update_frequency);
        frequencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int pos, long id) {
				switch (pos) {
					case 0: frequency = 1; break;
					case 1: frequency = 5; break;
					case 2: frequency = 10; break;
					case 3: frequency = 15; break;
					case 4: frequency = 30; break;
					default: frequency = 1;
				}
				frequency *= 60000;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
		});
        
        // Set up click actions for buttons
        final Button startButton = (Button) findViewById(R.id.btn_start_logging);
        final Button stopButton = (Button) findViewById(R.id.btn_stop_logging);
        
        startButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				locationManager.requestLocationUpdates("gps", frequency, 10, locationListener);
			}
        	
        });
        
        stopButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				locationManager.removeUpdates(locationListener);
			}
        	
        });
    }
}