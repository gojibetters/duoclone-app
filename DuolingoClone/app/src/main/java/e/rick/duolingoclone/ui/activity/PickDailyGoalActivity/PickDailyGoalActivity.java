package e.rick.duolingoclone.ui.activity.PickDailyGoalActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.google.firebase.FirebaseApp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.Data.Repository;
import e.rick.duolingoclone.R;
import e.rick.duolingoclone.Utils.Injection;
import e.rick.duolingoclone.ui.activity.LessonListActivity.LessonListActivity;

/**
 * Created by Rick on 3/9/2018.
 */

public class PickDailyGoalActivity extends AppCompatActivity {

    @BindView(R.id.back_button)
    ImageView backButton;

    @BindView(R.id.casual_goal)
    RadioButton casualGoal;

    @BindView(R.id.regular_goal)
    RadioButton regularGoal;

    @BindView(R.id.serious_goal)
    RadioButton seriousGoal;

    @BindView(R.id.insane_goal)
    RadioButton insaneGoal;

    @BindView(R.id.continue_button)
    Button continueButton;

    ArrayList<RadioButton> radioButtonArray = new ArrayList<>();

    int checkedButton;

    Repository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_pick_daily_goal);

        ButterKnife.bind(this);

        repository = Injection.provideRepository();

        setRadioButton();
        regularGoal.setChecked(true);

        continueListener();

        changeScreenMain();
    }

    private void setRadioButton() {

        radioButtonArray.add(casualGoal);
        radioButtonArray.add(regularGoal);
        radioButtonArray.add(seriousGoal);
        radioButtonArray.add(insaneGoal);

        for (int i = 0; i < radioButtonArray.size(); i++) {

            final int finalIndex;
            finalIndex = i;

            radioButtonArray.get(i).setOnClickListener(v -> {

                checkedButton = finalIndex;

                ArrayList<Integer> buttonIdx = new ArrayList<>();

                buttonIdx.add(0);
                buttonIdx.add(1);
                buttonIdx.add(2);
                buttonIdx.add(3);

                buttonIdx.remove(finalIndex);

                radioButtonArray.get(finalIndex).setChecked(true);

                for (int index : buttonIdx) {

                    radioButtonArray.get(index).setChecked(false);
                }
            });
        }
    }

    private void continueListener() {

        continueButton.setOnClickListener(v -> {

            int dailyGoal = 2;

            if(checkedButton == 3) dailyGoal = 50; else dailyGoal = (checkedButton + 1) * 10;

            repository.setDailyGoal(dailyGoal);
        });

//        backButton.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.back_button));
        backButton.setOnClickListener(v -> onBackPressed());

    }

    private void changeScreenMain(){

        Intent it = new Intent(this, LessonListActivity.class);
        startActivity(it);

    }
}
