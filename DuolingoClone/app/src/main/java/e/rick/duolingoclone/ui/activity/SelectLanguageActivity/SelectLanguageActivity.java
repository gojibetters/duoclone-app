package e.rick.duolingoclone.ui.activity.SelectLanguageActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.R;

/**
 * Created by Rick on 3/8/2018.
 */

public class SelectLanguageActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.back_button)
    ImageView backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

        FirebaseApp.initializeApp(this);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        LanguageAdapter languageAdapter = new LanguageAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        @SuppressLint("UseCompatLoadingForDrawables") RvDividerItemDecoration itemDecoration = new RvDividerItemDecoration(getDrawable(R.drawable.recycler_view_divider));

        recyclerView.setAdapter(languageAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);

        backButton.setOnClickListener(v -> onBackPressed());
    }
}
