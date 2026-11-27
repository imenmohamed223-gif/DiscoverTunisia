package com.example.discovertunisia;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class ItinerairesFragment extends Fragment {

    private LinearLayout listeItineraires;

    public ItinerairesFragment() {
        super(R.layout.fragment_itineraires);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listeItineraires = view.findViewById(R.id.listeItineraires);


        ajouterItineraire("Escapade à Sidi Bou Saïd", "2 jours • 5 lieux", R.drawable.sidibousaid);
        ajouterItineraire("Circuit historique", "1 jour • 3 lieux", R.drawable.eljem);

        // 2. Le bouton ouvre une boîte de dialogue pour créer un itinéraire
        view.findViewById(R.id.boutonNouvelItineraire)
                .setOnClickListener(v -> ouvrirDialogue());
    }


    private void ouvrirDialogue() {
        EditText champNom = new EditText(requireContext());
        champNom.setHint("Nom de l'itinéraire");
        champNom.setInputType(InputType.TYPE_CLASS_TEXT);

        new AlertDialog.Builder(requireContext())
                .setTitle("Nouvel itinéraire")
                .setView(champNom)
                .setPositiveButton("Ajouter", (dialogue, which) -> {
                    String nom = champNom.getText().toString().trim();
                    if (nom.isEmpty()) {
                        Toast.makeText(requireContext(), "Écrire un nom", Toast.LENGTH_SHORT).show();
                    } else {
                        ajouterItineraire(nom, "1 jour • 0 lieu", R.drawable.medina);
                    }
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void ajouterItineraire(String titre, String detail, int image) {
        View carte = LayoutInflater.from(requireContext())
                .inflate(R.layout.item_itineraire, listeItineraires, false);

        ((TextView) carte.findViewById(R.id.titreItineraire)).setText(titre);
        ((TextView) carte.findViewById(R.id.detailItineraire)).setText(detail);
        ((ImageView) carte.findViewById(R.id.imageItineraire)).setImageResource(image);

        carte.setOnClickListener(v ->
                Toast.makeText(requireContext(), titre, Toast.LENGTH_SHORT).show());

        listeItineraires.addView(carte);
    }
}
