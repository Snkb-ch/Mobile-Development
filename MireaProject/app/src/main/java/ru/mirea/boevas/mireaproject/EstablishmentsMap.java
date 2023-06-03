package ru.mirea.boevas.mireaproject;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import ru.mirea.boevas.mireaproject.databinding.ActivityMainBinding;


public class EstablishmentsMap extends Fragment {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private MapView mapView = null;
    private MyLocationNewOverlay locationNewOverlay;
    private ActivityMainBinding binding;

    public EstablishmentsMap() {
        // Required empty public constructor
    }

    public static EstablishmentsMap newInstance(String param1, String param2) {
        EstablishmentsMap fragment = new EstablishmentsMap();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_establishments_map, container, false);

        mapView = rootView.findViewById(R.id.mapView);
        mapView.setZoomRounding(true);
        mapView.setMultiTouchControls(true);
        IMapController mapController = mapView.getController();
        mapController.setZoom(15.0);
        GeoPoint startPoint = new GeoPoint(55.794229, 37.700772);
        mapController.setCenter(startPoint);
        CompassOverlay compassOverlay = new CompassOverlay(requireContext(), new InternalCompassOrientationProvider(requireContext()), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);
        final Context context = requireActivity().getApplicationContext();
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(mapView);
        scaleBarOverlay.setCentred(true);
        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        mapView.getOverlays().add(scaleBarOverlay);
        Marker marker = new Marker(mapView);
        marker.setPosition(new GeoPoint(55.7247, 37.5624));
        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(requireContext(), "Surf Coffee x Sport \n" +
                        "Ulitsa Usacheva, 62" +
                        "Moscow", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        Marker marker2 = new Marker(mapView);
        marker2.setPosition(new GeoPoint(55.6700, 37.4812));
        marker2.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(requireContext(), "МИРЭА - Проспект Вернадского 78", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        Marker marker3 = new Marker(mapView);
        marker3.setPosition(new GeoPoint(55.6650, 37.4819));
        marker3.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(requireContext(), "ТЦ Звездочка", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        mapView.getOverlays().add(marker);
        mapView.getOverlays().add(marker2);
        mapView.getOverlays().add(marker3);

        marker.setIcon(ResourcesCompat.getDrawable(requireContext().getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));
        marker.setTitle("Title");

        marker2.setIcon(ResourcesCompat.getDrawable(requireContext().getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));
        marker2.setTitle("Title2");

        marker3.setIcon(ResourcesCompat.getDrawable(requireContext().getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));
        marker3.setTitle("Title3");

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            locationNewOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(requireContext()), mapView);
            locationNewOverlay.enableMyLocation();
            mapView.getOverlays().add(locationNewOverlay);
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()));
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Configuration.getInstance().save(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()));
        if (mapView != null) {
            mapView.onPause();
        }
    }
}
