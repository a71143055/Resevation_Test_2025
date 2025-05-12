package kr.ac.kopo.reservation_test_2025;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    RadioGroup rg;
    RadioButton rb_date, rb_time;
    CalendarView calendar;
    TimePicker time_pick;
    TextView text_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chronometer = findViewById(R.id.chronometer);
        Button btn_start = findViewById(R.id.btn_start);
        rg = findViewById(R.id.rg);
        rb_date = findViewById(R.id.rb_date);
        rb_time = findViewById(R.id.rb_time);
        calendar = findViewById(R.id.calendar);
        time_pick = findViewById(R.id.time_pick);
        text_result = findViewById(R.id.text_result);
        Button btnDone = findViewById(R.id.btn_done);

        calendar.setVisibility(View.INVISIBLE);
        time_pick.setVisibility(View.INVISIBLE);
        rb_date.setOnClickListener(rbListener);
    }
    View.OnClickListener rbListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rbEvent = (RadioButton) v;
            if(rbEvent == rb_date) {
                calendar.setVisibility(View.VISIBLE);
                time_pick.setVisibility(View.INVISIBLE);
            } else if(rbEvent == rb_time) {
                calendar.setVisibility(View.INVISIBLE);
                time_pick.setVisibility(View.VISIBLE);
            }
        }
    };
}