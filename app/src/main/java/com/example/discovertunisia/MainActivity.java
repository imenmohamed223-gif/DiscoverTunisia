package com.example.discovertunisia;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle boutonMenu = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.menu_ouvrir, R.string.menu_fermer);
        drawerLayout.addDrawerListener(boutonMenu);
        boutonMenu.syncState();

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(element -> {
            ouvrirElement(element.getItemId());
            drawerLayout.closeDrawers();
            return true;
        });

        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnItemSelectedListener(element -> {
            ouvrirElement(element.getItemId());
            return true;
        });

        if (savedInstanceState == null) {
            ouvrirElement(R.id.nav_accueil);
        }
    }

    private void ouvrirElement(int id) {
        if (id == R.id.nav_accueil) {
            afficherFragment(new HomeFragment(), "Discover Tunisia");
        } else if (id == R.id.nav_sites) {
            afficherFragment(new SitesFragment(), "Sites touristiques");
        } else if (id == R.id.nav_itineraires) {
            afficherFragment(new ItinerairesFragment(), "Itinéraires");
        } else if (id == R.id.nav_favoris) {
            afficherFragment(new FavorisFragment(), "Favoris");
        } else if (id == R.id.nav_apropos) {
            afficherFragment(new AboutFragment(), "À propos");
        } else if (id == R.id.nav_parametres) {
            Toast.makeText(this, "Paramètres", Toast.LENGTH_SHORT).show();
            return;
        } else if (id == R.id.nav_deconnexion) {
            Toast.makeText(this, "Déconnexion", Toast.LENGTH_SHORT).show();
            return;
        }

        navigationView.setCheckedItem(id);
        MenuItem item = bottomNavigation.getMenu().findItem(id);
        if (item != null) item.setChecked(true);
    }

    private void afficherFragment(Fragment fragment, String titre) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titre);
        }
    }
}
