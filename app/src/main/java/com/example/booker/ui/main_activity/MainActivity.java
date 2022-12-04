package com.example.booker.ui.main_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.booker.R;
import com.example.booker.ui.image.Dashboard;
import com.example.booker.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    AppBarConfiguration appBarConfiguration;
    private MainActivityViewModel viewModel;

    private FloatingActionButton viewGalary;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        initViews();
        setupNavigation();

        viewGalary = findViewById(R.id.view_gallery);
        viewGalary.setOnClickListener(v -> navigateToDashboard());
    }

    private void navigateToDashboard() {
        startActivity(new Intent(this, Dashboard.class));
        finish();
    }

    private void setupNavigation() {
       navController = Navigation.findNavController(this, R.id.nav_host_fragment);
       setSupportActionBar(toolbar);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.treatments,
                R.id.logout)
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_drawer);
        toolbar = findViewById(R.id.myToolbar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (FirebaseAuth.getInstance().getCurrentUser().getEmail().contains("manager") ||
                FirebaseAuth.getInstance().getCurrentUser().getEmail().contains("admin")) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
        }
        else {
            getMenuInflater().inflate(R.menu.menu_for_all, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    public void logout(MenuItem item) {
        viewModel.logout();
        navigateToLogin();
    }

    private void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}