package com.example.sccounder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private Spinner[] gradeSpinners;
    private TextView cgpaResult;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Spinners and TextView
        gradeSpinners = new Spinner[] {
                findViewById(R.id.grade1),
                findViewById(R.id.grade2),
                findViewById(R.id.grade3),
                findViewById(R.id.grade4),
                findViewById(R.id.grade5),
                findViewById(R.id.grade6),
                findViewById(R.id.grade7),
                findViewById(R.id.grade8),
                findViewById(R.id.grade9)
        };
        cgpaResult = findViewById(R.id.cgpaResult);
    }

    public void setCgpaResult(View view) {
        int totalCredit = 4 + 4 + 2 + 2 + 2 + 0 + 1 + 2 + 1;
        double totalGradePoints = 0;

        for (int i = 0; i < gradeSpinners.length; i++) {
            String grade = gradeSpinners[i].getSelectedItem().toString().toUpperCase();
            totalGradePoints += getGradePoint(grade) * getCreditsForSubject(i);
        }

        double cgpa = totalGradePoints / totalCredit;
        cgpaResult.setText("CGPA: " + String.format("%.2f", cgpa));
        Toast.makeText(this, "CGPA: " + String.format("%.2f", cgpa), Toast.LENGTH_SHORT).show();
    }

    private double getCreditsForSubject(int index) {
        switch (index) {
            case 0: return 4;
            case 1: return 4;
            case 2: return 2;
            case 3: return 2;
            case 4: return 2;
            case 5: return 0;
            case 6: return 1;
            case 7: return 2;
            case 8: return 1;
            default: return 0;
        }
    }

    private double getGradePoint(String grade) {
        switch (grade) {
            case "EX": return 10;
            case "AA": return 9;
            case "AB": return 8.5;
            case "BB": return 8;
            case "BC": return 7.5;
            case "CC": return 7;
            case "CD": return 6.5;
            case "DD": return 6;
            case "DE": return 5.5;
            case "EE": return 5;
            default: return 0;
        }
    }
}
