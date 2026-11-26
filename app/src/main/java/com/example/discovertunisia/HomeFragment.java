package com.example.discovertunisia;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.carteElJem).setOnClickListener(v -> ouvrirDetail(0));
        view.findViewById(R.id.carteSidiBouSaid).setOnClickListener(v -> ouvrirDetail(1));

        view.findViewById(R.id.voirTout).setOnClickListener(v ->
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new SitesFragment())
                        .addToBackStack(null)
                        .commit());

        EditText champRecherche = view.findViewById(R.id.champRecherche);
        champRecherche.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                rechercher(champRecherche.getText().toString().trim());
                return true;
            }
            return false;
        });
    }

    private void ouvrirDetail(int index) {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, SiteDetailFragment.nouveau(index))
                .addToBackStack(null)
                .commit();
    }

    private void rechercher(String texte) {
        if (texte.isEmpty()) {
            Toast.makeText(requireContext(), "Tapez le nom d'un lieu", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < SiteRepository.SITES.size(); i++) {
            Site s = SiteRepository.SITES.get(i);
            if (s.nom.toLowerCase().contains(texte.toLowerCase())) {
                ouvrirDetail(i);
                return;
            }
        }

        Toast.makeText(requireContext(), "Aucun lieu trouvé pour : " + texte,
                Toast.LENGTH_SHORT).show();
    }
}