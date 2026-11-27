package com.example.discovertunisia;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FavorisFragment extends Fragment {

    private View favoriSidi, favoriElJem, favoriDjerba, texteVide;

    public FavorisFragment() {
        super(R.layout.fragment_favoris);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favoriSidi = view.findViewById(R.id.favoriSidi);
        favoriElJem = view.findViewById(R.id.favoriElJem);
        favoriDjerba = view.findViewById(R.id.favoriDjerba);
        texteVide = view.findViewById(R.id.texteVide);


        view.findViewById(R.id.coeurSidi).setOnClickListener(v -> retirer(favoriSidi, "Sidi Bou Saïd"));
        view.findViewById(R.id.coeurElJem).setOnClickListener(v -> retirer(favoriElJem, "El Jem"));
        view.findViewById(R.id.coeurDjerba).setOnClickListener(v -> retirer(favoriDjerba, "Djerba"));
    }

    private void retirer(View carte, String nom) {
        carte.setVisibility(View.GONE);
        Toast.makeText(requireContext(), nom + " retiré des favoris", Toast.LENGTH_SHORT).show();


        boolean vide = favoriSidi.getVisibility() == View.GONE
                && favoriElJem.getVisibility() == View.GONE
                && favoriDjerba.getVisibility() == View.GONE;
        texteVide.setVisibility(vide ? View.VISIBLE : View.GONE);
    }
}
