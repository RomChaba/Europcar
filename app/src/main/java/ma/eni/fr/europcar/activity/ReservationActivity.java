package ma.eni.fr.europcar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.fragment.ReservationFragment;
import ma.eni.fr.europcar.fragment.VehiculeFragment;
import ma.eni.fr.europcar.model.Vehicule;
import ma.eni.fr.europcar.service.LocationService;
import ma.eni.fr.europcar.service.VehiculeService;

public class ReservationActivity extends AppCompatActivity implements ReservationFragment.OnFragmentInteractionListener,VehiculeFragment.OnFragmentInteractionListener {

    VehiculeFragment vehiculeFragment;
    ReservationFragment reservationFragment;
    List<Vehicule> vehiculeList = new ArrayList<>();
    Vehicule vehicule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
    }

    @Override
    protected void onResume() {
        super.onResume();

        int id = getIntent().getIntExtra("idVehicule",-1);
        vehicule = VehiculeService.getInstance().getVehiculeById(id);

        vehiculeList.add(vehicule);


        vehiculeFragment = (VehiculeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_liste_vehicule);
        reservationFragment = (ReservationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_form_reservation);

        vehiculeFragment.refreshList(vehiculeList);

    }

    @Override
    public void reservationValider(String date_debut, String date_fin, String tarif_jouralier) {

        SimpleDateFormat dt_debut = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dt_fin = new SimpleDateFormat("dd/MM/yyyy");
        Date temp1 = null;
        Date temp2 = null;

        try {
            temp1 = dt_debut.parse(date_debut);
            temp2 = dt_fin.parse(date_fin);
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        reservation(Vehicule vehicule,String date_debut,String date_fin,String tarif_journalier)
        LocationService.getInstance().reservation(vehicule,temp1,temp2,tarif_jouralier);
        Intent intent = new Intent(ReservationActivity.this,LocationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onVehiculeClick(Vehicule vehicule) {

    }
}
