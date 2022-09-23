package e.rick.duolingoclone.ui.activity.LessonListActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.Data.Repository;
import e.rick.duolingoclone.R;
import e.rick.duolingoclone.Utils.ActivityNavigation;
import e.rick.duolingoclone.Utils.CustomProgressBar;
import e.rick.duolingoclone.Utils.Injection;
import e.rick.duolingoclone.databinding.ActivityLessonListBinding;
import e.rick.duolingoclone.ui.activity.SignInActivity.SignInActivity;

/**
 * Created by Rick on 3/10/2018.
 */

public class LessonListActivity extends AppCompatActivity {

    private static final String TAG = "LessonListActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.basic_bar)
    CustomProgressBar basic1Bar;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.phrases_bar)
    CustomProgressBar phrasesBar;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.greeting_bar)
    CustomProgressBar greetingBar;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.animal_bar)
    CustomProgressBar animalBar;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.food_bar)
    CustomProgressBar foodBar;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.clothing_bar)
    CustomProgressBar clothingBar;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.shortcut_button)
    Button shortcutButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.current_language)
    TextView currentLanguage;

    Repository repository;

    Handler handler;

    private ActivityLessonListBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityLessonListBinding.inflate(getLayoutInflater());

        setContentView(mBinding.getRoot());

        initNavigation();

        ButterKnife.bind(this);

        //checkUserValid();

        initData();

    }

    private void initNavigation () {
        NavHostFragment mNavHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavHostController mNavController = (NavHostController) mNavHostFragment.getNavController();
        NavigationUI.setupWithNavController(mBinding.bottomNavigation, mNavController);

    }

    private void initData() {

        Hawk.init(this).build();

        repository = Injection.provideRepository();

        handler = new Handler();

        repository.getDailyGoal();
        repository.getDailyXp();
        repository.getWeekXp();
        repository.getLessonCompleted();

        setupBarListener();
        setupLessonBar();

        Hawk.put("currentLanguage", currentLanguage.getText().toString().toLowerCase());
    }

    private void setupBarListener() {

        basic1Bar.setOnClickListener(v -> {

            Hawk.put("lesson", "basic");
            ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
        });

        phrasesBar.setOnClickListener(v -> {

            Hawk.put("lesson", "phrases");
            ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
        });

        greetingBar.setOnClickListener(v -> {

            Hawk.put("lesson", "greeting");
            ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
        });

        foodBar.setOnClickListener(v -> {

            Hawk.put("lesson", "food");
            ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
        });

        animalBar.setOnClickListener(v -> {

            Hawk.put("lesson", "animal");
            ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
        });

        clothingBar.setOnClickListener(v -> {

            Hawk.put("lesson", "clothing");
            ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
        });

        shortcutButton.setOnClickListener(v -> {

            Hawk.put("lesson", "shortcut");
            ActivityNavigation.getInstance(LessonListActivity.this).takeToRandomTask();
        });
    }

    private void setupLessonBar() {

        handler.postDelayed(() -> {

            if (Hawk.get("basic") != null) {
                basic1Bar.setProgress(100);
            }

            if (Hawk.get("phrases") != null) {
                phrasesBar.setProgress(100);
            }

            if (Hawk.get("greeting") != null) {
                greetingBar.setProgress(100);
            }

            if (Hawk.get("food") != null) {
                foodBar.setProgress(100);
            }

            if (Hawk.get("animal") != null) {
                animalBar.setProgress(100);
            }

            if (Hawk.get("clothing") != null) {
                clothingBar.setProgress(100);
            }
        }, 2000);
    }

    private boolean checkUserValid() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {

            Intent intent = new Intent(this, SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        return true;
    }
}