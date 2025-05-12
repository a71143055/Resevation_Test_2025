package kr.ac.kopo.reservation_test_2025;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
    Button btn_start, btnDone;

    int selectedYear, selectedMonth, selectedDay;
    int selectedHour, selectedMinute;

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
        btn_start = findViewById(R.id.btn_start);
        rg = findViewById(R.id.rg);
        rb_date = findViewById(R.id.rb_date);
        rb_time = findViewById(R.id.rb_time);
        calendar = findViewById(R.id.calendar);
        time_pick = findViewById(R.id.time_pick);
        text_result = findViewById(R.id.text_result);
        btnDone = findViewById(R.id.btn_done);

        calendar.setVisibility(View.INVISIBLE);
        time_pick.setVisibility(View.INVISIBLE);
        rb_date.setOnClickListener(rbListener);
        rb_time.setOnClickListener(rbListener);
        btn_start.setOnClickListener(btnListener);
        btnDone.setOnClickListener(btnListener);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedYear = year;
                selectedMonth = month + 1;
                selectedDay = dayOfMonth;
            }
        });
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

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button btnEvent = (Button) v;
            if(btnEvent == btn_start) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                chronometer.setTextColor(Color.RED);
            } else if(btnEvent == btnDone) {
                chronometer.stop();
                chronometer.setTextColor(Color.BLUE);
                selectedHour = time_pick.getHour();
                selectedMinute = time_pick.getMinute();
                text_result.setText("예약 완료 : " + selectedYear + "년 " + selectedMonth + "월 " + selectedDay + "일 " + selectedHour + "시 " + selectedMinute + "분");
            }
        }
    };
}