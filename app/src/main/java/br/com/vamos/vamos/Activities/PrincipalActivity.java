package br.com.vamos.vamos.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import br.com.vamos.vamos.Fragments.AjustesFragment;
import br.com.vamos.vamos.Fragments.BuscarFragment;
import br.com.vamos.vamos.Fragments.PerfilFragment;
import br.com.vamos.vamos.R;

public class PrincipalActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.setSelectedItemId(R.id.navigation_perfil);
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.navigation_perfil:
                fragment = new PerfilFragment();
                break;
            case R.id.navigation_busca:
                fragment = new BuscarFragment();
                break;
            case R.id.navigation_ajustes:
                fragment = new AjustesFragment();
                break;
        }

        return loadFragment(fragment);
    }
}
