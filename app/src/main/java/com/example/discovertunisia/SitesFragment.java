package com.example.discovertunisia;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.ChipGroup;

public class SitesFragment extends Fragment {

    private LinearLayout liste;

    public SitesFragment() {
        super(R.layout.fragment_sites);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        liste = view.findViewById(R.id.listeSites);

        ChipGroup filtres = view.findViewById(R.id.groupeFiltres);
        filtres.setOnCheckedStateChangeListener((groupe, ids) -> {
            int id = ids.isEmpty() ? R.id.chipTous : ids.get(0);
            if (id == R.id.chipHist) afficher("historique");
            else if (id == R.id.chipNat) afficher("naturel");
            else if (id == R.id.chipBal) afficher("balneaire");
            else afficher(null);
        });

        afficher(null);
    }

    private void afficher(String categorie) {
        liste.removeAllViews();

        for (Site s : SiteRepository.SITES) {
            if (categorie != null && !s.categorie.equals(categorie)) continue;

            View carte = getLayoutInflater().inflate(R.layout.item_site, liste, false);
            ((ImageView) carte.findViewById(R.id.imageSite)).setImageResource(s.image);
            ((TextView) carte.findViewById(R.id.nomSite)).setText(s.nom);
            ((TextView) carte.findViewById(R.id.noteSite)).setText(s.note);
            ((TextView) carte.findViewById(R.id.descSite)).setText(s.description);

            // Clic : ouvrir l'écran détail
            carte.setOnClickListener(v -> {
                int index = SiteRepository.SITES.indexOf(s);
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, SiteDetailFragment.nouveau(index))
                        .addToBackStack(null)   // le bouton retour revient à la liste
                        .commit();
            });

            liste.addView(carte);
        }

        if (liste.getChildCount() == 0) {
            Toast.makeText(requireContext(), "Aucun site dans cette catégorie",
                    Toast.LENGTH_SHORT).show();
        }
    }
}