package com.example.discovertunisia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SiteDetailFragment extends Fragment {

    private static final String CLE_INDEX = "index";

    public static SiteDetailFragment nouveau(int index) {
        SiteDetailFragment f = new SiteDetailFragment();
        Bundle args = new Bundle();
        args.putInt(CLE_INDEX, index);
        f.setArguments(args);
        return f;
    }

    public SiteDetailFragment() {
        super(R.layout.fragment_site_detail);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int index = requireArguments().getInt(CLE_INDEX);
        Site site = SiteRepository.SITES.get(index);

        ((ImageView) view.findViewById(R.id.imageDetail)).setImageResource(site.image);
        ((TextView) view.findViewById(R.id.nomDetail)).setText(site.nom);
        ((TextView) view.findViewById(R.id.noteDetail)).setText(site.note);
        ((TextView) view.findViewById(R.id.descDetail)).setText(site.description);
        ((TextView) view.findViewById(R.id.lieuDetail)).setText("📍  " + site.lieu);
        ((TextView) view.findViewById(R.id.typeDetail)).setText("🏛  " + site.type);
        TextView coeur = view.findViewById(R.id.coeurDetail);
        coeur.setText(site.favori ? "♥" : "♡");
        coeur.setOnClickListener(v -> {
            site.favori = !site.favori;
            coeur.setText(site.favori ? "♥" : "♡");
            Toast.makeText(requireContext(),
                    site.nom + (site.favori ? " ajouté aux favoris" : " retiré des favoris"),
                    Toast.LENGTH_SHORT).show();
        });


        view.findViewById(R.id.boutonCarte).setOnClickListener(v -> {
            Uri geo = Uri.parse("geo:" + site.lat + "," + site.lng
                    + "?q=" + site.lat + "," + site.lng + "(" + Uri.encode(site.nom) + ")");
            Intent intent = new Intent(Intent.ACTION_VIEW, geo);
            try {
                startActivity(intent);
            } catch (Exception e) {

                Uri web = Uri.parse("https://www.google.com/maps/search/?api=1&query="
                        + site.lat + "," + site.lng);
                startActivity(new Intent(Intent.ACTION_VIEW, web));
            }
        });
    }
}