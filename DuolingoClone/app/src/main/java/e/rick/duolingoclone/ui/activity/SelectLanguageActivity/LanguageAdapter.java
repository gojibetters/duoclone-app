package e.rick.duolingoclone.ui.activity.SelectLanguageActivity;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.rick.duolingoclone.Data.Repository;
import e.rick.duolingoclone.ui.activity.PickDailyGoalActivity.PickDailyGoalActivity;
import e.rick.duolingoclone.R;
import e.rick.duolingoclone.Utils.Injection;

/**
 * Created by Rick on 3/8/2018.
 */

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {

    private static final String TAG = "LanguageAdapter";

    String[] languages = {
            "Spanish", "Portuguese", "Swedish", "French",
            "German", "Chinese", "Japanese", "Korean", "Italian",
            "Dutch", "Irish", "Danish"};

    Repository repository;
    Context context;

    public LanguageAdapter(Context context) {

        repository = Injection.provideRepository();
        this.context = context;
    }

    @NonNull
    @Override
    public LanguageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_course_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LanguageAdapter.ViewHolder holder, final int position) {

        final String language = languages[position];

        holder.tvLanguage.setText(language);

        holder.main_item_layout.setOnClickListener(v -> {

            repository.setNewLanguage(language.toLowerCase());

            Intent intent = new Intent(context, PickDailyGoalActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return languages.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.language)
        TextView tvLanguage;

        @BindView(R.id.main_item_layout)
        RelativeLayout main_item_layout;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
