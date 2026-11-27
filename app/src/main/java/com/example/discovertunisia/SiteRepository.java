package com.example.discovertunisia;

import java.util.ArrayList;
import java.util.List;

public class SiteRepository {

    public static final List<Site> SITES = new ArrayList<>();

    static {
        SITES.add(new Site("El Jem", "★ 4.8 (12k avis)",
                "L'amphithéâtre d'El Jem est l'un des plus grands amphithéâtres romains au monde, un site historique à ne pas manquer en Tunisie.",
                "historique", "Site historique", "El Jem, Tunisie",
                R.drawable.eljem, 35.2964, 10.7068));

        SITES.add(new Site("Sidi Bou Saïd", "★ 4.7 (8k avis)",
                "Un village pittoresque aux couleurs bleues et blanches, perché au-dessus de la mer.",
                "historique", "Village pittoresque", "Sidi Bou Saïd, Tunisie",
                R.drawable.sidibousaid, 36.8700, 10.3411));

        SITES.add(new Site("La Médina de Tunis", "★ 4.6 (10k avis)",
                "Un voyage au cœur de l'histoire, entre souks, ruelles et monuments anciens.",
                "historique", "Site historique", "Tunis, Tunisie",
                R.drawable.medina, 36.7981, 10.1708));

        SITES.add(new Site("Djerba", "★ 4.7 (6k avis)",
                "Une île aux mille charmes, avec ses plages, ses palmiers et son artisanat.",
                "balneaire", "Site balnéaire", "Djerba, Tunisie",
                R.drawable.djerba, 33.8076, 10.8451));
    }
}