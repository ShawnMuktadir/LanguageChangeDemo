package com.example.languagechangedemo;

import android.content.Context;
import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mstb_multi_id)
    MultiStateToggleButton mstb_multi_id;
    TextView textView;

    Button english, bengali;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        textView = findViewById(R.id.tv);
        english = findViewById(R.id.english);
        bengali = findViewById(R.id.bengali);

        Paper.init(this);
        String language = Paper.book().read("language");

        if (language == null) {
            Paper.book().write("language", "en");

            updateView((String) Paper.book().read("language"));
        }

        setlisteners();
        multiStateToogleButtonImpl();
    }

    private void setlisteners() {
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write("language", "");
                updateView((String) Paper.book().read("language"));
            }
        });

        bengali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write("language", "bn");
                updateView((String) Paper.book().read("language"));
            }
        });

    }

    private void updateView(String language) {
        Context context = LocaleHelper.setLocale(this, language);
        Resources resources = context.getResources();
        textView.setText(resources.getString(R.string.hello));
    }

    private void multiStateToogleButtonImpl() {
//        Changing colors programmatically

//        MultiStateToggleButton button = (MultiStateToggleButton) this.findViewById(R.id.mstb_multi_id);
//        button.setColorRes(R.color.color_pressed, R.color.color_released);

        mstb_multi_id.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int position) {
//                Log.d("MultiState", "Position: " + position);
//                Toast.makeText(getApplicationContext(), position + " Clicked", Toast.LENGTH_LONG).show();
                if (position == 0){
                    Paper.book().write("language", "bn");
                    updateView((String) Paper.book().read("language"));
                }else{
                    Paper.book().write("language", "");
                    updateView((String) Paper.book().read("language"));
                }
            }
        });
    }
}
