package ubb.barcoaie.lab4_ma;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ubb.barcoaie.lab4_ma.Service.VegetableService;

public class ReadActivity extends AppCompatActivity {
    private VegetableViewModel vegetableViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final VegetableListAdapter adapter = new VegetableListAdapter(new VegetableListAdapter.VegetableDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        vegetableViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new VegetableViewModel(getApplication());
            }
        }).get(VegetableViewModel.class);
        vegetableViewModel.getAllVegetables().observe(this, adapter::submitList);
    }

    @Override
    public void onBackPressed() {
        Intent replyIntent = new Intent();
        setResult(RESULT_OK, replyIntent);
        ReadActivity.this.finish();
    }
}
